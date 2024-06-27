package com.cosmo.vendorservice.court.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateCourtRequestModel extends ModelBase {
    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "image cannot be blank")
    private String image;

    @NotBlank(message = "description cannot be blank")
    private String description;
}
