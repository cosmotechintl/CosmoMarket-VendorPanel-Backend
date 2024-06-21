package com.cosmo.vendorservice.court.service.impl;

import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.authentication.vendor.model.request.BlockVendorRequest;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.vendorservice.court.entity.CourtDetails;
import com.cosmo.vendorservice.court.mapper.CourtMapper;
import com.cosmo.vendorservice.court.model.BlockCourtRequest;
import com.cosmo.vendorservice.court.model.CreateCourtRequestModel;
import com.cosmo.vendorservice.court.model.UpdateCourtRequest;
import com.cosmo.vendorservice.court.repo.VendorCourtCreationRepository;
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

    @Override
    public Mono<ApiResponse<?>> createCourt(CreateCourtRequestModel createCourtRequestModel) {
        CourtDetails courtDetails = courtMapper.toEntity(createCourtRequestModel);
        try {
            courtCreationRepository.save(courtDetails);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Court created successfully"));
        }
        catch (Exception e) {
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
        }
            else {
            return Mono.just(ResponseUtil.getFailureResponse("Court not found"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> blockCourt(BlockCourtRequest blockCourtRequest) {
        Optional<CourtDetails> court = courtCreationRepository.findByName(blockCourtRequest.getName());
        if (court.isEmpty()) {
            return Mono.just(ResponseUtil.getFailureResponse("Court not found"));
        }else {
            CourtDetails courtDetails = court.get();
            if (StatusConstant.DELETED.getName().equals(courtDetails.getStatus().getName()) || StatusConstant.BLOCKED.getName().equals(courtDetails.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Court not found"));
            } else {
                courtDetails.setStatus(statusRepository.findByName(StatusConstant.BLOCKED.getName()));
                courtCreationRepository.save(courtDetails);
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("court blocked successfully"));
            }
        }
    }




}
