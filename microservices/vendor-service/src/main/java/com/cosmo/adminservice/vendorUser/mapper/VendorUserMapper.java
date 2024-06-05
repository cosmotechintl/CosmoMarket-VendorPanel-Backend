package com.cosmo.adminservice.vendorUser.mapper;

import com.cosmo.adminservice.vendorUser.model.VendorUserRequestDto;
import com.cosmo.adminservice.vendorUser.model.VendorUserResponseDto;
import com.cosmo.authentication.user.entity.Vendor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendorUserMapper {
    Vendor dtoToEntity(VendorUserRequestDto vendorUserRequestDto);
    VendorUserResponseDto entityToDto(Vendor vendor);
    Vendor updateEntityFromDto(VendorUserRequestDto vendorUserRequestDto, Vendor existingVendor);


}