package com.cosmo.authentication.accessGroup.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignRoleModel extends ModelBase {

    @NotNull(message = "Role ID is required.")
    private Long roleId;
}
