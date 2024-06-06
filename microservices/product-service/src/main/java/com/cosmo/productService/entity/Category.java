package com.cosmo.productService.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "category")
public class Category extends AbstractEntity {

    @Column(name = "name")
    private String name;

}
