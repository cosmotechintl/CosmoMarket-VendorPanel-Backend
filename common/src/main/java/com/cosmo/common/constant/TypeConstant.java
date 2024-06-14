package com.cosmo.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeConstant {
    SYSTEM("SYSTEM", "SYSTEM"),
    VENDOR_ADMIN("VENDOR_ADMIN", "VENDOR_ADMIN"),
    VENDOR_STAFF("VENDOR_STAFF", "VENDOR_STAFF"),;

    private final String name;
    private final String description;
}
