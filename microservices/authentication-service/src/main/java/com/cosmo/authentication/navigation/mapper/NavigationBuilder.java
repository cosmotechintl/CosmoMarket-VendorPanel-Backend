package com.cosmo.authentication.navigation.mapper;

import com.cosmo.authentication.navigation.model.NavigationRoleResponseDto;
import com.cosmo.authentication.role.entity.Roles;
import com.cosmo.authentication.role.model.RoleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Author: Sapana Rimal
 * Date: 5/29/2024
 */

@Slf4j
@Component
public class NavigationBuilder {

    private Map<String, NavigationRoleResponseDto> navigationRoleResponses;
    private Map<String, List<RoleResponse>> roleResponses;

    public NavigationBuilder() {
        navigationRoleResponses = new LinkedHashMap<>();
        roleResponses = new LinkedHashMap<>();
    }


    public List<NavigationRoleResponseDto> buildNavigation(List<Roles> roles) {
        try {
            buildRoleResponse(roles);
            buildNavigationResponse(roles);
            return new ArrayList<>(navigationRoleResponses.values());
        } catch (Exception e) {
            log.error("Exception :: " + e.getMessage());
            return null;
        }
    }

    private void buildRoleResponse(List<Roles> roles) {
        roles.stream().forEach(role -> {
            RoleResponse roleResponse = setRoleResponse(new RoleResponse(), role);
            setChildRoleResponsesWithRespectToParentName(role, roleResponse).accept(roles);
        });
    }

    private RoleResponse setRoleResponse(RoleResponse roleResponse, Roles role) {
        roleResponse.setDescription(role.getDescription());
        roleResponse.setName(role.getName());
        roleResponse.setNavigation(role.getNavigation());
        roleResponse.setUiGroupName(role.getUiGroupName());
        roleResponse.setIcon(role.getIcon());
        roleResponse.setParentName(role.getParentName());
        roleResponse.setPosition(role.getPosition());
        roleResponse.setPermission(role.getPermission());
        return roleResponse;
    }

    private Consumer<List<Roles>> setChildRoleResponsesWithRespectToParentName(Roles role, RoleResponse roleResponse) {
        return roles -> {
            if (!isRoot(role.getName()) && !isRoot(role.getParentName())) {
                setChildResponse(role, roles).accept(roleResponse);
                if (!roleResponses.containsKey(role.getParentName())) {
                    List<RoleResponse> childRoles = new ArrayList<>();
                    roleResponses.put(role.getParentName(), childRoles);
                }

                roleResponses.get(role.getParentName()).add(roleResponse);
            }
        };

    }

    private boolean isRoot(String name) {
        return name.equalsIgnoreCase("ROOT");
    }

    private Consumer<RoleResponse> setChildResponse(Roles role, List<Roles> roles) {
        return roleResponse -> {
            List<RoleResponse> childRoleResponseList = new ArrayList<>();
            for (Roles childRole : roles) {
                if (role.getName().equalsIgnoreCase(childRole.getParentName())) {
                    RoleResponse childRoleResponse = setRoleResponse(new RoleResponse(), childRole);
                    childRoleResponseList.add(childRoleResponse);
                    setChildResponse(childRole, roles).accept(childRoleResponse);
                }
            }
            roleResponse.setChildRoles(childRoleResponseList);
        };
    }

    private void buildNavigationResponse(List<Roles> roles) {
        roles.forEach(this::buildForNavigationRoles);

    }

    private void buildForNavigationRoles(Roles role) {
        if (!isRoot(role.getName()) && isRoot(role.getParentName())) {
            if (navigationRoleResponses.containsKey(role.getName())) {
                navigationRoleResponses.get(role.getName()).setRoles(roleResponses.get(role.getName()));
            } else {
                NavigationRoleResponseDto navigationRoleResponse = setNavigationRole(new NavigationRoleResponseDto(), role);
                navigationRoleResponses.put(role.getName(), navigationRoleResponse);
            }
        }
    }

    private NavigationRoleResponseDto setNavigationRole(NavigationRoleResponseDto navigationRoleResponse, Roles role) {
        navigationRoleResponse.setUiGroupName(role.getDescription());
        navigationRoleResponse.setIcon(role.getIcon());
        navigationRoleResponse.setRoles(roleResponses.get(role.getName()));
        navigationRoleResponse.setPosition(role.getPosition());
        navigationRoleResponse.setName(role.getName());
        navigationRoleResponse.setDescription(role.getDescription());
        navigationRoleResponse.setNavigation(role.getNavigation());
        navigationRoleResponse.setPermission(role.getPermission());
        navigationRoleResponse.setDescription(role.getDescription());
        return navigationRoleResponse;
    }


}
