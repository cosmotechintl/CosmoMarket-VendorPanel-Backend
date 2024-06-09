package com.cosmo.authentication.accessGroup.model.my;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private String name;
    private String description;
    private List<RoleDto> childRoles = new ArrayList<>();

}
