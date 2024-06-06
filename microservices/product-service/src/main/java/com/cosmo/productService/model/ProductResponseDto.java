package com.cosmo.productService.model;

import lombok.Data;

@Data
public class ProductResponseDto {
    private String name;
    private double price;
    private String description;
    private int quantity;
    private String categoryName;
    private String image;
    private String updatedBy;
}
