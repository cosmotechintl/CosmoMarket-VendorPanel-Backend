package com.cosmo.vendorservice.productService.servcie;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.productService.model.CreateProductModel;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface ProductService {
    Mono<ApiResponse<Object>> createProduct(CreateProductModel createProductModel, Principal connectedVendor);
}
