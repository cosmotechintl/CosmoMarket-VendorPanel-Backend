package com.cosmo.authentication.user.model.request;
import com.cosmo.authentication.accessGroup.model.AccessGroupDto;
import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorUserDetails extends ModelBase {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Mobile cannot be blank")
    @Size(min = 10, max = 19, message = "Invalid mobile number")
    private String mobileNumber;
    @NotNull
    private AccessGroupDto accessGroup;

}
