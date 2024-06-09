package com.cosmo.authentication.accessGroup.model;

import com.cosmo.authentication.accessgroup.entity.AccessGroupRoleMap;
import com.cosmo.authentication.role.model.RolesDto;
import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link AccessGroupRoleMap}
 */
@Getter
@Setter
public class AccessGroupRoleMapDto extends ModelBase {

    private AccessGroupDto accessGroup;
    private Boolean isActive;
    private RolesDto roles;
}