package com.cosmo.authentication.vendor.model.request;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class BlockVendorRequest extends ModelBase {
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;
}
