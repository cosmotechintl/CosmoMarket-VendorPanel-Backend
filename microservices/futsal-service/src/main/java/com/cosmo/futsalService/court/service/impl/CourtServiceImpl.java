package com.cosmo.futsalService.court.service.impl;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.futsalService.court.entity.Court;
import com.cosmo.futsalService.court.mapper.CourtsMapper;
import com.cosmo.futsalService.court.model.FetchCourtByVendorCode;
import com.cosmo.futsalService.court.model.SearchCourtResponse;
import com.cosmo.futsalService.court.repo.CourtRepository;
import com.cosmo.futsalService.court.repo.CourtSearchRepository;
import com.cosmo.futsalService.court.service.CourtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourtServiceImpl implements CourtService {
    private final CourtRepository courtRepository;
    private final CourtsMapper courtsMapper;
    private final StatusRepository statusRepository;
    private final CourtSearchRepository courtSearchRepository;
    private final SearchResponse searchResponse;

    @Override
    public Mono<ApiResponse<?>> getCourtByVendorCode(SearchParam searchParam, FetchCourtByVendorCode fetchCourtByVendorCode) {
        String vendorCode = fetchCourtByVendorCode.getVendorCode();
        SearchResponseWithMapperBuilder<Court, SearchCourtResponse> responseBuilder = SearchResponseWithMapperBuilder.<Court, SearchCourtResponse>builder()
                .count(courtSearchRepository::count).searchData(courtSearchRepository.findByVendorCode(searchParam, vendorCode))
                .mapperFunction(this.courtsMapper::getCourtResponses).searchParam(searchParam)
                .build();
        PageableResponse<SearchCourtResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Courts fetched successfully"));
    }
}
