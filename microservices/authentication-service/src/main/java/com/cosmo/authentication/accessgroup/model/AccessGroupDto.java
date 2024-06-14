package com.cosmo.authentication.accessgroup.model;

import com.cosmo.authentication.accessgroup.entity.AccessGroup;
import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * DTO for {@link AccessGroup}
 */
@Getter
@Setter
public class AccessGroupDto extends ModelBase {
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private StatusDto status;
    private boolean isAdminGroup;
    private String remarks;
}