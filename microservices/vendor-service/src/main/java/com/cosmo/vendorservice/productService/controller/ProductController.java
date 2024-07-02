package com.cosmo.vendorservice.productService.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.productService.model.CreateProductModel;
import com.cosmo.vendorservice.productService.servcie.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse<Object>> createProduct(@RequestBody @Valid CreateProductModel createProductModel, Principal connectedVendor){
        return productService.createProduct(createProductModel,connectedVendor);
    }
}
