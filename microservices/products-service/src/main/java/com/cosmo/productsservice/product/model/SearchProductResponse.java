package com.cosmo.productsservice.product.model;

import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import com.cosmo.productsservice.category.model.ProductCategoryDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchProductResponse extends ModelBase {
    private String name;
    private String description;
    private String price;
    private String brand;
    private String color;
    private String size;
    private String code;
    private String quantity;
    private String inStock;
    private ProductCategoryDto category;
    private StatusDto status;
    private String createdAt;
}
