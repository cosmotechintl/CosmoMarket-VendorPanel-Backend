package com.cosmo.vendorservice.businessHourService.model;

import com.cosmo.common.model.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BusinessHourDetailModel extends ModelBase {
    private String vendorCode;
}
