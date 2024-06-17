package com.cosmo.authentication.accessGroup.service;

import com.cosmo.authentication.accessGroup.model.CreateAccessGroupModel;
import com.cosmo.authentication.accessGroup.model.UpdateAccessGroupModel;
import com.cosmo.authentication.accessGroup.model.request.DeleteAccessGroupRequest;
import com.cosmo.authentication.accessGroup.model.request.FetchAccessGroupDetail;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;

public interface AccessGroupService {
        Mono<ApiResponse> createAccessGroup(CreateAccessGroupModel createAccessGroupModel);
        Mono<ApiResponse> updateAccessGroup(UpdateAccessGroupModel updateAccessGroupModel);
        Mono<ApiResponse >deleteAccessGroup(DeleteAccessGroupRequest deleteAccessGroupRequest);
        Mono<ApiResponse<?>> getAllAccessGroup(SearchParam searchParam);
        Mono<ApiResponse<?>> getAccessGroupDetail(FetchAccessGroupDetail fetchAccessGroupDetail);
}
