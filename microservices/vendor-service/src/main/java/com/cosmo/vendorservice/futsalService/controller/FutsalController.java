package com.cosmo.vendorservice.futsalService.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.futsalService.model.CreateFutsalModel;
import com.cosmo.vendorservice.futsalService.service.FutsalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.FUTSAL)
@RequiredArgsConstructor
public class FutsalController {
    private final FutsalService futsalService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse<Object>> createFutsal(@RequestBody @Valid CreateFutsalModel createFutsalModel, Principal connectedUser){
        return futsalService.createFutsal(createFutsalModel, connectedUser);
    }
}
