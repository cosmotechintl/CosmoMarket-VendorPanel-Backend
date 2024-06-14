package com.cosmo.authentication.user.model;

import com.cosmo.authentication.accessgroup.model.AccessGroupDto;
import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateVendorUserModel extends ModelBase {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Password is mandatory")
    private String username;

    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Size(min = 10,max = 10, message = "Mobile number Invalid")
    @NotBlank(message = "Mobile number is mandatory")
    private String mobileNumber;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotNull(message = "Access Group Cannot Be Null")
    private AccessGroupDto accessGroup;

}
