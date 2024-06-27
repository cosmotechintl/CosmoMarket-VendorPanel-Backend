package com.cosmo.authentication.user.log.model;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorUserDeleteLogModel extends ModelBase {

    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;

    private VendorUser vendorUser;
}
