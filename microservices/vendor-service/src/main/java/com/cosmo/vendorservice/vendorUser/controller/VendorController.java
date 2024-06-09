package com.cosmo.vendorservice.vendorUser.controller;

import com.cosmo.vendorservice.vendorUser.Service.VendorService;
import com.cosmo.vendorservice.vendorUser.model.VendorUserRequestDto;
import com.cosmo.vendorservice.vendorUser.model.VendorUserResponseDto;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.VENDOR_USER)
@RequiredArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @PostMapping(ApiConstant.CREATE)
    public ResponseEntity<ApiResponse<VendorUserResponseDto>> addVendorUser(@Valid @RequestBody VendorUserRequestDto vendorUserRequestDto) {
        return ResponseEntity.ok(vendorService.addVendorUser(vendorUserRequestDto));
    }

    @PostMapping(ApiConstant.DELETE)
    public ResponseEntity<ApiResponse<VendorUserResponseDto>> deleteVendorUser(@RequestBody VendorUserRequestDto vendorUserRequestDto) {
        return ResponseEntity.ok(vendorService.deleteVendorUser(vendorUserRequestDto));
    }

    @GetMapping(ApiConstant.GET)
    public ResponseEntity<ApiResponse<?>> getAllVendorUsers(@RequestBody SearchParam searchParam) {
        return ResponseEntity.ok().body(vendorService.getAllVendorUsers(searchParam));
    }

    @PostMapping(ApiConstant.MODIFY)
    public ResponseEntity<ApiResponse<VendorUserResponseDto>> updateVendorUser(@Valid @RequestBody VendorUserRequestDto vendorUserRequestDto) {
        return ResponseEntity.ok(vendorService.updateVendorUser(vendorUserRequestDto));
    }

    @GetMapping(ApiConstant.VENDOR_DETAILS)
    public ResponseEntity<ApiResponse<?>> getVendorByUsername(@RequestBody VendorUserRequestDto vendorUserRequestDto){
        return ResponseEntity.ok(vendorService.getVendorByUsername(vendorUserRequestDto));
    }
}
