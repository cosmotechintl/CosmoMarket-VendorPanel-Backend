package com.cosmo.futsalService.court.model;

import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCourtResponse extends ModelBase {
    private String name;
    private String description;
    private StatusDto status;

}
