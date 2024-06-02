package com.cosmo.adminservice.accessGroup.service;

import com.cosmo.adminservice.accessGroup.model.AccessGroupModel;
import com.cosmo.adminservice.accessGroup.model.AccessGroupResponse;
import com.cosmo.authentication.role.entity.AccessGroup;

public interface AccessGroupService {
    public AccessGroupResponse createAccessGroup(AccessGroupModel accessGroupModel);

}
