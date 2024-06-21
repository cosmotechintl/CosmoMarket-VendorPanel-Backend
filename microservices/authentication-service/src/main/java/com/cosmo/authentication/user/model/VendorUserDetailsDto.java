package com.cosmo.authentication.user.model;

import com.cosmo.authentication.accessGroup.model.request.FetchAccessGroupDetail;
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
    private String email;
    private String mobileNumber;
    private String address;
    private StatusDto status;
    private FetchAccessGroupDetail accessGroup;
    private String profilePictureName;
}
