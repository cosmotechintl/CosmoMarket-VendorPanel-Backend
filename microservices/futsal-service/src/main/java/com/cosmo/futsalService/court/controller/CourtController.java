package com.cosmo.futsalService.court.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.futsalService.court.model.FetchCourtByVendorCode;
import com.cosmo.futsalService.court.service.CourtService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(ApiConstant.COURT)
@RequiredArgsConstructor
public class CourtController {
    private final CourtService courtService;

    @PostMapping(ApiConstant.GET_BY_CODE)
    public Mono<ApiResponse<?>> getCourtDetails(@RequestBody @Valid SearchParam searchParam,FetchCourtByVendorCode fetchCourtByVendorCode) {
        return courtService.getCourtByVendorCode(searchParam, fetchCourtByVendorCode);
    }
}
