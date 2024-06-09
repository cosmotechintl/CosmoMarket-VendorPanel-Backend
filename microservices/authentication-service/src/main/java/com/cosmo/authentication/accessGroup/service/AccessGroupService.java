package com.cosmo.authentication.accessGroup.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.authentication.accessGroup.model.my.AccessGroupRequestDto;
import com.cosmo.authentication.accessGroup.model.my.AccessGroupListResponseDto;

public interface AccessGroupService {
    ApiResponse<AccessGroupListResponseDto> createAccessGroup(AccessGroupRequestDto accessGroupRequestDto);

    ApiResponse<AccessGroupListResponseDto> updateAccessGroup(AccessGroupRequestDto accessGroupRequestDto);

    ApiResponse<?> getallProducts(SearchParam searchParam);

}
