package com.cosmo.vendorservice.businesshour.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.businesshour.model.BusinessHourRequest;
import com.cosmo.vendorservice.businesshour.model.CreateBusinessHourRequestModel;
import com.cosmo.vendorservice.businesshour.model.UpdateBusinessHourModel;
import com.cosmo.vendorservice.businesshour.service.BusinessHourService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
