package com.cosmo.futsalService.futsal.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.futsalService.futsal.model.CreateFutsalModel;
import com.cosmo.futsalService.futsal.model.request.*;
import reactor.core.publisher.Mono;


public interface FutsalService {
    Mono<ApiResponse> createFutsal(CreateFutsalModel createFutsalModel);
    Mono<ApiResponse<?>> getFutsalDetails(FetchFutsalDetail fetchFutsalDetail);
    Mono<ApiResponse<?>> getAllFutsal(SearchParam searchParam);
    Mono<ApiResponse> updateFutsal(UpdateFutsalModel updateFutsalModel);
    Mono<ApiResponse<?>> blockFutsal(BlockFutsalRequest blockVendorRequest);
    Mono<ApiResponse<?>> unblockFutsal(UnblockFutsalRequest unblockFutsalRequest);
    Mono<ApiResponse<?>> listFutsalByVendor(SearchParam searchParam, FetchFutsalByVendor fetchFutsalByVendor);

}
