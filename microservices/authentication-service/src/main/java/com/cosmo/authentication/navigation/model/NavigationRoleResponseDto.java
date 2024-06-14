package com.cosmo.authentication.navigation.model;

import com.cosmo.authentication.role.model.RoleResponse;
import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NavigationRoleResponseDto extends ModelBase {

    private String uiGroupName;
    private String icon;
    private String name;
    private String description;
    private String navigation;
    private int position;
    private boolean isDropdown;
    private List<RoleResponse> roles;
    private String permission;
}
