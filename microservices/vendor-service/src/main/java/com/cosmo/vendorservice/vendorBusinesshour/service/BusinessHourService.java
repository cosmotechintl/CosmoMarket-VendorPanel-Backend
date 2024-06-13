package com.cosmo.vendorservice.vendorBusinesshour.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.vendorBusinesshour.model.SetBusinessHour;
import com.cosmo.vendorservice.vendorBusinesshour.model.UpdateBusinessHourModel;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;

public interface BusinessHourService {
    Mono<ApiResponse<?>> addBusinessHour(List<SetBusinessHour> setBusinessHours, Principal connectedUser);
    Mono<ApiResponse<?>> updateBusinessHour(List<UpdateBusinessHourModel> updateBusinessHourModels, Principal connectedUser);
}
