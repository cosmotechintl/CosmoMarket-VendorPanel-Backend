package com.cosmo.authentication.user.controller;

import com.cosmo.authentication.user.model.PasswordChangeRequest;
import com.cosmo.authentication.user.model.requestDto.DeleteVenderRequest;
import com.cosmo.authentication.user.model.requestDto.UpdateVenderRequest;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.authentication.user.service.VendorService;
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
public class VendorController {
    private final VendorService vendorService;

    @PostMapping(ApiConstant.EDIT)
    public Mono<ApiResponse<?>> edit(@RequestBody @Valid UpdateVenderRequest updateVenderRequest, Principal connectedUser){
        return vendorService.updateVenderUser(updateVenderRequest,connectedUser);
    }



    @PostMapping(ApiConstant.DELETE)
    public Mono<ApiResponse<?>> deleteVenderUser(@RequestBody @Valid DeleteVenderRequest deleteVenderRequest,
                                                 Principal connectedUser)
        {
            return vendorService.deleteVenderUser(deleteVenderRequest,connectedUser);
        }


    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> addVendorUser(@RequestBody @Valid CreateVendorUserModel createVendorUseerModel
            , Principal connectedUser) {
        return vendorService.createVendorUser(createVendorUseerModel, connectedUser);
    }

    @GetMapping(ApiConstant.GET + ApiConstant.SLASH + ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> getVendorUserDetails(
            @RequestHeader("Authorization") String token){
        return vendorService.getVendorUserDetail(token);
    }
    @GetMapping(ApiConstant.ALL_VENDOR_USERS)
    public Mono<ApiResponse<?>> getallVendorUserDetail(@RequestBody @Valid SearchParam searchParam) {
        return vendorService.getallVendorUserDetail(searchParam);
    }
    @PostMapping(ApiConstant.CHANGE_PASSWORD)
    public Mono<ApiResponse<?>>changePassword(
            @RequestBody @Valid PasswordChangeRequest passwordChangeRequest,
            Principal connectedUser)
    {
        return vendorService.changePassword(passwordChangeRequest,connectedUser);
    }

}
