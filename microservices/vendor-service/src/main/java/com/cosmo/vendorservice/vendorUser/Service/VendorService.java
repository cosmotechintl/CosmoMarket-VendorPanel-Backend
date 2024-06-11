package com.cosmo.vendorservice.vendorUser.Service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.vendorUser.model.CreateVendorUserModel;
import com.cosmo.vendorservice.vendorUser.model.requestDto.FetchVendorUserDetails;
import reactor.core.publisher.Mono;

public interface VendorService {
//    ApiResponse<VendorUserResponseDto> addVendorUser(VendorUserRequestDto vendorUserRequestDto);
//    ApiResponse<VendorUserResponseDto> deleteVendorUser(VendorUserRequestDto vendorUserRequestDto);
//    ApiResponse<VendorUserResponseDto> updateVendorUser(VendorUserRequestDto vendorUserRequestDto);
//    ApiResponse<?> getAllVendorUsers(SearchParam searchParam);

    Mono<ApiResponse> createVendorUser(CreateVendorUserModel createVendorUserModel);
    Mono<ApiResponse<?>> getVendorUserDetail(String token);
}
