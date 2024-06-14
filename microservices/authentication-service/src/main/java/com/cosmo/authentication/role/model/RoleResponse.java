package com.cosmo.authentication.role.model;

import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author: Sapana Rimal
 * Date: 5/29/2024
 */
@Getter
@Setter
public class RoleResponse extends ModelBase {

    private String name;
    private String description;
    private String uiGroupName;
    private String navigation;
    private String parentName;
    private String icon;
    private int position;
    private List<RoleResponse> childRoles;
    private String permission;
}
