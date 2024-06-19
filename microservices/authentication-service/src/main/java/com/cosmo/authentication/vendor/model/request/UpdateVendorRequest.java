package com.cosmo.authentication.vendor.model.request;

import com.cosmo.authentication.user.model.request.VendorUserDetails;
import com.cosmo.authentication.vendor.model.CategoryDto;
import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateVendorRequest extends ModelBase {

    private String email;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Category cannot be blank")
    private CategoryDto category;
    private String logo;
    @NotBlank(message = "Address cannot be blank")
    private String address;
    @NotBlank(message = "Mobile number cannot be blank")
    @Size(min = 10, max = 10, message = "Invalid mobile number ")
    private String mobileNumber;
    @NotNull
    private List<VendorUserDetails> vendorUser;

}
