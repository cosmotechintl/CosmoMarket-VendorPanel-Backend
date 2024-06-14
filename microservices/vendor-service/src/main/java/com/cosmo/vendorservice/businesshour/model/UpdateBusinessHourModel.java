package com.cosmo.vendorservice.businesshour.model;

import com.cosmo.common.model.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateBusinessHourModel extends ModelBase {
    private String id;
    private String day;
    private String startTime;
    private String endTime;
    private boolean closed;
}
