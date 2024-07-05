package com.cosmo.authentication.vendor.service.impl;

import com.cosmo.authentication.vendor.entity.Category;
import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.authentication.vendor.mapper.CategoryMapper;
import com.cosmo.authentication.vendor.model.CategoryDto;
import com.cosmo.authentication.vendor.model.SearchVendorResponse;
import com.cosmo.authentication.vendor.repository.CategoryRepository;
import com.cosmo.authentication.vendor.repository.CategorySearchRepository;
import com.cosmo.authentication.vendor.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {
    private final CategorySearchRepository categorySearchRepository;
    private final CategoryMapper categoryMapper;
    private final SearchResponse searchResponse;

    @Override
    public Mono<ApiResponse<?>> getCategories(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<Category, CategoryDto> responseBuilder = SearchResponseWithMapperBuilder.<Category, CategoryDto>builder()
                .count(categorySearchRepository::count).searchData(categorySearchRepository::getAll)
                .mapperFunction(this.categoryMapper::getCategoryResponse).searchParam(searchParam)
                .build();
        PageableResponse<CategoryDto> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Vendor categories fetched successfully"));
    }
}
