package com.cosmo.vendorservice.court.model;

import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourtDetailsDto extends ModelBase {

    private String name;
    private String description;
    private String image;
    private String code;
    private StatusDto status;
}