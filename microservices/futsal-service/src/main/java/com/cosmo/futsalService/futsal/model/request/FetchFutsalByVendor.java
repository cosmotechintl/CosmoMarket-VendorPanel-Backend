package com.cosmo.futsalService.futsal.model.request;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchFutsalByVendor extends ModelBase {
    @NotBlank(message = "Vendor code cannot be blank")
    private String vendorCode;
}
