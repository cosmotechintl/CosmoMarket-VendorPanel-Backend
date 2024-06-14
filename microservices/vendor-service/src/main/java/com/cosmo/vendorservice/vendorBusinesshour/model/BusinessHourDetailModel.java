package com.cosmo.vendorservice.vendorBusinesshour.model;

import com.cosmo.common.model.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BusinessHourDetailModel extends ModelBase{
    private String day;
    private String startTime;
    private String endTime;
    private boolean isClosed;
}
