package com.cosmo.futsalService.businesshour.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.futsalService.businesshour.model.BusinessHourBookingModel;
import com.cosmo.futsalService.businesshour.model.BusinessHourDetailModel;
import com.cosmo.futsalService.businesshour.model.CreateBusinessHourRequestModel;
import com.cosmo.futsalService.businesshour.model.UpdateBusinessHourModel;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;

public interface BusinessHourService {
    Mono<ApiResponse> addBusinessHour(CreateBusinessHourRequestModel businessHourRequests);
    Mono<ApiResponse> updateBusinessHour(UpdateBusinessHourModel updateBusinessHourModels);
    Mono<ApiResponse<?>> getVendorsBusinessHours(BusinessHourDetailModel businessHourDetailModel);
    Mono<ApiResponse<?>> getBusinessHours(SearchParam searchParam);

}
