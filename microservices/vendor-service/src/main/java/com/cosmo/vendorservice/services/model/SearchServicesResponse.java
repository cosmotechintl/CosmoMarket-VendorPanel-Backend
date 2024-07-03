package com.cosmo.vendorservice.services.model;

import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchServicesResponse extends ModelBase {
    private String name;
    private String description;
    private String uuid;
    private StatusDto status;
}
