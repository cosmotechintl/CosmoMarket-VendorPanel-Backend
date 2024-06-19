package com.cosmo.authentication.vendor.model;

import com.cosmo.authentication.user.model.requestDto.VendorUserDto;
import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateVendorModel extends ModelBase {

    @NotBlank(message = "Vendor name cannot be blank")
    private String name;

    @NotNull(message = "Category cannot be blank")
    private CategoryDto category;

    private String logo;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @Email
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Mobile number cannot be blank")
    @Size(min = 10, max = 10, message = "The mobile number is incorrect")
    private String phoneNumber;

    @NotBlank(message = "Pan number cannot be blank")
    private String panNumber;

    @NotNull
    private VendorUserDto vendorUser;
}
