package com.cosmo.authentication.vendor.service;

import com.cosmo.authentication.vendor.model.VendorCategoryCreateModel;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;

public interface VendorCategoryService {
    Mono<ApiResponse<?>> getCategories(SearchParam searchParam);
    Mono<ApiResponse> createVendorCategory(VendorCategoryCreateModel createModel);
}
