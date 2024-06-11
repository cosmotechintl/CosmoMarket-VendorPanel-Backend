package com.cosmo.futsalBooking_service.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking extends AbstractEntity {
    private String customerName;
    private String mobileNumber;
    private String startTime;
    private String endTime;
    private String amount;
    private Court court;
    private String slot;
    private String action;
}