package com.cosmo.vendorservice.services.service.impl;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.vendorservice.services.entity.Services_Details;
import com.cosmo.vendorservice.services.mapper.ServicesMapper;
import com.cosmo.vendorservice.services.model.SearchServicesResponse;
import com.cosmo.vendorservice.services.repo.ServicesSearchRepository;
import com.cosmo.vendorservice.services.service.ServicesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
@Slf4j
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {
    private final ServicesSearchRepository servicesSearchRepository;
    private final ServicesMapper servicesMapper;
    private final SearchResponse searchResponse;

    @Override
    public Mono<ApiResponse<?>> getAll(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<Services_Details, SearchServicesResponse> responseBuilder = SearchResponseWithMapperBuilder.<Services_Details, SearchServicesResponse>builder()
                .count(servicesSearchRepository::count).searchData(servicesSearchRepository::getAll)
                .mapperFunction(this.servicesMapper::getServicesResponses).searchParam(searchParam)
                .build();
        PageableResponse<SearchServicesResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response,"List of all services fetched successfully"));
    }
}
