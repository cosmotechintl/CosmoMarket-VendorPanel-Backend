package com.cosmo.authentication.vendor.mapper;

import com.cosmo.authentication.vendor.entity.Category;
import com.cosmo.authentication.vendor.model.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CategoryMapper {

    public abstract CategoryDto entityToRes(Category category);
    public List<CategoryDto> getCategoryResponse(List<Category> categories){
        return categories.stream().map(this::entityToRes).collect(toList());
    }

}
