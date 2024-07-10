package com.cosmo.authentication.vendor.entity;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import com.cosmo.common.entity.Status;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vendor")
public class Vendor extends AbstractEntity {
    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "logo")
    private String logo;

    @Column(name = "address")
    private String address;

    @Column(name = "email",unique = true,nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "pan_number",nullable = false)
    private String panNumber;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "code")
    private String code;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category", referencedColumnName = "id")
    private VendorCategory vendorCategory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status", referencedColumnName = "id")
    private Status status;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(mappedBy = "vendor",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<VendorUser> vendorUsers;
}
