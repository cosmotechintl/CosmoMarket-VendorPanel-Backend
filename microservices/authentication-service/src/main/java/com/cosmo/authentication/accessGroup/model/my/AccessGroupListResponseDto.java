package com.cosmo.authentication.accessGroup.model.my;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessGroupListResponseDto {
    private String name;
    private String description;
    private String statusName;
    private List<String> roleNames;
}
