package com.cosmo.vendorservice.court.controller;

import com.cosmo.authentication.vendor.model.request.BlockVendorRequest;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.vendorservice.court.model.BlockCourtRequest;
import com.cosmo.vendorservice.court.model.CreateCourtRequestModel;
import com.cosmo.vendorservice.court.model.UpdateCourtRequest;
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
    public Mono<ApiResponse<?>> blockCourt(@RequestBody @Valid BlockCourtRequest blockCourtRequest){
        return vendorCourtService.blockCourt(blockCourtRequest);
    }
    @PostMapping()
    public Mono<ApiResponse<?>> getAllCategory(@RequestBody @Valid SearchParam searchParam){
        return vendorCourtService.getAllCourt(searchParam);
    }
}
