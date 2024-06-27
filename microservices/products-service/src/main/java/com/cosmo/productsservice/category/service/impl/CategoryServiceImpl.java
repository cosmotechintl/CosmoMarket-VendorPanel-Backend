package com.cosmo.productsservice.category.service.impl;

import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.authentication.vendor.model.VendorDetailDto;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.productsservice.category.model.CategoryDetailsDto;
import com.cosmo.productsservice.category.model.FetchCategoryDetails;
import com.cosmo.productsservice.category.model.request.SearchCategoryResponse;
import com.cosmo.productsservice.category.model.request.SearchCategoryResponse;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.productsservice.category.entity.ProductCategory;
import com.cosmo.productsservice.category.mapper.CategoryMapper;
import com.cosmo.productsservice.category.model.CreateCategoryModel;
import com.cosmo.productsservice.category.model.request.DeleteCategoryRequest;
import com.cosmo.productsservice.category.model.request.UpdateCategoryRequest;
import com.cosmo.productsservice.category.repo.ProductCategoryRepository;
import com.cosmo.productsservice.category.repo.ProductCategorySearchRepository;
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
    private final StatusRepository statusRepository;
    private final ProductCategorySearchRepository productCategorySearchRepository;
    private final SearchResponse searchResponse;


    @Override
    public Mono<ApiResponse<?>> createCategory(CreateCategoryModel createCategoryModel) {
        Optional<ProductCategory> existedName = productCategoryRepository.findByName(createCategoryModel.getName());
        if (existedName.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("This Product category already exists"));
        }
        ProductCategory category = categoryMapper.toEntity(createCategoryModel);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product Category created successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> updateCategory(UpdateCategoryRequest updateCategoryRequest) {
        Optional<ProductCategory> category = productCategoryRepository.findByName(updateCategoryRequest.getName());
        if (category.isPresent()) {
            ProductCategory category1 = category.get();
            if (StatusConstant.BLOCKED.getName().equals(category1.getStatus().getName()) ||
                    StatusConstant.DELETED.getName().equals(category1.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Category not found"));
            } else {
                categoryMapper.updateEntity(updateCategoryRequest, category1);
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("Category updated successfully"));
            }
        } else {
            return Mono.just(ResponseUtil.getNotFoundResponse("Category not found"));
        }
    }


    @Override
    public Mono<ApiResponse<?>> deleteCategory(DeleteCategoryRequest deleteCategoryRequest) {
        Optional<ProductCategory> court = productCategoryRepository.findByName(deleteCategoryRequest.getName());
        if (court.isEmpty()) {
            return Mono.just(ResponseUtil.getFailureResponse("Category not found"));
        }else {
            ProductCategory categoryDetails = court.get();
            if (StatusConstant.DELETED.getName().equals(categoryDetails.getStatus().getName()) || StatusConstant.BLOCKED.getName().equals(categoryDetails.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Category not found"));
            } else {
                categoryDetails.setStatus(statusRepository.findByName(StatusConstant.DELETED.getName()));
                productCategoryRepository.save(categoryDetails);
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("category deleted successfully"));
            }
        }
    }

    @Override
    public Mono<ApiResponse<?>> getAllCategory(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<ProductCategory, SearchCategoryResponse> responseBuilder = SearchResponseWithMapperBuilder.<ProductCategory, SearchCategoryResponse>builder()
                .count(productCategorySearchRepository::count).searchData(productCategorySearchRepository::getAll)
                .mapperFunction(this.categoryMapper::getCategoryResponses).searchParam(searchParam)
                .build();
        PageableResponse<SearchCategoryResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Category fetched successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> getCategoryDetails(FetchCategoryDetails fetchCategoryDetails) {
        Optional<ProductCategory> category = productCategoryRepository.findByName(fetchCategoryDetails.getName());
        if (category.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Category not found"));
        } else {
            CategoryDetailsDto categoryDetailsDto = categoryMapper.getCategoryDetails(category.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(categoryDetailsDto, "Category details fetched successfully"));
        }
    }
    }


    








