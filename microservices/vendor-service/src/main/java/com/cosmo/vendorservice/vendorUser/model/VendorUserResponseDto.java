package com.cosmo.vendorservice.vendorUser.model;

import lombok.Data;

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
