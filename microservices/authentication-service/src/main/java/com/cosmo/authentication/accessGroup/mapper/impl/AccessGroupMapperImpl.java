package com.cosmo.authentication.accessGroup.mapper.impl;

import com.cosmo.authentication.accessGroup.mapper.AccessGroupMapper;
import com.cosmo.authentication.accessGroup.model.my.AccessGroupListResponseDto;
import com.cosmo.authentication.accessGroup.model.my.AccessGroupRequestDto;
import com.cosmo.common.exception.BadRequestException;
import com.cosmo.authentication.role.entity.AccessGroup;
import com.cosmo.common.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccessGroupMapperImpl implements AccessGroupMapper {
    private final StatusRepository statusRepository;

    @Override
    public AccessGroup dtoToEntity(AccessGroupRequestDto accessGroupRequestDto) {
        if (accessGroupRequestDto == null) {
            return null;
        }

        AccessGroup accessGroup = new AccessGroup();
        accessGroup.setName(accessGroupRequestDto.getName());
        accessGroup.setDescription(accessGroupRequestDto.getDescription());
        accessGroup.setSuperAdminGroup(accessGroupRequestDto.getIsSuperAdminGroup());
        accessGroup.setRemarks(accessGroupRequestDto.getRemarks());
        return accessGroup;
    }

    public AccessGroupListResponseDto entityToDto(AccessGroup accessGroup) {
        AccessGroupListResponseDto response = new AccessGroupListResponseDto();
        response.setName(accessGroup.getName());
        response.setDescription(accessGroup.getDescription());
        response.setStatusName(accessGroup.getStatus().getName());
//        List<Role> role = accessGroup.getAccessGroupRoleMaps().stream()
//                .map(accessGroupRoleMap -> buildRoleTree(accessGroupRoleMap.getRoles()))
//                .collect(Collectors.toList());
//
//        response.setRole(role);
        response.setRoleNames(accessGroup.getAccessGroupRoleMaps().stream()
                .map(accessGroupRoleMap -> accessGroupRoleMap.getRoles().getName())
                .collect(Collectors.toList()));
        return response;
    }

    @Override
    public AccessGroup updateEntityFromDto(AccessGroupRequestDto accessGroupRequestDto, AccessGroup existingAccessGroup) {
        if (accessGroupRequestDto == null) {
            throw new BadRequestException("No access group to update");
        }
        if (existingAccessGroup == null) {
            throw new BadRequestException("Access group does not exist");
        }
        existingAccessGroup.setName(accessGroupRequestDto.getName());
        existingAccessGroup.setDescription(accessGroupRequestDto.getDescription());
        existingAccessGroup.setSuperAdminGroup(accessGroupRequestDto.getIsSuperAdminGroup());
        existingAccessGroup.setRemarks(accessGroupRequestDto.getRemarks());
        return existingAccessGroup;
    }

}
