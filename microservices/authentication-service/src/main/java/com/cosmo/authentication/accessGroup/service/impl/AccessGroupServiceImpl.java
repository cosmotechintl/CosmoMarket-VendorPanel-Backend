package com.cosmo.authentication.accessGroup.service.impl;

import com.cosmo.authentication.accessGroup.model.my.AccessGroupListResponseDto;
import com.cosmo.authentication.accessGroup.model.my.AccessGroupRequestDto;
import com.cosmo.authentication.accessGroup.service.AccessGroupService;
import com.cosmo.common.exception.BadRequestException;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.PaginationUtil;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.authentication.accessGroup.mapper.AccessGroupMapper;
import com.cosmo.authentication.role.entity.AccessGroup;
import com.cosmo.authentication.role.entity.AccessGroupRoleMap;
import com.cosmo.authentication.role.entity.Roles;
import com.cosmo.authentication.role.repo.AccessGroupRepository;
import com.cosmo.authentication.role.repo.RoleRepository;
import com.cosmo.common.repository.StatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccessGroupServiceImpl implements AccessGroupService {
    private final AccessGroupRepository accessGroupRepository;
    private final AccessGroupMapper accessGroupMapper;
    private final StatusRepository statusRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public ApiResponse createAccessGroup(AccessGroupRequestDto accessGroupRequestDto) {
        AccessGroup accessGroup = accessGroupMapper.dtoToEntity(accessGroupRequestDto);
        List<AccessGroupRoleMap> accessGroupRoleMaps = new ArrayList<>();
        for(Long roleId : accessGroupRequestDto.getRoleIds()) {
            Roles role = roleRepository.findById(roleId).orElse(null);
            AccessGroupRoleMap roleMap = new AccessGroupRoleMap();
            roleMap.setRoles(role);
            roleMap.setAccessGroup(accessGroup);
            roleMap.setIsActive(true); // Set default value for isActive
            accessGroupRoleMaps.add(roleMap);
        }
        accessGroup.setAccessGroupRoleMaps(accessGroupRoleMaps);
        AccessGroup savedAccessGroup = accessGroupRepository.save(accessGroup);
        AccessGroupListResponseDto accessGroupListResponseDto = accessGroupMapper.entityToDto(savedAccessGroup);
        return ResponseUtil.getSuccessfulApiResponse(accessGroupListResponseDto, "Access Group created successfully");
    }

    @Override
    public ApiResponse updateAccessGroup(AccessGroupRequestDto accessGroupRequestDto) {
        AccessGroup existingAccessGroup = accessGroupRepository.findById(accessGroupRequestDto.getId())
                .orElseThrow(() -> new BadRequestException("Access Group does not exist"));
        AccessGroup accessGroup = accessGroupMapper.updateEntityFromDto(accessGroupRequestDto, existingAccessGroup);
        List<AccessGroupRoleMap> accessGroupRoleMaps = new ArrayList<>(accessGroup.getAccessGroupRoleMaps());
        accessGroupRoleMaps.removeIf(roleMap -> !accessGroupRequestDto.getRoleIds().contains(roleMap.getRoles().getId()));
        for(Long roleId : accessGroupRequestDto.getRoleIds()) {
            if (accessGroupRoleMaps.stream().noneMatch(roleMap -> roleMap.getRoles().getId().equals(roleId))) {
                Roles role = roleRepository.findById(roleId).orElse(null);
                AccessGroupRoleMap roleMap = new AccessGroupRoleMap();
                roleMap.setRoles(role);
                roleMap.setAccessGroup(accessGroup);
                roleMap.setIsActive(true); // Set default value for isActive
                accessGroupRoleMaps.add(roleMap);
            }
        }
        AccessGroup savedAccessGroup = accessGroupRepository.save(accessGroup);
        AccessGroupListResponseDto accessGroupListResponseDto = accessGroupMapper.entityToDto(savedAccessGroup);
        return ResponseUtil.getSuccessfulApiResponse(accessGroupListResponseDto, "Access Group updated successfully");
    }

    @Override
    public ApiResponse<?> getallProducts(SearchParam searchParam) {
        Pageable pageable = PaginationUtil.getPageable(searchParam);
        Page<AccessGroup> accessGroupsPageList = accessGroupRepository.findAll(pageable);
        List<AccessGroup> allAccessGroups = accessGroupsPageList.getContent();
        if (allAccessGroups.isEmpty()) {
            return ResponseUtil.getFailureResponse("No Access Groups found");
        } else {
            List<AccessGroupListResponseDto> accessGroupListRespons = allAccessGroups.stream()
                    .map(accessGroupMapper::entityToDto)
                    .collect(Collectors.toList());
            return ResponseUtil.getSuccessfulApiResponseWithData(accessGroupListRespons, "Access Groups fetched successfully");
        }
    }
}
