package com.cosmo.vendorservice.businesshour.model;

import com.cosmo.common.model.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessHourRequest extends ModelBase {
    private String day;
    private String startTime;
    private String endTime;
    private boolean closed;
}