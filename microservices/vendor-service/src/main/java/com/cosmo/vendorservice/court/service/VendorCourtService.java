package com.cosmo.vendorservice.court.service;

import com.cosmo.authentication.vendor.model.request.BlockVendorRequest;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.vendorservice.court.model.BlockCourtRequest;
import com.cosmo.vendorservice.court.model.CreateCourtRequestModel;
import com.cosmo.vendorservice.court.model.UpdateCourtRequest;
import reactor.core.publisher.Mono;


public interface VendorCourtService {
    Mono<ApiResponse<?>> createCourt(CreateCourtRequestModel createCourtRequestModel);
    Mono<ApiResponse<?>> updateCourt(UpdateCourtRequest updateCourtRequest);
    Mono<ApiResponse<?>> blockCourt(BlockCourtRequest blockCourtRequest);
    Mono<ApiResponse<?>> getAllCourt(SearchParam searchParam);
}
