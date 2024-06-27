package com.cosmo.productsservice.product.model.request;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteProductRequest extends ModelBase {
    @NotBlank(message = "Code cannot be empty")
    private String code;
}
