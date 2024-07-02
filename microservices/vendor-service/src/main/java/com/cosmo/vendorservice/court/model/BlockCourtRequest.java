package com.cosmo.vendorservice.court.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockCourtRequest extends ModelBase {
    private String name;
    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;
}
