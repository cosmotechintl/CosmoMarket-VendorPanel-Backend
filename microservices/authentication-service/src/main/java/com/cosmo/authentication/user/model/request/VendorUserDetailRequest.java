package com.cosmo.authentication.user.model.request;

import com.cosmo.authentication.accessGroup.model.AccessGroupDto;
import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorUserDetailRequest extends ModelBase {
    private String name;
    private String email;
    private String mobileNumber;
    private AccessGroupDto accessGroup;
}
