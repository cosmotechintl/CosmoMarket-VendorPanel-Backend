package com.cosmo.vendorservice.businesshour.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.businesshour.model.CreateBusinessHourRequestModel;
import com.cosmo.vendorservice.businesshour.model.UpdateBusinessHourModel;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;

public interface BusinessHourService {
    Mono<ApiResponse<?>> addBusinessHour(CreateBusinessHourRequestModel businessHourRequests, Principal connectedUser);
    Mono<ApiResponse<?>> updateBusinessHour(List<UpdateBusinessHourModel> updateBusinessHourModels, Principal connectedUser);
}
