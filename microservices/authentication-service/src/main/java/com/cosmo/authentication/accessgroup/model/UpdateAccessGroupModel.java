package com.cosmo.authentication.accessgroup.model;

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
public class UpdateAccessGroupModel extends ModelBase {
    private Long id;
    @NotBlank(message = "Name is required.")
    private String name;
    @NotBlank(message = "Descriptions is required.")
    private String description;
    @Valid
    @NotNull
    private List<AssignRoleModel> roles;
}
