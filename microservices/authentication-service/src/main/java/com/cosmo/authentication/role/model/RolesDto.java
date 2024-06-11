package com.cosmo.authentication.role.model;

import com.cosmo.authentication.role.entity.Roles;
import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Roles}
 */
@Getter
@Setter
public class RolesDto extends ModelBase {
    private String name;
    private String description;
    private String icon;
    private String navigation;
    private Integer position;
    private String uiGroupName;
    private String parentName;
    private String permission;
}