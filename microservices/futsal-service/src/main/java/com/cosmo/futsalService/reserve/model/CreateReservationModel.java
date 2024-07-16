package com.cosmo.futsalService.reserve.model;

import com.cosmo.futsalService.futsal.entity.Futsal;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CreateReservationModel {

    @NotNull(message = "Vendor ID cannot be null")
    private String vendorCode;

    @NotNull(message = "Futsal code cannot be null")
    private String futsal_uuid;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;
}


