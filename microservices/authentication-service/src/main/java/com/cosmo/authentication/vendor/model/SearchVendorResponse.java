package com.cosmo.authentication.vendor.model;

import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SearchVendorResponse extends ModelBase {
    private String name;
    private String address;
    private String mobileNumber;
    private String email;
    private String panNumber;
    private String code;
    private String createdDate;
    private CategoryDto category;
    private StatusDto status;
}
