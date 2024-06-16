package com.cosmo.authentication.user.model;

import com.cosmo.authentication.accessgroup.model.AccessGroupDto;
import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchVendorUsersResponse extends ModelBase {

    private String name;
    private String username;
    private boolean isActive;
    private String email;
    private String mobileNumber;
    private String address;
    private StatusDto status;
    private AccessGroupDto accessGroup;

}