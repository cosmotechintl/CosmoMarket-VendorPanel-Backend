package com.cosmo.authentication.accessGroup.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccessGroupModel extends ModelBase {
    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Descriptions is required.")
    private String description;

    @Valid
    @NotNull(message = "Type is required")
    private TypeDto type;

    @Valid
    @NotNull
    private List<AssignRoleModel> roles;
}
