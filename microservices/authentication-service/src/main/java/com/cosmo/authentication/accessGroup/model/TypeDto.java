package com.cosmo.authentication.accessGroup.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeDto extends ModelBase {
    @NotBlank(message = "type is required.")
    private String name;
}
