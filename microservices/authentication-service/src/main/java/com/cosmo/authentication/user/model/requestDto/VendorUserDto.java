package com.cosmo.authentication.user.model.requestDto;

import com.cosmo.authentication.accessGroup.model.AccessGroupDto;
import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class VendorUserDto extends ModelBase {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Mobile number cannot be blank")
    @Size(min = 10,max = 10,message = "The mobile number is incorrect")
    private String mobileNumber;

    @Email
    @NotBlank(message = "Email cannot be Blank")
    private String email;

    @NotNull
    private AccessGroupDto accessGroup;
}
