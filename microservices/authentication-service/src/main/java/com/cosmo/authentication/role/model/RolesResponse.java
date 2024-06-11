package com.cosmo.authentication.role.model;

import com.cosmo.common.model.ModelBase;
import lombok.*;

import java.util.List;

/**
 * Author: Sapana Rimal
 * Date: 5/15/2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolesResponse extends ModelBase {

    private Long id;
    private String name;
    private String description;
    private Boolean isActive;
    private String parentName;
    private List<RolesResponse> childRoles;

}
