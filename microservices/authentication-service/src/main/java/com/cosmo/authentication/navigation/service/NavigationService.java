package com.cosmo.authentication.navigation.service;

import com.cosmo.common.model.ApiResponse;
import reactor.core.publisher.Mono;

public interface NavigationService {

    Mono<ApiResponse> getAllNavigation();

}
