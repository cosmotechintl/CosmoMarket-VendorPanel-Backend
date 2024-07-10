package com.cosmo.authentication.vendor.controller;

import com.cosmo.authentication.vendor.model.VendorCategoryCreateModel;
import com.cosmo.authentication.vendor.service.VendorCategoryService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.VENDOR_CATEGORY)
@RequiredArgsConstructor
public class VendorCategoryController {
    private final VendorCategoryService vendorCategoryService;

    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> getCategories(@RequestBody @Valid SearchParam searchParam){
        return vendorCategoryService.getCategories(searchParam);
    }
    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> createVendorCategory(@RequestBody @Valid VendorCategoryCreateModel createModel){
        return vendorCategoryService.createVendorCategory(createModel);
    }
}
