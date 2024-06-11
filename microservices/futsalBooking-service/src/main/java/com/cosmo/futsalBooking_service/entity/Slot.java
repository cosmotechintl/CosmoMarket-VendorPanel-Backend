package com.cosmo.futsalBooking_service.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "slot")
public class Slot extends AbstractEntity {
    private LocalTime startTime;
    private LocalTime endTime;
}
