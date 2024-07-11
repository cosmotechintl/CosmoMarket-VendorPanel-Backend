package com.cosmo.futsalService.futsal.model;

import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FutsalDto extends ModelBase {
    private String name;
    private String description;
    private String image;
    private double price;
    private String location;
    private StatusDto status;
    private String uuid;
    private String vendorCode;
}
