package com.cosmo.authentication.user.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.authentication.user.model.CreateVendorUserModel;

import reactor.core.publisher.Mono;

import java.security.Principal;

public interface VendorService {
//    ApiResponse<VendorUserResponseDto> addVendorUser(VendorUserRequestDto vendorUserRequestDto);
//    ApiResponse<VendorUserResponseDto> deleteVendorUser(VendorUserRequestDto vendorUserRequestDto);
//    ApiResponse<VendorUserResponseDto> updateVendorUser(VendorUserRequestDto vendorUserRequestDto);
//    ApiResponse<?> getAllVendorUsers(SearchParam searchParam);

    Mono<ApiResponse> createVendorUser(CreateVendorUserModel createVendorUserModel, Principal connectedUser);
    Mono<ApiResponse<?>> getVendorUserDetail(String token);
}
