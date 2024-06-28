package com.cosmo.vendorservice.court.service.impl;

import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.vendorservice.court.entity.CourtDetails;
import com.cosmo.vendorservice.court.log.entity.CourtBlockLog;
import com.cosmo.vendorservice.court.log.mapper.BlockCourtLogMapper;
import com.cosmo.vendorservice.court.log.model.BlockCourtLogModel;
import com.cosmo.vendorservice.court.log.repo.CourtBlockLogRepository;
import com.cosmo.vendorservice.court.mapper.CourtMapper;
import com.cosmo.vendorservice.court.model.*;
import com.cosmo.vendorservice.court.model.request.SearchCourtResponse;
import com.cosmo.vendorservice.court.repo.VendorCourtCreationRepository;
import com.cosmo.vendorservice.court.repo.VendorCourtSearchRepository;
import com.cosmo.vendorservice.court.service.VendorCourtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VendorCourtServiceImpl implements VendorCourtService {
    private final VendorCourtCreationRepository courtCreationRepository;
    private final CourtMapper courtMapper;
    private final StatusRepository statusRepository;
    private final VendorCourtSearchRepository vendorCourtSearchRepository;
    private final SearchResponse searchResponse;
    private final BlockCourtLogMapper blockCourtLogMapper;
    private final CourtBlockLogRepository courtBlockLogRepository;

    @Override
    public Mono<ApiResponse<?>> createCourt(CreateCourtRequestModel createCourtRequestModel) {
        CourtDetails courtDetails = courtMapper.toEntity(createCourtRequestModel);
        try {
            courtCreationRepository.save(courtDetails);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Court created successfully"));
        } catch (Exception e) {
            log.error("Error creating court: ", e);
            return Mono.just(ResponseUtil.getFailureResponse("Court creation failed"));
        }
    }


    @Override
    public Mono<ApiResponse<?>> updateCourt(UpdateCourtRequest updateCourtRequest) {
        Optional<CourtDetails> optionalCourt = courtCreationRepository.findByName(updateCourtRequest.getName());
        if (optionalCourt.isPresent()) {
            CourtDetails courtDetails = optionalCourt.get();
            CourtMapper.updateEntity(courtDetails, updateCourtRequest);
            courtCreationRepository.save(courtDetails);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Court updated successfully"));
        } else {
            return Mono.just(ResponseUtil.getFailureResponse("Court not found"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> blockCourt(BlockCourtRequest blockCourtRequest, BlockCourtLogModel blockCourtLogModel) {
        Optional<CourtDetails> court = courtCreationRepository.findByName(blockCourtRequest.getName());
        if (court.isEmpty()) {
            return Mono.just(ResponseUtil.getFailureResponse("Court not found"));
        } else {
            CourtDetails courtDetails = court.get();
            if (StatusConstant.DELETED.getName().equals(courtDetails.getStatus().getName()) || StatusConstant.BLOCKED.getName().equals(courtDetails.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Court not found"));
            } else {
                courtDetails.setStatus(statusRepository.findByName(StatusConstant.BLOCKED.getName()));
                courtCreationRepository.save(courtDetails);

                blockCourtLogModel.setRemarks(blockCourtRequest.getRemarks());
                blockCourtLogModel.setCourt(courtDetails);

                CourtBlockLog courtBlockLog = blockCourtLogMapper.mapToEntity(blockCourtLogModel);
                courtBlockLogRepository.save(courtBlockLog);
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("court blocked successfully"));
            }
        }
    }

    @Override
    public Mono<ApiResponse<?>> unblockCourt(UnblockCourtRequest unblockCourtRequest) {
        Optional<CourtDetails> court = courtCreationRepository.findByName(unblockCourtRequest.getName());
        CourtDetails courtDetails = court.get();
        if (StatusConstant.BLOCKED.getName().equals(courtDetails.getStatus().getName())) {
            courtDetails.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
            courtCreationRepository.save(courtDetails);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Court unblocked successfully"));
        }
        return Mono.just(ResponseUtil.getFailureResponse("Court unblock failed"));
    }

    @Override
    public Mono<ApiResponse<?>> getAllCourt(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<CourtDetails, SearchCourtResponse> responseBuilder = SearchResponseWithMapperBuilder.<CourtDetails, SearchCourtResponse>builder()
                .count(vendorCourtSearchRepository::count).searchData(vendorCourtSearchRepository::getAll)
                .mapperFunction(this.courtMapper::getCourtResponses).searchParam(searchParam)
                .build();
        PageableResponse<SearchCourtResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Courts fetched successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> getCourtDetails(FetchCourtDetails fetchCourtDetails) {
        Optional<CourtDetails> court = courtCreationRepository.findByName(fetchCourtDetails.getName());
        if (court.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Court not found"));
        } else {
            CourtDetailsDto courtDetailsDto = courtMapper.getCourtDetails(court.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(courtDetailsDto, "Court details fetched successfully"));
        }
    }


}
