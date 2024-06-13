package com.cosmo.authentication.accessgroup.model;

import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchAccessGroupResponse extends ModelBase {
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private StatusDto status;
    private Boolean isAdminGroup;
    private String remarks;

}
