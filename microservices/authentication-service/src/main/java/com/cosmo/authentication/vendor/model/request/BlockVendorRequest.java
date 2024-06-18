package com.cosmo.authentication.vendor.model.request;

import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class BlockVendorRequest extends ModelBase {
    private String email;
}
