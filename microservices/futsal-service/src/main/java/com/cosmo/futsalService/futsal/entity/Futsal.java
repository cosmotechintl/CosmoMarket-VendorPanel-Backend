package com.cosmo.futsalService.futsal.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import com.cosmo.common.entity.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "futsal")
public class Futsal extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "location", nullable = false)
    private String location;

    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;

    @Column(name = "vendor_code", nullable = false)
    private String vendorCode;

}
