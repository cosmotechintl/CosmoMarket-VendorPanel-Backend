package com.cosmo.authentication.user.model.request;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetPasswordRequest extends ModelBase {
    @NotBlank(message = "Password field cannot be blank")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*\\d)(?=.*[a-z]).{8,15}$",
            message = "Invalid password format"
    )
    private String password;
    @NotBlank(message = "Confirm password field cannot be blank")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*\\d)(?=.*[a-z]).{8,15}$",
            message = "Invalid password format"
    )
    private String confirmPassword;
    @NotBlank(message = "UUID field cannot be blank")
    private String uuid;
}
