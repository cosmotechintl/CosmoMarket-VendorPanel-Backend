package com.cosmo.futsalService.reserve.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import com.cosmo.futsalService.futsal.entity.Futsal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "reservation_details")
public class ReservationDetails extends AbstractEntity {

    @Column(name = "vendor_code", nullable = false)
    private String vendorCode;

    @JoinColumn(name = "futsal_uuid", referencedColumnName = "uuid")
    private String futsal_uuid;

    @Column(name = "date", columnDefinition = "TIME")
    private LocalDate date;

    @Column(name = "start_time", columnDefinition = "TIME")
    private LocalTime startTime;

    @Column(name = "end_time", columnDefinition = "TIME")
    private LocalTime endTime;

    @Column(name = "code")
    private String code;

    @Column(name = "day")
    @Enumerated(EnumType.ORDINAL)
    private DayOfWeek day;
}
