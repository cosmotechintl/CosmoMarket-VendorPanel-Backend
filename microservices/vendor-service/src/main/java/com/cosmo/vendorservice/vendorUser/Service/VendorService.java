package com.cosmo.vendorservice.vendorUser.Service;

import com.cosmo.vendorservice.vendorUser.model.VendorUserRequestDto;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.vendorservice.vendorUser.model.VendorUserResponseDto;

public interface VendorService {
    ApiResponse<VendorUserResponseDto> addVendorUser(VendorUserRequestDto vendorUserRequestDto);
    ApiResponse<VendorUserResponseDto> deleteVendorUser(VendorUserRequestDto vendorUserRequestDto);
    ApiResponse<VendorUserResponseDto> updateVendorUser(VendorUserRequestDto vendorUserRequestDto);
    ApiResponse<?> getAllVendorUsers(SearchParam searchParam);


    ApiResponse<?> getVendorByUsername(VendorUserRequestDto vendorUserRequestDto);
}
