package com.cosmo.authentication.user.model.requestDto;

import com.cosmo.authentication.accessGroup.model.AccessGroupDto;
import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UpdateVendorRequest extends ModelBase {

    private String email;
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Mobile Number cannot be null")
    private String mobileNumber;
    @NotBlank(message = "Address cannot be null")
    private String address;
    @NotNull
    private AccessGroupDto accessGroup;

}
