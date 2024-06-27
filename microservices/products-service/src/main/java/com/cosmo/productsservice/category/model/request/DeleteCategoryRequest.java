package com.cosmo.productsservice.category.model.request;

import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeleteCategoryRequest extends ModelBase {
    private String name;
}
