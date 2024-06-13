package com.cosmo.authentication.accessgroup.mapper;

import com.cosmo.authentication.accessgroup.entity.AccessGroup;
import com.cosmo.authentication.accessgroup.entity.AccessGroupRoleMap;
import com.cosmo.authentication.accessgroup.model.AccessGroupRoleMapDto;
import com.cosmo.authentication.accessgroup.model.AssignRoleModel;
import com.cosmo.authentication.accessgroup.repo.AccessGroupRoleMapRepository;
import com.cosmo.authentication.role.entity.Roles;
import com.cosmo.authentication.role.service.RolesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;

@Slf4j
@RequiredArgsConstructor
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AccessGroupRoleMapMapper {

    @Autowired
    protected RolesService rolesService;

    @Autowired
    AccessGroupRoleMapRepository accessGroupRoleMapRepository;

    public List<AccessGroupRoleMap> createAccessGroupRoleMap(AccessGroup accessGroup, List<AssignRoleModel> roles) {
        List<Long> assignedRoleId = roles.stream().map(AssignRoleModel::getRoleId).toList();
        log.info("Assigned Role IDs: {}", assignedRoleId);

        List<Roles> allRoles = rolesService.getAllRoles();
        log.info("All Roles: {}", allRoles);

        List<AccessGroupRoleMap> accessGroupRoleMaps = allRoles.stream().map(role -> {
            AccessGroupRoleMap accessGroupRoleMap = new AccessGroupRoleMap();
            accessGroupRoleMap.setAccessGroup(accessGroup);
            accessGroupRoleMap.setIsActive(assignedRoleId.contains(role.getId()));
            accessGroupRoleMap.setRoles(role);
            return accessGroupRoleMap;
        }).collect(Collectors.toList());
        return accessGroupRoleMapRepository.saveAll(accessGroupRoleMaps);
    }

    public List<AccessGroupRoleMap> updateAccessGroupRoleMap(AccessGroup accessGroup, List<AssignRoleModel> roles) {
        List<Long> assignedRoleId = roles.stream().map(AssignRoleModel::getRoleId).toList();
        log.info("Assigned Role IDs: {}", assignedRoleId);

        List<Roles> allRoles = rolesService.getAllRoles();
        log.info("All Roles: {}", allRoles);

        List<AccessGroupRoleMap> accessGroupRoleMaps = allRoles.stream().map(role -> {
            AccessGroupRoleMap accessGroupRoleMap = accessGroupRoleMapRepository.findByAccessGroupAndRoles(accessGroup, role);
            if (accessGroupRoleMap == null) {
                accessGroupRoleMap = new AccessGroupRoleMap();
                accessGroupRoleMap.setAccessGroup(accessGroup);
                accessGroupRoleMap.setRoles(role);
            }
            accessGroupRoleMap.setIsActive(assignedRoleId.contains(role.getId()));
            return accessGroupRoleMap;
        }).collect(Collectors.toList());
        return accessGroupRoleMapRepository.saveAll(accessGroupRoleMaps);
    }

    public abstract AccessGroupRoleMap toEntity(AccessGroupRoleMapDto accessGroupRoleMapDto);

    public abstract AccessGroupRoleMapDto toDto(AccessGroupRoleMap accessGroupRoleMap);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract AccessGroupRoleMap partialUpdate(AccessGroupRoleMapDto accessGroupRoleMapDto, @MappingTarget AccessGroupRoleMap accessGroupRoleMap);
}