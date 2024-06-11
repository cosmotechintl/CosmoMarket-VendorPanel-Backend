package com.cosmo.vendorservice.vendorUser.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.vendorUser.Service.VendorService;
import com.cosmo.vendorservice.vendorUser.model.CreateVendorUserModel;
import com.cosmo.vendorservice.vendorUser.model.requestDto.FetchVendorUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.VENDOR_USER)
@RequiredArgsConstructor
public class VendorController {
    private final VendorService vendorService;

//    @PostMapping(ApiConstant.CREATE)
//    public ResponseEntity<ApiResponse<VendorUserResponseDto>> addVendorUser(@Valid @RequestBody VendorUserRequestDto vendorUserRequestDto) {
//        return ResponseEntity.ok(vendorService.addVendorUser(vendorUserRequestDto));
//    }
//
//    @PostMapping(ApiConstant.DELETE)
//    public ResponseEntity<ApiResponse<VendorUserResponseDto>> deleteVendorUser(@RequestBody VendorUserRequestDto vendorUserRequestDto) {
//        return ResponseEntity.ok(vendorService.deleteVendorUser(vendorUserRequestDto));
//    }
//
//    @GetMapping(ApiConstant.GET)
//    public ResponseEntity<ApiResponse<?>> getAllVendorUsers(@RequestBody SearchParam searchParam) {
//        return ResponseEntity.ok().body(vendorService.getAllVendorUsers(searchParam));
//    }
//
//    @PostMapping(ApiConstant.MODIFY)
//    public ResponseEntity<ApiResponse<VendorUserResponseDto>> updateVendorUser(@Valid @RequestBody VendorUserRequestDto vendorUserRequestDto) {
//        return ResponseEntity.ok(vendorService.updateVendorUser(vendorUserRequestDto));
//    }

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> addAccessGroup(@RequestBody @Valid CreateVendorUserModel createVendorUseerModel) {
        return vendorService.createVendorUser(createVendorUseerModel);
    }


    @GetMapping(ApiConstant.GET + ApiConstant.SLASH + ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> getVendorUserDetails(
            @RequestHeader("Authorization") String token){
        return vendorService.getVendorUserDetail(token);
    }

//    @GetMapping(ApiConstant.GET + ApiConstant.SLASH + ApiConstant.DETAIL)
//    public Mono<ApiResponse<?>> getAccessGroupDetail(@RequestBody @Valid FetchAccessGroupDetail fetchAccessGroupDetail) {
//        return accessGroupService.getAccessGroupDetail(fetchAccessGroupDetail);
//    }
}
