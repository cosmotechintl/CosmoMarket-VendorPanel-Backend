package com.cosmo.authentication.vendor.controller;

import com.cosmo.authentication.vendor.service.CategoryService;
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
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> getCategories(@RequestBody @Valid SearchParam searchParam){
        return categoryService.getCategories(searchParam);
    }
}
