package com.cosmo.authentication.user.model;

import com.cosmo.authentication.accessgroup.model.AccessGroupDto;
import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Data;


/**
 * DTO for {@link VendorUser}
 */
@Data
public class VendorUserDetailsDto extends ModelBase {
    private String name;
    private String username;
    private boolean isActive;
    private String email;
    private String mobileNumber;
    private String address;
    private StatusDto status;
    private AccessGroupDto accessGroup;
    private String profilePictureName;
    private boolean twoFactorEnabled;
    private boolean isAdmin;
}
