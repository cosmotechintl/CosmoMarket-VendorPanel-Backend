package com.cosmo.productService.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.productService.model.ProductRequestDto;
import com.cosmo.productService.model.ProductResponseDto;

public interface ProductService {
    ApiResponse<ProductResponseDto> createProduct(ProductRequestDto productRequestDto,String token);

    ApiResponse<String> deleteProduct(Long id);

    ApiResponse<?> getallProducts(SearchParam searchParam);

    ApiResponse<ProductResponseDto> updateProduct(ProductRequestDto productRequestDto, String token);

}
