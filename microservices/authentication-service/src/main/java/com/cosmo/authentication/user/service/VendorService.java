package com.cosmo.authentication.user.service;

import com.cosmo.authentication.user.model.CreateVendorUserModel;
import com.cosmo.authentication.user.model.PasswordChangeRequest;
import com.cosmo.authentication.user.model.requestDto.DeleteVenderRequest;
import com.cosmo.authentication.user.model.requestDto.UpdateVenderRequest;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface VendorService {
//    ApiResponse<VendorUserResponseDto> addVendorUser(VendorUserRequestDto vendorUserRequestDto);
//    ApiResponse<VendorUserResponseDto> deleteVendorUser(VendorUserRequestDto vendorUserRequestDto);
//    ApiResponse<VendorUserResponseDto> updateVendorUser(VendorUserRequestDto vendorUserRequestDto);
//    ApiResponse<?> getAllVendorUsers(SearchParam searchParam);
    Mono<ApiResponse<?>> getallVendorUserDetail(SearchParam searchParam);
    Mono<ApiResponse<?>> changePassword(PasswordChangeRequest passwordChangeRequest, Principal connectedUser);
    Mono<ApiResponse<?>> updateVenderUser(UpdateVenderRequest updateVenderRequest, Principal connectedUser);
    Mono<ApiResponse<?>> deleteVenderUser(DeleteVenderRequest deleteVenderRequest, Principal connectedUser);
    Mono<ApiResponse<?>> getVendorUserDetail(String token);
    Mono<ApiResponse> createVendorUser(CreateVendorUserModel createVendorUserModel, Principal connectedUser);
}
