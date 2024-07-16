package com.cosmo.vendorservice.businessHourService.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.vendorservice.businessHourService.model.BusinessHourDetailModel;
import com.cosmo.vendorservice.businessHourService.model.BusinessHourRequest;
import com.cosmo.vendorservice.businessHourService.model.UpdateBusinessHourModel;
import com.cosmo.vendorservice.businessHourService.service.BusinessHourService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.BUSINESS_HOUR)
@RequiredArgsConstructor
public class BusinessHourController {

    private final BusinessHourService businessHourService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse<Object>> addBusinessHour(@RequestBody BusinessHourRequest createBusinessHourRequest
            , Principal connectedUser) {
        return businessHourService.addBusinessHour(createBusinessHourRequest, connectedUser);
    }

    @PostMapping(ApiConstant.EDIT)
    public Mono<ApiResponse<Object>> editAccessGroup(@RequestBody @Valid UpdateBusinessHourModel updateBusinessHourModels
            , Principal connectedUser) {
        return businessHourService.updateBusinessHour(updateBusinessHourModels, connectedUser);
    }

    @PostMapping(ApiConstant.DETAIL)
    public Mono<ApiResponse<Object>> getVendorBusinessHours(BusinessHourDetailModel businessHourDetailModel, Principal connectedUser) {
        return businessHourService.getVendorBusinessHours(businessHourDetailModel,connectedUser);
    }
    @PostMapping()
    public Mono<ApiResponse<Object>> getBusinessHours( SearchParam searchParam) {
        return businessHourService.getBusinessHours(searchParam);
    }
}
