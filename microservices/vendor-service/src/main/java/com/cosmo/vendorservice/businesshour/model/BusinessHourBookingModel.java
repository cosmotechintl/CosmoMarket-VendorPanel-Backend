package com.cosmo.vendorservice.businesshour.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessHourBookingModel extends ModelBase {
    @NotNull
    private String vendorCode;
}
