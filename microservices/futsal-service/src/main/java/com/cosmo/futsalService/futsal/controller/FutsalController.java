package com.cosmo.futsalService.futsal.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.futsalService.futsal.model.CreateFutsalModel;
import com.cosmo.futsalService.futsal.model.request.*;
import com.cosmo.futsalService.futsal.service.FutsalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(ApiConstant.FUTSAL)
@RequiredArgsConstructor
public class FutsalController {
    private final FutsalService futsalService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> createFutsal(@RequestBody @Valid CreateFutsalModel createFutsalModel){
        return futsalService.createFutsal(createFutsalModel);
    }
    @PostMapping(ApiConstant.GET+ApiConstant.SLASH+ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> getFutsalDetails(@RequestBody @Valid FetchFutsalDetail fetchFutsalDetail){
        return futsalService.getFutsalDetails(fetchFutsalDetail);
    }
    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> getAllFutsal(@RequestBody @Valid SearchParam searchParam){
        return futsalService.getAllFutsal(searchParam);
    }

    @PostMapping(ApiConstant.UPDATE)
    public Mono<ApiResponse> updateFutsal(@RequestBody @Valid UpdateFutsalModel updateFutsalModel){
        return futsalService.updateFutsal(updateFutsalModel);
    }

    @PostMapping(ApiConstant.BLOCK)
    public Mono<ApiResponse<?>> blockFutsal(@RequestBody @Valid BlockFutsalRequest blockFutsalRequest){
        return futsalService.blockFutsal(blockFutsalRequest);
    }

    @PostMapping(ApiConstant.UNBLOCK)
    public Mono<ApiResponse<?>> unblockFutsal(@RequestBody @Valid UnblockFutsalRequest unblockFutsalRequest){
        return futsalService.unblockFutsal(unblockFutsalRequest);
    }

    @PostMapping(ApiConstant.GET_BY_CODE)
    public Mono<ApiResponse<?>> getCourtDetails(@RequestBody @Valid SearchParam searchParam, FetchFutsalByVendor fetchFutsalByVendor) {
        return futsalService.listFutsalByVendor(searchParam, fetchFutsalByVendor);
    }
}
