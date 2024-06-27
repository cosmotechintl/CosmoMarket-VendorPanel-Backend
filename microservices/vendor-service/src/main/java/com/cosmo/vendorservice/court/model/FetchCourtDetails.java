package com.cosmo.vendorservice.court.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchCourtDetails extends ModelBase {

    @NotBlank(message = "name cannot be blank")
    private String name;
}
