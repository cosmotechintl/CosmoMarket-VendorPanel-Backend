package com.cosmo.productsservice.product.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.productsservice.product.model.CreateProductModel;
import reactor.core.publisher.Mono;

public interface ProductService {

   Mono<ApiResponse> createProduct(CreateProductModel createProductModel);
}
