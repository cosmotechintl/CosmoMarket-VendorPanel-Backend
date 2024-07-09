package com.cosmo.authentication.user.model.request;

import com.cosmo.authentication.accessGroup.model.AccessGroupDto;
import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorUserDetailRequest extends ModelBase {
    private String name;
    private String username;
    private String email;
    private String mobileNumber;
    private String address;
    private StatusDto status;
    private AccessGroupDto accessGroup;
}
