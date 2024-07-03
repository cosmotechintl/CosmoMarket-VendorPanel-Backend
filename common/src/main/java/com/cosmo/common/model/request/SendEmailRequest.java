package com.cosmo.common.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailRequest {
    @Email(message="Invalid email format")
    private String recipient;
    @NotBlank(message="Subject cannot be empty")
    private String subject;
    @NotBlank(message="Message cannot be empty")
    private String message;
}
