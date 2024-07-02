package com.cosmo.vendorservice.court.log.model;

import com.cosmo.common.model.ModelBase;
import com.cosmo.vendorservice.court.entity.CourtDetails;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockCourtLogModel extends ModelBase {
    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;

    private CourtDetails court;
}
