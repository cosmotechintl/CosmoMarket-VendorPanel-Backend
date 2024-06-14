package com.cosmo.authentication.user.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PasswordChangeRequest extends ModelBase {
    @NotBlank(message = "old password is required")
    private String oldPassword;
    @NotBlank(message = "new password is required")
    private String newPassword;
    @NotBlank(message = "retype new password is required")
    private String retypeNewPassword;
}
