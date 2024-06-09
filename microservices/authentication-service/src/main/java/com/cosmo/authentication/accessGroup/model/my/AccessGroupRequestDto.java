package com.cosmo.authentication.accessGroup.model.my;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessGroupRequestDto {
    private Long id;
    private String name;
    private String description;
    private Boolean isSuperAdminGroup;
    private String remarks;
    private List<Long> roleIds;
}

