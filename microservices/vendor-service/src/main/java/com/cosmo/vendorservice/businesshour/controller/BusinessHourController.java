package com.cosmo.vendorservice.businesshour.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.businesshour.model.BusinessHourBookingModel;
import com.cosmo.vendorservice.businesshour.model.CreateBusinessHourRequestModel;
import com.cosmo.vendorservice.businesshour.model.UpdateBusinessHourModel;
import com.cosmo.vendorservice.businesshour.service.BusinessHourService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(ApiConstant.BUSINESS_HOUR)
@RequiredArgsConstructor
public class BusinessHourController {
    private final BusinessHourService businessHourService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse<?>> addBusinessHour(@RequestBody CreateBusinessHourRequestModel createBusinessHourRequestModel
            , Principal connectedUser) {
        return businessHourService.addBusinessHour(createBusinessHourRequestModel, connectedUser);
    }

    @PostMapping(ApiConstant.EDIT)
    public Mono<ApiResponse<?>> editAccessGroup(@RequestBody @Valid List<UpdateBusinessHourModel> updateBusinessHourModels
            , Principal connectedUser) {
        return businessHourService.updateBusinessHour(updateBusinessHourModels, connectedUser);
    }

    @GetMapping(ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> getBusinessHours(Principal connectedUser) {
        return businessHourService.getBusinessHours(connectedUser);
    }
    @PostMapping()
    public Mono<ApiResponse> getBusinessHours(@RequestBody @Valid BusinessHourBookingModel businessHourBookingModel) {
        return businessHourService.getBusinessHours(businessHourBookingModel);
    }
}
