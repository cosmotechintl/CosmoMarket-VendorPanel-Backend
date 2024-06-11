package com.cosmo.authentication.accessgroup.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignRoleModel extends ModelBase {

    @NotBlank(message = "Role ID is required.")
    private Integer roleId;
}
