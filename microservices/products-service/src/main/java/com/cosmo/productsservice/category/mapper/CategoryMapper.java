package com.cosmo.productsservice.category.mapper;

import com.cosmo.productsservice.category.entity.ProductCategory;
import com.cosmo.productsservice.category.model.CreateCategoryModel;
import com.cosmo.productsservice.category.model.request.UpdateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CategoryMapper {


     public ProductCategory toEntity(CreateCategoryModel model) {
        ProductCategory categoryDetails = new ProductCategory();
        categoryDetails.setName(model.getName());
        categoryDetails.setDescription(model.getDescription());
        return categoryDetails;
    }

    public static void updateEntity(ProductCategory entity, UpdateCategoryRequest model) {
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());

    }
}
