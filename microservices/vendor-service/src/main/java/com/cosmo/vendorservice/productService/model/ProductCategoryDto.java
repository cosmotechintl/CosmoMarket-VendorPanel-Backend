package com.cosmo.vendorservice.productService.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategoryDto extends ModelBase {
    @NotBlank(message = "Category name cannot be empty")
    private String name;
}
