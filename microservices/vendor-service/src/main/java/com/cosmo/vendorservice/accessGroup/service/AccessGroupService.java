package com.cosmo.vendorservice.accessGroup.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.accessGroup.model.AccessGroupModel;
import com.cosmo.vendorservice.accessGroup.model.AccessGroupResponse;

public interface AccessGroupService {
    ApiResponse<AccessGroupResponse> createAccessGroup(AccessGroupModel accessGroupModel);

}
