package com.cosmo.vendorservice.vendorBusinesshour.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.vendorBusinesshour.model.SetBusinessHour;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;

public interface BusinessHourService {
    Mono<ApiResponse<?>> addBusinessHour(List<SetBusinessHour> setBusinessHours, Principal connectedUser);
}
