package com.cosmo.authentication.user.model.request;

import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorUserDetailRequest extends ModelBase {
    private String name;
    private String email;
}
