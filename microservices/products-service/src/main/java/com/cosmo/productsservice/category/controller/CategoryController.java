package com.cosmo.productsservice.category.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.productsservice.category.model.CreateCategoryModel;
import com.cosmo.productsservice.category.model.request.DeleteCategoryRequest;
import com.cosmo.productsservice.category.model.request.UpdateCategoryRequest;
import com.cosmo.productsservice.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(ApiConstant.PRODUCT_CATEGORY)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse<?>> add(@RequestBody CreateCategoryModel createCategoryModel) {
        return categoryService.createCategory(createCategoryModel);
    }
    @PostMapping(ApiConstant.EDIT)
    public Mono<ApiResponse<?>> edit(@RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {
        return categoryService.updateCategory(updateCategoryRequest);
    }
    @PostMapping(ApiConstant.DELETE)
    public Mono<ApiResponse<?>> delete(@RequestBody @Valid DeleteCategoryRequest deleteCategoryRequest){
        return categoryService.deleteCategory(deleteCategoryRequest);
    }
    @PostMapping()
    public Mono<ApiResponse<?>> getAllCategory(@RequestBody @Valid SearchParam searchParam){
        return categoryService.getAllCategory(searchParam);
    }
}
