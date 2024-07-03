package com.cosmo.futsalService.futsal.log.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import com.cosmo.futsalService.futsal.entity.Futsal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "futsal_block_log")
public class FutsalBlockLog extends AbstractEntity {

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="futsal", nullable=false, referencedColumnName = "id")
    private Futsal futsal;

    @Column(name = "blocked_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date blockedDate;
}
