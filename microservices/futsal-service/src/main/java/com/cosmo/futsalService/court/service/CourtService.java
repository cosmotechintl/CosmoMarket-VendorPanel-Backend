package com.cosmo.futsalService.court.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.futsalService.court.model.FetchCourtByVendorCode;
import reactor.core.publisher.Mono;

public interface CourtService {
    Mono<ApiResponse<?>> getCourtByVendorCode(SearchParam searchParam, FetchCourtByVendorCode fetchCourtByVendorCode);

}
