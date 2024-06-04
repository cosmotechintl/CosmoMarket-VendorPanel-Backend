package com.cosmo.adminservice.accessGroup.service.impl;

import com.cosmo.adminservice.accessGroup.mapper.AccessGroupMapper;
import com.cosmo.adminservice.accessGroup.model.AccessGroupModel;
import com.cosmo.adminservice.accessGroup.model.AccessGroupResponse;
import com.cosmo.adminservice.accessGroup.service.AccessGroupService;
import com.cosmo.authentication.role.entity.AccessGroup;
import com.cosmo.authentication.role.entity.AccessGroupRoleMap;
import com.cosmo.authentication.role.entity.Roles;
import com.cosmo.authentication.role.repo.AccessGroupRepository;
import com.cosmo.authentication.role.repo.RoleRepository;
import com.cosmo.common.entity.Status;
import com.cosmo.common.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessGroupServiceImpl implements AccessGroupService {
    private final AccessGroupRepository accessGroupRepository;
    private final AccessGroupMapper accessGroupMapper;
    private final StatusRepository statusRepository;
    private final RoleRepository roleRepository;

    @Override
    public AccessGroupResponse createAccessGroup(AccessGroupModel accessGroupModel) {
        AccessGroup accessGroup = accessGroupMapper.dtoToEntity(accessGroupModel);
        Status status = statusRepository.findById(accessGroupModel.getStatus().getId()).orElse(null);
        accessGroup.setStatus(status);
        List<AccessGroupRoleMap> accessGroupRoleMaps = new ArrayList<>();
        for(Long roleId : accessGroupModel.getRoleIds()) {
            Roles role = roleRepository.findById(roleId).orElse(null);
            AccessGroupRoleMap roleMap = new AccessGroupRoleMap();
            roleMap.setRoles(role);
            roleMap.setAccessGroup(accessGroup);
            roleMap.setIsActive(true); // Set default value for isActive
            accessGroupRoleMaps.add(roleMap);
        }
        accessGroup.setAccessGroupRoleMaps(accessGroupRoleMaps);
        AccessGroup savedAccessGroup = accessGroupRepository.save(accessGroup);
        return accessGroupMapper.entityToDto(savedAccessGroup); // Use the entityToDto method that returns an AccessGroupResponse
    }
}
