package com.cosmo.authentication.vendor.model;

import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchVendorResponse extends ModelBase {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String panNumber;
    private String code;
    private String createdDate;
    private VendorCategoryDto category;
    private StatusDto status;
}
