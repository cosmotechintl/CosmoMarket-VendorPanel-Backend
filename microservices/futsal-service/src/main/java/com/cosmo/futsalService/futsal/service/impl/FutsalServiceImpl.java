package com.cosmo.futsalService.futsal.service.impl;

import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.futsalService.futsal.entity.Futsal;
import com.cosmo.futsalService.futsal.log.entity.FutsalBlockLog;
import com.cosmo.futsalService.futsal.log.mapper.FutsalLogMapper;
import com.cosmo.futsalService.futsal.log.model.FutsalBlockLogModel;
import com.cosmo.futsalService.futsal.log.repo.FutsalBlockLogRepository;
import com.cosmo.futsalService.futsal.mapper.FutsalMapper;
import com.cosmo.futsalService.futsal.model.CreateFutsalModel;
import com.cosmo.futsalService.futsal.model.FutsalDto;
import com.cosmo.futsalService.futsal.model.SearchFutsalResponse;
import com.cosmo.futsalService.futsal.model.request.*;
import com.cosmo.futsalService.futsal.repo.FutsalRepository;
import com.cosmo.futsalService.futsal.repo.FutsalSearchRepository;
import com.cosmo.futsalService.futsal.service.FutsalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FutsalServiceImpl implements FutsalService {
    private final FutsalRepository futsalRepository;
    private final FutsalMapper futsalMapper;
    private final FutsalSearchRepository futsalSearchRepository;
    private final SearchResponse searchResponse;
    private final StatusRepository statusRepository;
    private final FutsalLogMapper futsalLogMapper;
    private final FutsalBlockLogRepository futsalBlockLogRepository;

    @Override
    public Mono<ApiResponse> createFutsal(CreateFutsalModel createFutsalModel) {
        Optional<Futsal>existedFutsalName = futsalRepository.findByName(createFutsalModel.getName());
        if (existedFutsalName.isPresent()){
            return Mono.just(ResponseUtil.getFailureResponse("This name already exists"));
        }
        Futsal futsal = futsalMapper.mapToEntity(createFutsalModel);
        futsalRepository.save(futsal);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Futsal created successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> getFutsalDetails(FetchFutsalDetail fetchFutsalDetail) {
        Optional<Futsal> checkFutsal = futsalRepository.findByUuid(fetchFutsalDetail.getUuid());
        if(checkFutsal.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("Futsal not found"));
        }
        else {
            FutsalDto futsalDto = futsalMapper.getFutsalDetails(checkFutsal.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(futsalDto, "Futsal details fetched successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> getAllFutsal(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<Futsal, SearchFutsalResponse> responseBuilder = SearchResponseWithMapperBuilder.<Futsal, SearchFutsalResponse>builder()
                .count(futsalSearchRepository::count).searchData(futsalSearchRepository::getAll)
                .mapperFunction(this.futsalMapper::getFutsalResponses).searchParam(searchParam)
                .build();
        PageableResponse<SearchFutsalResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response,"List of futsal fetched successfully"));
    }

    @Override
    public Mono<ApiResponse> updateFutsal(UpdateFutsalModel updateFutsalModel) {
        Optional<Futsal> oldFutsal = futsalRepository.findByUuid(updateFutsalModel.getUuid());
        if(oldFutsal.isPresent()){
            futsalMapper.updatetoEntity(oldFutsal.get(), updateFutsalModel);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Futsal updated Successfully"));
        }else {
            return Mono.just(ResponseUtil.getNotFoundResponse("Futsal not found"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> blockFutsal(BlockFutsalRequest blockFutsalRequest) {
        Optional<Futsal> checkFutsal = futsalRepository.findByUuid(blockFutsalRequest.getUuid());
        if (checkFutsal.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Futsal not found"));
        } else {
            Futsal futsal = checkFutsal.get();
            if (StatusConstant.DELETED.getName().equals(futsal.getStatus().getName()) || StatusConstant.BLOCKED.getName().equals(futsal.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Futsal not found"));
            } else {
                futsal.setStatus(statusRepository.findByName(StatusConstant.BLOCKED.getName()));
                futsalRepository.save(futsal);

                FutsalBlockLogModel futsalBlockLogModel = new FutsalBlockLogModel();
                futsalBlockLogModel.setRemarks(blockFutsalRequest.getRemarks());
                futsalBlockLogModel.setFutsal(futsal);

                futsalLogMapper.mapToEntity(futsalBlockLogModel);

                return Mono.just(ResponseUtil.getSuccessfulApiResponse("Futsal blocked successfully"));
            }
        }
    }

    @Override
    public Mono<ApiResponse<?>> unblockFutsal(UnblockFutsalRequest unblockFutsalRequest) {
        Optional<Futsal> checkFutsal = futsalRepository.findByUuid(unblockFutsalRequest.getCode());
        Futsal futsal = checkFutsal.get();
        if (StatusConstant.BLOCKED.getName().equals(futsal.getStatus().getName())) {
            futsal.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
            futsalRepository.save(futsal);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Futsal unblocked successfully"));
        }
        return Mono.just(ResponseUtil.getFailureResponse("Futsal unblock failed!"));
    }

    @Override
    public Mono<ApiResponse<?>> listFutsalByVendor(SearchParam searchParam, FetchFutsalByVendor fetchFutsalByVendor) {
        String futsalCode = fetchFutsalByVendor.getVendorCode();
        SearchResponseWithMapperBuilder<Futsal, SearchFutsalResponse> responseBuilder = SearchResponseWithMapperBuilder.<Futsal, SearchFutsalResponse>builder()
                .count(futsalSearchRepository::count).searchData(futsalSearchRepository.findByVendorCode(searchParam, futsalCode))
                .mapperFunction(this.futsalMapper::getFutsalResponses).searchParam(searchParam)
                .build();
        PageableResponse<SearchFutsalResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Futsals fetched successfully"));
    }
}
