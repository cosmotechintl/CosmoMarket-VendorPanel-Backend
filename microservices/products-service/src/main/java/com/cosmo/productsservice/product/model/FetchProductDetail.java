package com.cosmo.productsservice.product.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchProductDetail extends ModelBase {
    @NotBlank(message = "Code cannot be blank")
    private String code;
}
