package com.cosmo.futsalBooking_service.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "availability")
public class Availability extends AbstractEntity {
    private Long vendorId;
    private String startTime;
    private String endTime;
    private boolean available;
}
