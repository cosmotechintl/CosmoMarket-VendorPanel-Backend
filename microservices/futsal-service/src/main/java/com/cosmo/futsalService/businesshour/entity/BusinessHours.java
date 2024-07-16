package com.cosmo.futsalService.businesshour.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "business_hours")
public class BusinessHours extends AbstractEntity {

    @Column(name = "vendor_code", nullable = false)
    private String vendorCode;

    @Column(name = "day")
    @Enumerated(EnumType.ORDINAL)
    private DayOfWeek day;

    @Column(name = "start_time", columnDefinition = "TIME")
    private LocalTime startTime;

    @Column(name = "end_time", columnDefinition = "TIME")
    private LocalTime endTime;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "closed")
    private boolean isClosed;

}
