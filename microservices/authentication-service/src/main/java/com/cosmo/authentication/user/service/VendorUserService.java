package com.cosmo.authentication.user.service;

import com.cosmo.authentication.user.model.PasswordChangeRequest;
import com.cosmo.authentication.user.model.request.VendorUserDetailRequest;
import com.cosmo.authentication.user.model.requestDto.DeleteVendorRequest;
import com.cosmo.authentication.user.model.requestDto.UpdateVendorRequest;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.authentication.user.model.CreateVendorUserModel;

import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface VendorUserService {

    Mono<ApiResponse<?>> getAllVendorUserDetail(SearchParam searchParam);
    Mono<ApiResponse<?>> changePassword(PasswordChangeRequest passwordChangeRequest, Principal connectedUser);
    Mono<ApiResponse<?>> updateVendorUser(UpdateVendorRequest updateVendorRequest, Principal connectedUser);
    Mono<ApiResponse<?>> deleteVendorUser(DeleteVendorRequest deleteVendorRequest, Principal connectedUser);
    Mono<ApiResponse<?>> getVendorUserDetail(Principal connectedUser);
    Mono<ApiResponse> createVendorUser(CreateVendorUserModel createVendorUserModel, Principal connectedUser);
    Mono<ApiResponse<?>> getVendorUserDetails(VendorUserDetailRequest vendorUserDetailRequest);
}
