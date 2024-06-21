package com.cosmo.authentication.accessGroup.model;

import com.cosmo.authentication.accessGroup.entity.AccessGroup;
import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * DTO for {@link AccessGroup}
 */
@Getter
@Setter
public class AccessGroupDetailDto extends ModelBase {

    private String name;
    private String description;
    private TypeDto type;
    private List<AccessGroupRoleMapDetailDto> accessGroupRoleMaps;
}
