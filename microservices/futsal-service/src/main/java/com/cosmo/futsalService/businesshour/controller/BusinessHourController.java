package com.cosmo.futsalService.businesshour.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.futsalService.businesshour.model.BusinessHourDetailModel;
import com.cosmo.futsalService.businesshour.service.BusinessHourService;
import com.cosmo.futsalService.businesshour.model.BusinessHourBookingModel;
import com.cosmo.futsalService.businesshour.model.CreateBusinessHourRequestModel;
import com.cosmo.futsalService.businesshour.model.UpdateBusinessHourModel;
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
    public Mono<ApiResponse> addBusinessHour(@RequestBody CreateBusinessHourRequestModel createBusinessHourRequestModel) {
        return businessHourService.addBusinessHour(createBusinessHourRequestModel);
    }

    @PostMapping(ApiConstant.EDIT)
    public Mono<ApiResponse> editAccessGroup(@RequestBody @Valid UpdateBusinessHourModel updateBusinessHourModels) {
        return businessHourService.updateBusinessHour(updateBusinessHourModels);
    }

    @PostMapping(ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> getVendorBusinessHours(@RequestBody @Valid BusinessHourDetailModel businessHourDetailModel) {
        return businessHourService.getVendorsBusinessHours(businessHourDetailModel);
    }
    @PostMapping()
    public Mono<ApiResponse<?>> getBusinessHours(@RequestBody @Valid SearchParam searchParam) {
        return businessHourService.getBusinessHours(searchParam);
    }
}
