package com.cosmo.authentication.vendor.log.entity;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "registration_email_log")
public class RegistrationEmailLog extends AbstractEntity {

    @Column(name="email",nullable = false)
    private String email;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="vendorUser", nullable=false, referencedColumnName = "id")
    private VendorUser vendorUser;

    @Column(name="message",nullable = false)
    private String message;

    @Column(name="is_sent")
    private boolean isSent;

    @Column(name="is_expired")
    private boolean isExpired;

    @Column(name="uuid")
    private String uuid;
}
