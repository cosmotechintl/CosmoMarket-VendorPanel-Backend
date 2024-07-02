package com.cosmo.vendorservice.futsalCourtService.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.constant.ServiceConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.vendorservice.futsalCourtService.model.FetchCourtByVendorCode;
import com.cosmo.vendorservice.futsalCourtService.service.CourtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ServiceConstant.COURT_SERVICE)
@RequiredArgsConstructor
public class CourtController {
    private final CourtService courtService;
    @PostMapping(ApiConstant.GET_BY_CODE)
    public Mono<ApiResponse<Object>> getCourtByVendorCode(@RequestBody @Valid SearchParam searchParam, FetchCourtByVendorCode fetchCourtByVendorCode){
        return courtService.getCourtsByVendorCode(searchParam, fetchCourtByVendorCode);
    }
}
