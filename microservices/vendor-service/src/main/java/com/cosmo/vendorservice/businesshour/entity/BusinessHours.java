package com.cosmo.vendorservice.businesshour.entity;

import com.cosmo.authentication.vendor.entity.Vendor;
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

    @ManyToOne
    @JoinColumn(name = "vendor", referencedColumnName = "code")
    private Vendor vendor;

    @Column(name = "day")
    @Enumerated(EnumType.ORDINAL)
    private DayOfWeek day;

    @Column(name = "start_time", columnDefinition = "TIME")
    private LocalTime startTime;

    @Column(name = "end_time", columnDefinition = "TIME")
    private LocalTime endTime;

    @Column(name = "closed")
    private boolean closed;

    public boolean isOpen(LocalTime time) {
        if (closed) {
            return false;
        }
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }

}
