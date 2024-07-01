package com.cosmo.vendorservice.futsalService.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.futsalService.model.CreateFutsalModel;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface FutsalService{
    Mono<ApiResponse<Object>> createFutsal(CreateFutsalModel createFutsalModel, Principal connectedUser);
}
