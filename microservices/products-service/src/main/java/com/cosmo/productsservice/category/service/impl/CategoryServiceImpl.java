package com.cosmo.productsservice.category.service.impl;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.productsservice.category.entity.ProductCategory;
import com.cosmo.productsservice.category.mapper.CategoryMapper;
import com.cosmo.productsservice.category.model.CreateCategoryModel;
import com.cosmo.productsservice.category.model.request.DeleteCategoryRequest;
import com.cosmo.productsservice.category.model.request.UpdateCategoryRequest;
import com.cosmo.productsservice.category.repo.ProductCategoryRepository;
import com.cosmo.productsservice.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Mono<ApiResponse<?>> createCategory(CreateCategoryModel createCategoryModel) {
        Optional<ProductCategory> existedName = productCategoryRepository.findByName(createCategoryModel.getName());
        if (existedName.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("This Product category already exist"));
        }
        categoryMapper.toEntity(createCategoryModel);
        return Mono.just((ResponseUtil.getSuccessfulApiResponse("Product Category created successfully")));
    }

    @Override
    public Mono<ApiResponse<?>> updateCategory(UpdateCategoryRequest updateCategoryRequest) {
        return null;
    }

    @Override
    public Mono<ApiResponse<?>> deleteCategory(DeleteCategoryRequest deleteCategoryRequest) {
        return null;
    }


}
