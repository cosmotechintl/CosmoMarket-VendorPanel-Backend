package com.cosmo.authentication.vendor.model;

import com.cosmo.authentication.user.model.request.VendorUserDetailRequest;
import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VendorDetailDto extends ModelBase {
    private String name;
    private String logo;
    private String address;
    private String code;
    private String email;
    private String mobileNumber;
    private StatusDto status;
    private List<VendorUserDetailRequest> vendorUsers;
}
