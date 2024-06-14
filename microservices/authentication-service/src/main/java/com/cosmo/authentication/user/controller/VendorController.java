package com.cosmo.authentication.user.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.authentication.user.service.VendorService;
import com.cosmo.authentication.user.model.CreateVendorUserModel;
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
}
