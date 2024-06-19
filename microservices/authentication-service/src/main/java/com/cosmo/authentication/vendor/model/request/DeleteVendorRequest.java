package com.cosmo.authentication.vendor.model.request;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteVendorRequest extends ModelBase {
    @NotBlank(message = "Email cannot be blank")
    private String email;
}
