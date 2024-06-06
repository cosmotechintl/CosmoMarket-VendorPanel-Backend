package com.cosmo.productService.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product extends AbstractEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id",foreignKey = @ForeignKey(name="FK_PRODUCT_CATEGORY"))
    private Category category;

    @Column(name="image")
    private String image;

    @Column(name = "updated_by")
    private String updatedBy;
}
