package com.cosmo.vendorservice.vendorBusinesshour.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SetBusinessHour {
    private String day;
    private String startTime;
    private String endTime;
    private boolean closed;
}