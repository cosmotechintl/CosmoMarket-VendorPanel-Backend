package com.cosmo.productsservice.category.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "product_category")
public class ProductCategory extends AbstractEntity {

    @Column(name ="name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "code")
    private String code;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
