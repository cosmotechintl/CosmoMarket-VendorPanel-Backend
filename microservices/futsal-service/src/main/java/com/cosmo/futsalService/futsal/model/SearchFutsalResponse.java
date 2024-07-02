package com.cosmo.futsalService.futsal.model;

import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFutsalResponse extends ModelBase {
    private String name;
    private double price;
    private String uuid;
    private StatusDto status;
    private String vendorCode;
}
