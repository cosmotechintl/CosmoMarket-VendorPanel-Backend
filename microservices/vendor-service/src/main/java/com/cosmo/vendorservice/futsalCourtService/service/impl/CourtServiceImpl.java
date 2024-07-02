package com.cosmo.vendorservice.futsalCourtService.service.impl;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.constant.ServiceConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;

import com.cosmo.vendorservice.config.AbstractConnectorService;
import com.cosmo.vendorservice.config.ConnectorService;
import com.cosmo.vendorservice.config.PropertiesFileValue;
import com.cosmo.vendorservice.futsalCourtService.model.FetchCourtByVendorCode;
import com.cosmo.vendorservice.futsalCourtService.service.CourtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Qualifier(ServiceConstant.COURT_SERVICE)
@RequiredArgsConstructor
@Service
@Slf4j
public class CourtServiceImpl extends AbstractConnectorService implements CourtService, ConnectorService {
    private final PropertiesFileValue propertiesFileValue;

    @Override
    protected String getBaseUrl() {
        return propertiesFileValue.getFutsalServiceUrl();
    }
    @Override
    public Mono<ApiResponse<Object>> getCourtsByVendorCode(SearchParam searchParam, FetchCourtByVendorCode fetchCourtByVendorCode) {
        log.info("Vendor Code :::{}", fetchCourtByVendorCode);
        Map<String, Object> payload = new HashMap<>();
        payload.put("searchParam", searchParam);
        payload.put("fetchCourtByVendorCode", fetchCourtByVendorCode);
        return connectToService(payload,
                ApiConstant.COURT+ApiConstant.SLASH+ApiConstant.GET_BY_CODE,
                new ParameterizedTypeReference<>() {
                });
    }
}
