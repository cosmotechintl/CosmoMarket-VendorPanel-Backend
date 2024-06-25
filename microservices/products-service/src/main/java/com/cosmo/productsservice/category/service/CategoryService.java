package com.cosmo.productsservice.category.service;


import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.productsservice.category.model.CreateCategoryModel;
import com.cosmo.productsservice.category.model.request.DeleteCategoryRequest;
import com.cosmo.productsservice.category.model.request.UpdateCategoryRequest;
import reactor.core.publisher.Mono;

public interface CategoryService {
    Mono<ApiResponse<?>> createCategory(CreateCategoryModel createCategoryModel);
    Mono<ApiResponse<?>> updateCategory(UpdateCategoryRequest updateCategoryRequest);
    Mono<ApiResponse<?>> deleteCategory(DeleteCategoryRequest deleteCategoryRequest);
    Mono<ApiResponse<?>> getAllCategory(SearchParam searchParam);
}
