package com.cosmo.vendorservice.court.log.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import com.cosmo.vendorservice.court.entity.CourtDetails;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "court_block_log")
public class CourtBlockLog extends AbstractEntity {
    @Column(name = "remarks")
    private String remarks;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="court", nullable=false, referencedColumnName = "id")
    private CourtDetails court;

    @Column(name = "blocked_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date blockedDate;
}
