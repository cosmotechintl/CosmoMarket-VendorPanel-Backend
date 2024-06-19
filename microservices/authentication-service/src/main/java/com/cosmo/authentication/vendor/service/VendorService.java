package com.cosmo.authentication.vendor.service;


import com.cosmo.authentication.vendor.model.request.BlockVendorRequest;
import com.cosmo.authentication.vendor.model.request.DeleteVendorRequest;
import com.cosmo.authentication.vendor.model.request.UnblockVendorRequest;
import com.cosmo.authentication.vendor.model.request.UpdateVendorDetailRequest;
import com.cosmo.authentication.vendor.model.CreateVendorModel;
import com.cosmo.authentication.vendor.model.FetchVendorDetail;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;

public interface VendorService {
    Mono<ApiResponse> createVendor(CreateVendorModel createVendorModel);
    Mono<ApiResponse<?>> getAllVendors(SearchParam searchParam);
    Mono<ApiResponse<?>> getVendorDetails(FetchVendorDetail fetchVendorDetail);
    Mono<ApiResponse<?>> deleteVendor(DeleteVendorRequest deleteVendorRequest);
    Mono<ApiResponse<?>> blockVendor(BlockVendorRequest blockVendorRequest);
    Mono<ApiResponse<?>> unblockVendor(UnblockVendorRequest unblockVendorRequest);
    Mono<ApiResponse<?>> updateVendor(UpdateVendorDetailRequest updateVendorDetailRequest);
}