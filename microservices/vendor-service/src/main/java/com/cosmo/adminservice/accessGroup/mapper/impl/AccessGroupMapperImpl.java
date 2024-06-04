package com.cosmo.adminservice.accessGroup.mapper.impl;

import com.cosmo.adminservice.accessGroup.mapper.AccessGroupMapper;
import com.cosmo.adminservice.accessGroup.model.AccessGroupModel;
import com.cosmo.adminservice.accessGroup.model.AccessGroupResponse;
import com.cosmo.authentication.role.entity.AccessGroup;
import com.cosmo.authentication.role.entity.AccessGroupRoleMap;
import com.cosmo.authentication.role.entity.Roles;
import com.cosmo.common.entity.Status;
import com.cosmo.common.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccessGroupMapperImpl implements AccessGroupMapper {
    private final StatusRepository statusRepository;

    @Override
    public AccessGroup dtoToEntity(AccessGroupModel accessGroupModel) {
        if (accessGroupModel == null) {
            return null;
        }

        AccessGroup accessGroup = new AccessGroup();
        accessGroup.setName(accessGroupModel.getName());
        accessGroup.setDescription(accessGroupModel.getDescription());
        accessGroup.setCreatedAt(accessGroupModel.getCreatedAt());
        accessGroup.setUpdatedAt(accessGroupModel.getUpdatedAt());
        accessGroup.setSuperAdminGroup(accessGroupModel.getIsSuperAdminGroup());
        accessGroup.setRemarks(accessGroupModel.getRemarks());
        if (accessGroupModel.getStatusId() != null) {
            Status status = statusRepository.findById(accessGroupModel.getStatusId())
                    .orElseThrow(() -> new IllegalArgumentException("Status with id " + accessGroupModel.getStatusId() + " does not exist"));
            accessGroup.setStatus(status);
        } else {
            throw new IllegalArgumentException("StatusId cannot be null");
        }

        return accessGroup;
    }

    public AccessGroupResponse entityToDto(AccessGroup accessGroup) {
        AccessGroupResponse response = new AccessGroupResponse();
        response.setName(accessGroup.getName());
        response.setDescription(accessGroup.getDescription());
        response.setCreatedAt(accessGroup.getCreatedAt());
        response.setUpdatedAt(accessGroup.getUpdatedAt());
        response.setStatusName(accessGroup.getStatus().getName());
        response.setIsSuperAdminGroup(accessGroup.isSuperAdminGroup());
        response.setRemarks(accessGroup.getRemarks());
        response.setRoleNames(accessGroup.getAccessGroupRoleMaps().stream()
                .map(accessGroupRoleMap -> accessGroupRoleMap.getRoles().getName())
                .collect(Collectors.toList()));
        return response;
    }



}
