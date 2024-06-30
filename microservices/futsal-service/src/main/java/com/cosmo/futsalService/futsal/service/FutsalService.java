package com.cosmo.futsalService.futsal.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.futsalService.futsal.model.CreateFutsalModel;
import com.cosmo.futsalService.futsal.model.request.FetchFutsalDetail;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface FutsalService {
    Mono<ApiResponse> createFutsal(CreateFutsalModel createFutsalModel, Principal connectedUser);
    Mono<ApiResponse<?>> getFutsalDetails(FetchFutsalDetail fetchFutsalDetail);
}
