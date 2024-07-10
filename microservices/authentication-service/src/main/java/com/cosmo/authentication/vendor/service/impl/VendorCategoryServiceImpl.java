package com.cosmo.authentication.vendor.service.impl;

import com.cosmo.authentication.vendor.entity.VendorCategory;
import com.cosmo.authentication.vendor.mapper.VendorCategoryMapper;
import com.cosmo.authentication.vendor.model.VendorCategoryDto;
import com.cosmo.authentication.vendor.model.VendorCategoryCreateModel;
import com.cosmo.authentication.vendor.repository.VendorCategoryRepository;
import com.cosmo.authentication.vendor.repository.VendorCategorySearchRepository;
import com.cosmo.authentication.vendor.service.VendorCategoryService;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class VendorCategoryServiceImpl implements VendorCategoryService {
    private final VendorCategorySearchRepository vendorCategorySearchRepository;
    private final VendorCategoryRepository vendorCategoryRepository;
    private final VendorCategoryMapper vendorCategoryMapper;
    private final SearchResponse searchResponse;

    @Override
    public Mono<ApiResponse<?>> getCategories(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<VendorCategory, VendorCategoryDto> responseBuilder = SearchResponseWithMapperBuilder.<VendorCategory, VendorCategoryDto>builder()
                .count(vendorCategorySearchRepository::count).searchData(vendorCategorySearchRepository::getAll)
                .mapperFunction(this.vendorCategoryMapper::getCategoryResponse).searchParam(searchParam)
                .build();
        PageableResponse<VendorCategoryDto> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Vendor categories fetched successfully"));
    }

    @Override
    public Mono<ApiResponse> createVendorCategory(VendorCategoryCreateModel createModel) {
       VendorCategory vendorCategory = vendorCategoryMapper.mapToEntity(createModel);
         vendorCategoryRepository.save(vendorCategory);
         return Mono.just(ResponseUtil.getSuccessfulApiResponse("Vendor category created successfully"));
    }

}
