package com.cosmo.authentication.user.controller;

import com.cosmo.authentication.user.model.PasswordChangeRequest;
import com.cosmo.authentication.user.model.requestDto.DeleteVendorRequest;
import com.cosmo.authentication.user.model.requestDto.UpdateVendorRequest;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.authentication.user.service.VendorUserService;
import com.cosmo.authentication.user.model.CreateVendorUserModel;
import com.cosmo.common.model.SearchParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.VENDOR_USER)
@RequiredArgsConstructor
public class VendorUserController {
    private final VendorUserService vendorUserService;

    @PostMapping(ApiConstant.EDIT)
    public Mono<ApiResponse<?>> edit(@RequestBody @Valid UpdateVendorRequest updateVendorRequest, Principal connectedUser) {
        return vendorUserService.updateVendorUser(updateVendorRequest, connectedUser);
    }


    @PostMapping(ApiConstant.DELETE)
    public Mono<ApiResponse<?>> deleteVendorUser(@RequestBody @Valid DeleteVendorRequest deleteVendorRequest,
                                                 Principal connectedUser) {
        return vendorUserService.deleteVendorUser(deleteVendorRequest, connectedUser);
    }


    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> addVendorUser(@RequestBody @Valid CreateVendorUserModel createVendorUserModel
            , Principal connectedUser) {
        return vendorUserService.createVendorUser(createVendorUserModel, connectedUser);
    }

    @GetMapping(ApiConstant.GET + ApiConstant.SLASH + ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> getVendorUserDetails(
            @RequestHeader("Authorization") String token) {
        return vendorUserService.getVendorUserDetail(token);
    }

    @PostMapping(ApiConstant.ALL_VENDOR_USERS)
    public Mono<ApiResponse<?>> getAllVendorUserDetail(@RequestBody @Valid SearchParam searchParam) {
        return vendorUserService.getallVendorUserDetail(searchParam);
    }

    @PostMapping(ApiConstant.CHANGE_PASSWORD)
    public Mono<ApiResponse<?>> changePassword(
            @RequestBody @Valid PasswordChangeRequest passwordChangeRequest,
            Principal connectedUser) {
        return vendorUserService.changePassword(passwordChangeRequest, connectedUser);
    }

}
