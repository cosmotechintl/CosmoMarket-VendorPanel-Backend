package com.cosmo.authentication.accessgroup.model;

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
public class AccessGroupRoleMapDetailDto extends ModelBase  {

    private Boolean isActive;
    private RolesDto roles;
}