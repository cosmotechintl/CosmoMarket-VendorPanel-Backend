package com.cosmo.authentication.vendor.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "category")
public class Category extends AbstractEntity {
    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}
