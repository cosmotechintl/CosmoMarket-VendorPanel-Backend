package com.cosmo.authentication.vendor.model.request;

import com.cosmo.authentication.user.model.request.VendorUserDetails;
import com.cosmo.authentication.vendor.model.VendorCategoryDto;
import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateVendorDetailRequest extends ModelBase {
    @NotBlank(message = "Code cannot be blank")
    private String code;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private VendorCategoryDto category;
    private String logo;
    @NotBlank(message = "Address cannot be blank")
    private String address;
    @NotBlank(message = "Mobile number cannot be blank")
    @Size(min = 10, max = 10, message = "Invalid mobile number ")
    private String phoneNumber;
    @NotNull
    private VendorUserDetails vendorUser;

}
