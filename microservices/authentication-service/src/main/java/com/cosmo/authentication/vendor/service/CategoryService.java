package com.cosmo.authentication.vendor.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;

public interface CategoryService {
    Mono<ApiResponse<?>> getCategories(SearchParam searchParam);
}
