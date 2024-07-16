package com.cosmo.vendorservice.futsalReservationService.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Setter
@Getter
public class CreateReservationModel extends ModelBase {
    @NotNull(message = "Vendor ID cannot be null")
    private String vendorCode;

    @NotNull(message = "Futsal code cannot be null")
    private String Futsal_uuid;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;
}