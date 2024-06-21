package com.cosmo.vendorservice.court.model;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UpdateCourtRequest {
    private Long id;
    @NotBlank(message = "name cannot be null")
    private String name;
    @NotBlank(message = "image cannot be null")
    private String image;
    @NotBlank(message = "description cannot be null")
    private String description;
}