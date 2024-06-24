package com.cosmo.productsservice.category.model.request;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryRequest extends ModelBase {
    private Long id;

    @NotBlank(message = "name cannot be null")
    private String name;

    @NotBlank(message = "description cannot be null")
    private String description;
}