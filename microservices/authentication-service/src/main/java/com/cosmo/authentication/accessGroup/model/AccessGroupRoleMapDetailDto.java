package com.cosmo.authentication.accessGroup.model;

import com.cosmo.authentication.role.entity.AccessGroupRoleMap;
import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link AccessGroupRoleMap}
 */
@Getter
@Setter
public class AccessGroupRoleMapDetailDto extends ModelBase  {

    private Boolean isActive;
    private RolesDto roles;
}
