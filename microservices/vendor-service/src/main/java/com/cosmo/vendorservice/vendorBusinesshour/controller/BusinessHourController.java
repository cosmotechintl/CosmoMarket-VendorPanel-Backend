package com.cosmo.vendorservice.vendorBusinesshour.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.vendorBusinesshour.model.SetBusinessHour;
import com.cosmo.vendorservice.vendorBusinesshour.model.UpdateBusinessHourModel;
import com.cosmo.vendorservice.vendorBusinesshour.service.BusinessHourService;
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
    public Mono<ApiResponse<?>> addBusinessHour(@RequestBody @Valid List<SetBusinessHour> setBusinessHours
            , Principal connectedUser) {
        return businessHourService.addBusinessHour(setBusinessHours, connectedUser);
    }

    @PostMapping(ApiConstant.EDIT)
    public Mono<ApiResponse<?>> editAccessGroup(@RequestBody @Valid List<UpdateBusinessHourModel> updateBusinessHourModels
            , Principal connectedUser) {
        return businessHourService.updateBusinessHour(updateBusinessHourModels, connectedUser);
    }
}
