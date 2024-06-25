package com.cosmo.authentication.vendor.controller;


import com.cosmo.authentication.vendor.log.model.VendorBlockLogModel;
import com.cosmo.authentication.vendor.log.model.VendorDeleteLogModel;
import com.cosmo.authentication.vendor.model.request.BlockVendorRequest;
import com.cosmo.authentication.vendor.model.request.DeleteVendorRequest;
import com.cosmo.authentication.vendor.model.CreateVendorModel;
import com.cosmo.authentication.vendor.model.FetchVendorDetail;
import com.cosmo.authentication.vendor.model.request.UnblockVendorRequest;
import com.cosmo.authentication.vendor.model.request.UpdateVendorDetailRequest;
import com.cosmo.authentication.vendor.service.VendorService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.VENDOR)
@RequiredArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> createVendor(@RequestBody @Valid CreateVendorModel create){
        return vendorService.createVendor(create);
    }
    @PostMapping()
    public Mono<ApiResponse<?>> getAllVendors(@RequestBody @Valid SearchParam searchParam){
        return vendorService.getAllVendors(searchParam);
    }
    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> getVendorDetails(@RequestBody @Valid FetchVendorDetail fetchVendorDetail){
        return vendorService.getVendorDetails(fetchVendorDetail);
    }
    @PostMapping(ApiConstant.DELETE)
    public Mono<ApiResponse<?>> deleteVendor(@RequestBody @Valid DeleteVendorRequest request, VendorDeleteLogModel logModel){
        return vendorService.deleteVendor(request, logModel);
    }
    @PostMapping(ApiConstant.BLOCK)
    public Mono<ApiResponse<?>> blockVendor(@RequestBody @Valid BlockVendorRequest request, VendorBlockLogModel logModel){
        return vendorService.blockVendor(request, logModel);
    }
    @PostMapping(ApiConstant.UNBLOCK)
    public Mono<ApiResponse<?>> unblockVendor(@RequestBody @Valid UnblockVendorRequest request){
        return vendorService.unblockVendor(request);
    }
    @PostMapping(ApiConstant.UPDATE)
    public Mono<ApiResponse<?>> updateVendor(@RequestBody @Valid UpdateVendorDetailRequest request){
        return vendorService.updateVendor(request);
    }

}
