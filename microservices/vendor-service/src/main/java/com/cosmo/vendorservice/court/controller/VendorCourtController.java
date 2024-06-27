package com.cosmo.vendorservice.court.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.vendorservice.court.model.*;
import com.cosmo.vendorservice.court.service.VendorCourtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(ApiConstant.COURT)
@RequiredArgsConstructor
public class VendorCourtController {
    private final VendorCourtService vendorCourtService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse<?>> addCourt(@RequestBody CreateCourtRequestModel createCourtRequestModel) {
        return vendorCourtService.createCourt(createCourtRequestModel);
    }

    @PostMapping(ApiConstant.EDIT)
    public Mono<ApiResponse<?>> edit(@RequestBody @Valid UpdateCourtRequest updateCourtRequest) {
        return vendorCourtService.updateCourt(updateCourtRequest);
    }

    @PostMapping(ApiConstant.BLOCK)
    public Mono<ApiResponse<?>> blockCourt(@RequestBody @Valid BlockCourtRequest blockCourtRequest) {
        return vendorCourtService.blockCourt(blockCourtRequest);
    }

    @PostMapping(ApiConstant.UNBLOCK)
    public Mono<ApiResponse<?>> unblockCourt(@RequestBody @Valid UnblockCourtRequest request) {
        return vendorCourtService.unblockCourt(request);
    }

    @PostMapping(ApiConstant.GET_ALL_COURT)
    public Mono<ApiResponse<?>> getAllCourt(@RequestBody @Valid SearchParam searchParam) {
        return vendorCourtService.getAllCourt(searchParam);
    }

    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> getCourtDetails(@RequestBody @Valid FetchCourtDetails fetchCourtDetails) {
        return vendorCourtService.getCourtDetails(fetchCourtDetails);
    }
}
