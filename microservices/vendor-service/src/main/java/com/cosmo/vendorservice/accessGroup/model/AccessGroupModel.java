package com.cosmo.vendorservice.accessGroup.model;

import com.cosmo.common.entity.Status;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessGroupModel {
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private Long statusId;
    private Boolean isSuperAdminGroup;
    private String remarks;
    private List<Long> roleIds;

    public Status getStatus() {
        Status status = new Status();
        status.setId(statusId); // Set the statusId as the ID of the Status object
        return status;
    }
}

