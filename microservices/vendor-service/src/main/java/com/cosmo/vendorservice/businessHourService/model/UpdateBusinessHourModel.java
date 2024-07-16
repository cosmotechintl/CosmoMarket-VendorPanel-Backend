package com.cosmo.vendorservice.businessHourService.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UpdateBusinessHourModel extends ModelBase {
    @NotBlank(message = "vendor code cannot be blank")
    private String vendorCode;
    @NotBlank(message = "day cannot be blank")
    private String day;
    @NotBlank(message = "startTime cannot be blank")
    private String startTime;
    @NotBlank(message = "endTime cannot be blank")
    private String endTime;
    @NotNull(message = "closed cannot be null")
    private boolean closed;
}
