package com.cosmo.productsservice.product.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import com.cosmo.common.entity.Status;
import com.cosmo.productsservice.category.entity.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "image")
    private String image;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "in_stock")
    private boolean inStock;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @JoinColumn(name = "product_category", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductCategory category;

    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
