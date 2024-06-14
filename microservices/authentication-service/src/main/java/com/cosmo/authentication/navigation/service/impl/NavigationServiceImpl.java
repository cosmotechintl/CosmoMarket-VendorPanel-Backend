package com.cosmo.authentication.navigation.service.impl;

import com.cosmo.authentication.accessgroup.entity.AccessGroup;
import com.cosmo.authentication.accessgroup.repo.AccessGroupRepository;
import com.cosmo.authentication.accessgroup.repo.AccessGroupRoleMapRepository;
import com.cosmo.authentication.navigation.mapper.NavigationBuilder;
import com.cosmo.authentication.navigation.model.NavigationRoleResponseDto;
import com.cosmo.authentication.navigation.service.NavigationService;
import com.cosmo.authentication.role.entity.Roles;
import com.cosmo.authentication.security.JwtToken;
import com.cosmo.common.exception.InvalidInputException;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class NavigationServiceImpl implements NavigationService {
    private final JwtToken jwtToken;
    private final AccessGroupRepository accessGroupRepository;
    private final AccessGroupRoleMapRepository accessGroupRoleMapRepository;
    private final NavigationBuilder navigationBuilder;

    @Override

    public Mono<ApiResponse> getAllNavigation() {
        String groupTypeName = jwtToken.getGroupTypeName();
        AccessGroup accessGroup = accessGroupRepository.findByName(groupTypeName).orElseThrow(() -> new InvalidInputException("Merchant Access Group not found."));
        List<Roles> rolesList = accessGroupRoleMapRepository.getRolesByAccessGroup(accessGroup.getId());
        List<NavigationRoleResponseDto> navigationRoleResponseDtoList = getNavigationRoleResponseDto(rolesList);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(navigationRoleResponseDtoList, "Navigation Successfully Fetched."));
    }

    private List<NavigationRoleResponseDto> getNavigationRoleResponseDto(List<Roles> rolesList) {
        return navigationBuilder.buildNavigation(rolesList);
    }
}
