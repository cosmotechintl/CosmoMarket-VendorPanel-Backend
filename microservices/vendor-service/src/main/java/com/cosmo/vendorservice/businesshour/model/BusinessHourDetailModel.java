package com.cosmo.vendorservice.businesshour.model;

import com.cosmo.common.model.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BusinessHourDetailModel extends ModelBase {
    private Long vendorId;
    private String day;
    private String startTime;
    private String endTime;
    private boolean isClosed;
}
