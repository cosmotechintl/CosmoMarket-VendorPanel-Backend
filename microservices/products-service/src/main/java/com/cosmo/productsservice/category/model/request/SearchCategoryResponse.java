package com.cosmo.productsservice.category.model.request;

import com.cosmo.authentication.vendor.model.CategoryDto;
import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCategoryResponse extends ModelBase {
    private String name;
    private String code;
    private String createdAt;
    private StatusDto status;
}
