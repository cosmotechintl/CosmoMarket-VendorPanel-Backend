package com.cosmo.vendorservice.businessHourService.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.vendorservice.businessHourService.model.BusinessHourDetailModel;
import com.cosmo.vendorservice.businessHourService.model.BusinessHourRequest;
import com.cosmo.vendorservice.businessHourService.model.UpdateBusinessHourModel;

import reactor.core.publisher.Mono;

import java.security.Principal;

public interface BusinessHourService {
    Mono<ApiResponse<Object>> addBusinessHour(BusinessHourRequest businessHourRequests, Principal connectedUser);
    Mono<ApiResponse<Object>> updateBusinessHour(UpdateBusinessHourModel updateBusinessHourModels, Principal connectedUser);
    Mono<ApiResponse<Object>> getVendorBusinessHours(BusinessHourDetailModel businessHourDetailModel, Principal connectedUser);
    Mono<ApiResponse<Object>> getBusinessHours(SearchParam searchParam);
}
