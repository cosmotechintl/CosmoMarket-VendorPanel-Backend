package com.cosmo.productsservice.product.model.request;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequest extends ModelBase {
    @NotBlank(message = "Code cannot be empty")
    private String code;

    @NotBlank(message = "Product name cannot be empty")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Price cannot be empty")
    private double price;

    @NotBlank(message = "Product image cannot be empty")
    private String image;

    @NotNull(message = "Product quantity cannot be empty")
    private int quantity;
}
