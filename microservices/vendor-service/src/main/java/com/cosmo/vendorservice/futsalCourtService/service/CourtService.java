package com.cosmo.vendorservice.futsalCourtService.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.vendorservice.futsalCourtService.model.FetchCourtByVendorCode;
import reactor.core.publisher.Mono;

public interface CourtService {
    Mono<ApiResponse<Object>> getCourtsByVendorCode(SearchParam searchParam,FetchCourtByVendorCode fetchCourtByVendorCode);
}
