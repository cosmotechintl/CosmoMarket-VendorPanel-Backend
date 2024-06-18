package com.cosmo.authentication.vendor.model.request;

import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnblockVendorRequest extends ModelBase {
    private String email;
}
