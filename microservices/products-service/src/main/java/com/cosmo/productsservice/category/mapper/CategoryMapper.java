package com.cosmo.productsservice.category.mapper;

import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.productsservice.category.entity.ProductCategory;
import com.cosmo.productsservice.category.model.CategoryDetailsDto;
import com.cosmo.productsservice.category.model.CreateCategoryModel;
import com.cosmo.productsservice.category.model.request.SearchCategoryResponse;
import com.cosmo.productsservice.category.model.request.UpdateCategoryRequest;
import com.cosmo.productsservice.category.repo.ProductCategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CategoryMapper {
    @Autowired
    protected StatusRepository statusRepository;

    @Autowired
    protected ProductCategoryRepository productCategoryRepository;

    public abstract CategoryDetailsDto getCategoryDetails(ProductCategory category);

    public abstract SearchCategoryResponse entityToRes(ProductCategory productCategory);

    public List<SearchCategoryResponse> getCategoryResponses(List<ProductCategory> category) {
        return category.stream().map(this::entityToRes).collect(Collectors.toList());
    }

    public ProductCategory toEntity(CreateCategoryModel model) {
        ProductCategory categoryDetails = new ProductCategory();
        categoryDetails.setName(model.getName());
        categoryDetails.setDescription(model.getDescription());
        categoryDetails.setCode(UUID.randomUUID().toString());
        categoryDetails.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
        return productCategoryRepository.save(categoryDetails);
    }

    public ProductCategory updateEntity(UpdateCategoryRequest model, ProductCategory entity) {
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        return productCategoryRepository.save(entity);
    }

}
