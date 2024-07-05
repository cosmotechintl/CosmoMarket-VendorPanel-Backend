package com.cosmo.vendorservice.services.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;


public interface ServicesService {
    Mono<ApiResponse<?>> getAll(SearchParam searchParam);
}
