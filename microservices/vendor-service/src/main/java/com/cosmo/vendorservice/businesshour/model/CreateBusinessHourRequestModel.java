package com.cosmo.vendorservice.businesshour.model;

import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBusinessHourRequestModel extends ModelBase {
    private List<BusinessHourRequest> businessHours;
}
