package com.cosmo.authentication.user.entity;

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
    @Column(unique = true)
    private String vendorName;

    private String category;

    private String logo;

    private String address;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<VendorUser> vendorUsers;
}
