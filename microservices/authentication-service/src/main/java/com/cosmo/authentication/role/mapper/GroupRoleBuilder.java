package com.cosmo.authentication.role.mapper;

import com.cosmo.authentication.role.entity.Roles;
import com.cosmo.authentication.role.model.RolesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Author: Sapana Rimal
 * Date: 5/15/2024
 */

@Slf4j
@Component
@RequestScope
public class GroupRoleBuilder {

    Map<String, List<RolesResponse>> rolesMap;
    Map<String, RolesResponse> parentMap;
    List<RolesResponse> rolesResponses;

    public GroupRoleBuilder() {
        rolesMap = new LinkedHashMap<>();
        parentMap = new LinkedHashMap<>();
        rolesResponses = new ArrayList<>();
    }

    public List<RolesResponse> buildRolesResponse(List<Roles> rolesList, List<Long> existingAccessGroupMapIds) {
        buildProfileRole(rolesList, existingAccessGroupMapIds);
        setChildRolesToParent();
        return new ArrayList<>(parentMap.values());
    }

    public void buildProfileRole(List<Roles> rolesList, List<Long> existingAccessGroupMapIds) {
        rolesList.stream().forEach(roles -> {
            RolesResponse rolesResponse = setRolesResponse(roles, new RolesResponse(), existingAccessGroupMapIds);

            setParentRoles(roles).accept(rolesResponse);
            buildRoleResponse(roles, existingAccessGroupMapIds).accept(rolesResponse, rolesList);

        });
    }

    private Consumer<RolesResponse> setParentRoles(Roles roles) {
        return (rolesResponse) -> {
            if (!isRoot(roles.getName()) && isRoot(roles.getParentName())) { //check if it is not root roll\
                if (!parentMap.containsKey(roles.getName())) {
                    parentMap.put(rolesResponse.getName(), rolesResponse);
                }
            }
        };
    }

    private BiConsumer<RolesResponse, List<Roles>> buildRoleResponse(Roles roles, List<Long> existingServiceGroupMapsIds) {
        return (rolesResponse, rolesList) -> {
            if (!isRoot(roles.getName())) {
                buildChildProfileRoles(rolesList, existingServiceGroupMapsIds).accept(rolesResponse);
                setRolesMap(roles).accept(rolesResponse);
            }
        };
    }

    private Consumer<RolesResponse> setRolesMap(Roles roles) {
        return (rolesResponse) -> {
            if (!rolesMap.containsKey(roles.getParentName())) {
                rolesMap.put(rolesResponse.getParentName(), new ArrayList<>());
                rolesMap.get(rolesResponse.getParentName()).add(rolesResponse);
            } else {
                rolesMap.get(rolesResponse.getParentName()).add(rolesResponse);
            }
        };

    }

    private Consumer<RolesResponse> buildChildProfileRoles(List<Roles> rolesList, List<Long> existingServiceGroupMapsIds) {
        return (rolesResponse) -> {
            List<RolesResponse> childRoles = new ArrayList<>();
            rolesList.stream().forEach(groupTypeRoleMap -> {
                if (rolesResponse.getName().equalsIgnoreCase(groupTypeRoleMap.getParentName())) {
                    RolesResponse rolesResponse1 = setRolesResponse(groupTypeRoleMap, new RolesResponse(), existingServiceGroupMapsIds);
                    childRoles.add(rolesResponse1);
                    buildChildProfileRoles(rolesList, existingServiceGroupMapsIds).accept(rolesResponse1);
                }
            });
            rolesResponse.setChildRoles(childRoles);
        };

    }

    private void setChildRolesToParent() {
        for (Map.Entry<String, RolesResponse> parentMapObj : parentMap.entrySet()) {
            if (parentMap.containsKey(parentMapObj.getKey())) {
                parentMap.get(parentMapObj.getKey()).setChildRoles(rolesMap.get(parentMapObj.getKey()));
            }
        }
    }

    private boolean isRoot(String name) {
        return name.equalsIgnoreCase("ROOT");
    }

    private RolesResponse setRolesResponse(Roles roles, RolesResponse rolesResponse, List<Long> existingAccessGroupMapIds) {
        if (!existingAccessGroupMapIds.contains(roles.getId())) {
            rolesResponse.setIsActive(false);
        } else {
            rolesResponse.setIsActive(true);
        }
        rolesResponse.setDescription(roles.getDescription());
        rolesResponse.setName(roles.getName());
        rolesResponse.setId(roles.getId());
        rolesResponse.setParentName(roles.getParentName());
        return rolesResponse;
    }
}
