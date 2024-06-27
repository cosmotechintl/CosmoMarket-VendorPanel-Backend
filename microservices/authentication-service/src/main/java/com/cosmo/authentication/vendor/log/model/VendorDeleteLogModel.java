package com.cosmo.authentication.vendor.log.model;

import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorDeleteLogModel extends ModelBase {

    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;

    private Vendor vendor;
}
