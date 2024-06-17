package com.cosmo.authentication.vendor.entity;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vendor")
public class Vendor extends AbstractEntity {
    @Column(name = "vendorName")
    private String vendorName;

    @Column(name = "category")
    private String category;

    @Column(name = "logo")
    private String logo;

    @Column(name = "address")
    private String address;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<VendorUser> vendorUsers;
}
