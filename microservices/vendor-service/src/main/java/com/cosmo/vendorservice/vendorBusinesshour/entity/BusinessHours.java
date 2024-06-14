package com.cosmo.vendorservice.vendorBusinesshour.entity;

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

    @Column(name = "vendor_id")
    private Long vendorId;

    private DayOfWeek day;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean closed;

    public boolean isOpen(LocalTime time) {
        if (closed) {
            return false;
        }
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }

}
