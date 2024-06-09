package com.cosmo.productService.model;

import lombok.Data;

@Data
public class ProductRequestDto {
    private Long id;
    private String name;
    private double price;
    private String description;
    private String brand;
    private String color;
    private String size;
    private String status;
    private int quantity;
    private Long categoryId;
    private String image;
    private String updatedBy;
}
