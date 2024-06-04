package com.cosmo.adminservice.vendorUser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class VendorUserResponseDto {
    private String name;
    private String username;
    private boolean isActive;
    private String email;
    private String mobileNumber;
    private String address;
    private String statusName;
    private String accessGroupName;
    private String profilePictureName;
    private boolean twoFactorEnabled;
    private boolean isSuperAdmin;
}
