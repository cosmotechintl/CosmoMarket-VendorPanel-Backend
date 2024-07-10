package com.cosmo.authentication.vendor.mapper;

import com.cosmo.authentication.vendor.entity.VendorCategory;
import com.cosmo.authentication.vendor.model.VendorCategoryCreateModel;
import com.cosmo.authentication.vendor.model.VendorCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class VendorCategoryMapper {

    public abstract VendorCategoryDto entityToRes(VendorCategory vendorCategory);
    public List<VendorCategoryDto> getCategoryResponse(List<VendorCategory> categories){
        return categories.stream().map(this::entityToRes).collect(toList());
    }
    public abstract VendorCategory mapToEntity(VendorCategoryCreateModel createModel);

}
