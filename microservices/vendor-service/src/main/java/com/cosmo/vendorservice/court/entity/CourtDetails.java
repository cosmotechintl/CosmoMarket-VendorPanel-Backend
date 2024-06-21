package com.cosmo.vendorservice.court.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import com.cosmo.common.entity.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "court_details")
public class CourtDetails extends AbstractEntity {

    @Column(name ="name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "id")
    private Status status;

}
