package com.cosmo.vendorservice.vendorBusinesshour.model;

import com.cosmo.common.model.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SetBusinessHour extends ModelBase {
    private String day;
    private String startTime;
    private String endTime;
    private boolean closed;
}