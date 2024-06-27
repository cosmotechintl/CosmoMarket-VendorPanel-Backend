package com.cosmo.authentication.vendor.log.entity;

import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vendor_delete_log")
public class VendorDeleteLog extends AbstractEntity {

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="vendor", nullable=false, referencedColumnName = "id")
    private Vendor vendor;

    @Column(name = "deleted_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;
}
