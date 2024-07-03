package com.cosmo.futsalService.futsal.model.request;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFutsalModel extends ModelBase {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Image cannot be blank")
    private String image;

    @NotNull(message = "Price cannot be blank")
    private double price;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    private String uuid;
}
