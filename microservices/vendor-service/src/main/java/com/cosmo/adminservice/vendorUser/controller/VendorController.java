package com.cosmo.adminservice.vendorUser.controller;

import com.cosmo.adminservice.vendorUser.Service.VendorService;
import com.cosmo.adminservice.vendorUser.model.VendorUserRequestDto;
import com.cosmo.adminservice.vendorUser.model.VendorUserResponseDto;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.exception.BadRequestException;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import jakarta.transaction.Transactional;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(ApiConstant.ADD_VENDOR_USER)
    public ResponseEntity<ApiResponse<VendorUserResponseDto>> addVendorUser(@Valid @RequestBody VendorUserRequestDto vendorUserRequestDto) {
        VendorUserResponseDto response = (VendorUserResponseDto) vendorService.addVendorUser(vendorUserRequestDto);
        ApiResponse<VendorUserResponseDto> apiResponse = new ApiResponse<>();
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setMessage("Vendor user added successfully");
        apiResponse.setData(response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping(ApiConstant.DELETE_VENDOR_USER)
    public ResponseEntity<ApiResponse<String>> deleteVendorUser(@RequestBody VendorUserRequestDto vendorUserRequestDto) {
        String username = vendorUserRequestDto.getUsername();
        vendorService.deleteVendorUser(username);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setMessage("Vendor user deleted successfully");
        apiResponse.setData(username);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(ApiConstant.GET_ALL_VENDOR_USERS)
    public ResponseEntity<ApiResponse<?>> getAllVendorUsers(@RequestParam Integer pageNo,
                                                            @RequestParam Integer pageSize) {
        SearchParam searchParam = new SearchParam();
        searchParam.setFirstRow(pageNo);
        searchParam.setPageSize(pageSize);
        return ResponseEntity.ok().body(vendorService.getAllVendorUsers(searchParam));
    }


}
