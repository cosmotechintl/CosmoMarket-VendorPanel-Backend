package com.cosmo.authentication.accessGroup.model.request;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchAccessGroupDetail extends ModelBase {
    @NotBlank(message = "Name is required.")
    private String name;
}
