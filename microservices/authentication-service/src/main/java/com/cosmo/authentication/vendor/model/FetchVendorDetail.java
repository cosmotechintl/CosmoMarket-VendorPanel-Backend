package com.cosmo.authentication.vendor.model;


import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchVendorDetail extends ModelBase {
    @NotBlank(message = "Code cannot be blank")
    private String code;
}