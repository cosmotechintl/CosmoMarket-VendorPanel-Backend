package com.cosmo.productsservice.product.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.productsservice.product.model.CreateProductModel;
import com.cosmo.productsservice.product.model.FetchProductDetail;
import com.cosmo.productsservice.product.model.request.DeleteProductRequest;
import com.cosmo.productsservice.product.model.request.UpdateProductRequest;
import reactor.core.publisher.Mono;

public interface ProductService {

   Mono<ApiResponse> createProduct(CreateProductModel createProductModel);
   Mono<ApiResponse<?>> deleteProduct(DeleteProductRequest deleteProductRequest);
   Mono<ApiResponse<?>> viewProductDetail(FetchProductDetail fetchProductDetail);
   Mono<ApiResponse<?>> viewProductList(SearchParam searchParam);
   Mono<ApiResponse<?>> updateProduct(UpdateProductRequest updateProductRequest);
}
