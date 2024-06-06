package com.cosmo.vendorservice.vendorUser.mapper;

import com.cosmo.vendorservice.vendorUser.model.VendorUserRequestDto;
import com.cosmo.vendorservice.vendorUser.model.VendorUserResponseDto;
import com.cosmo.authentication.user.entity.Vendor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendorUserMapper {
    Vendor dtoToEntity(VendorUserRequestDto vendorUserRequestDto);
    VendorUserResponseDto entityToDto(Vendor vendor);
    Vendor updateEntityFromDto(VendorUserRequestDto vendorUserRequestDto, Vendor existingVendor);


}