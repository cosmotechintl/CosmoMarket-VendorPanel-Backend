package com.cosmo.authentication.user.log.entity;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vendor_user_block_log")
public class VendorUserBlockLog extends AbstractEntity {

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="vendor_user", nullable=false, referencedColumnName = "id")
    private VendorUser vendorUser;

    @Column(name = "blocked_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date blockedDate;
}
