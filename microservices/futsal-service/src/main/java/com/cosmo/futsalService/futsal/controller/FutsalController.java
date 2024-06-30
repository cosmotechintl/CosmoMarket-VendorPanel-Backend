package com.cosmo.futsalService.futsal.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.futsalService.futsal.model.CreateFutsalModel;
import com.cosmo.futsalService.futsal.model.request.FetchFutsalDetail;
import com.cosmo.futsalService.futsal.service.FutsalService;
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
    public Mono<ApiResponse> createFutsal(@RequestBody @Valid CreateFutsalModel createFutsalModel, Principal connectUser){
        return futsalService.createFutsal(createFutsalModel,connectUser);
    }
    @PostMapping(ApiConstant.GET+ApiConstant.SLASH+ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> getFutsalDetails(@RequestBody @Valid FetchFutsalDetail fetchFutsalDetail){
        return futsalService.getFutsalDetails(fetchFutsalDetail);
    }
}
