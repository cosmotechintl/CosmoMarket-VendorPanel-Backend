package com.cosmo.vendorservice.futsalService.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.vendorservice.futsalService.model.CreateFutsalModel;
import com.cosmo.vendorservice.futsalService.model.FetchFutsalDetail;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface FutsalService{
    Mono<ApiResponse<Object>> createFutsal(CreateFutsalModel createFutsalModel, Principal connectedUser);
    Mono<ApiResponse<Object>> getAllFutsal(SearchParam searchParam);
    Mono<ApiResponse<Object>> getFutsalDetails(FetchFutsalDetail fetchFutsalDetail);
}
