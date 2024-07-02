package com.cosmo.futsalService.futsal.log.model;

import com.cosmo.common.model.ModelBase;
import com.cosmo.futsalService.futsal.entity.Futsal;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FutsalBlockLogModel extends ModelBase {

    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;

    private Futsal futsal;
}
