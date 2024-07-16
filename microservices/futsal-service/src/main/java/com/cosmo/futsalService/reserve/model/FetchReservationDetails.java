package com.cosmo.futsalService.reserve.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class FetchReservationDetails extends ModelBase {
    @NotNull(message = "vendor code cannot be blank")
    private String vendorCode;
    @NotNull(message = "date cannot be blank")
    private LocalDate date;
}

