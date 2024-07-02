package com.cosmo.futsalService.futsal.service.impl;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.futsalService.futsal.entity.Futsal;
import com.cosmo.futsalService.futsal.mapper.FutsalMapper;
import com.cosmo.futsalService.futsal.model.CreateFutsalModel;
import com.cosmo.futsalService.futsal.model.FutsalDto;
import com.cosmo.futsalService.futsal.model.SearchFutsalResponse;
import com.cosmo.futsalService.futsal.model.request.FetchFutsalDetail;
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

    @Override
    public Mono<ApiResponse> createFutsal(CreateFutsalModel createFutsalModel) {
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
}
