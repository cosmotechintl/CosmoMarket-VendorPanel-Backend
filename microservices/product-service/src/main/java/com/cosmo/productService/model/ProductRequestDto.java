package com.cosmo.productService.model;

import lombok.Data;

@Data
public class ProductRequestDto {
    private Long id;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private Long categoryId;
    private String image;
    private String updatedBy;
}
