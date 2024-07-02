package com.cosmo.vendorservice.futsalService.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchFutsalDetail extends ModelBase {

    @NotBlank(message = "uuid cannot be blank")
    private String uuid;
}
