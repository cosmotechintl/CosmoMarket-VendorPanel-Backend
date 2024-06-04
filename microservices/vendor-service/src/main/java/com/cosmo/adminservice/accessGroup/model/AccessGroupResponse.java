package com.cosmo.adminservice.accessGroup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessGroupResponse {
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private String statusName;
    private Boolean isSuperAdminGroup;
    private String remarks;
    private List<String> roleNames;
}
