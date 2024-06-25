package com.cosmo.productsservice.product.model;

import com.cosmo.common.model.ModelBase;
import com.cosmo.productsservice.category.model.ProductCategoryDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductModel extends ModelBase {
        @NotBlank(message = "Product name cannot be empty")
        private String name;

        @NotBlank(message = "Product description cannot be empty")
        private String description;

        @NotNull(message = "Product price cannot be empty")
        private double price;

        @NotBlank(message = "Brand name cannot be empty")
        private String brand;

        @NotBlank(message = "Product Color cannot be empty")
        private String color;

        @NotBlank(message = "Product image cannot be empty")
        private String image;

        @NotBlank(message = "Product size cannot be empty")
        private String size;

        @NotNull(message = "Product quantity cannot be empty")
        private int quantity;

        @NotNull
        private ProductCategoryDto category;
}
