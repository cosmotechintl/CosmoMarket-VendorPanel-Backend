package com.cosmo.productsservice.product.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.productsservice.product.model.CreateProductModel;
import com.cosmo.productsservice.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.PRODUCT)
@RequiredArgsConstructor
public class ProductController {
        private final ProductService productService;

        @PostMapping(ApiConstant.CREATE)
        public Mono<ApiResponse> createProduct(@RequestBody @Valid CreateProductModel create){
            return productService.createProduct(create);
        }
}
