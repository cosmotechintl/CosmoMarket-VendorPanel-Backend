package com.cosmo.productService.mapper;

import com.cosmo.productService.entity.Product;
import com.cosmo.productService.model.ProductRequestDto;
import com.cosmo.productService.model.ProductResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product dtotoEntity(ProductRequestDto productRequestDto);

    ProductResponseDto entityToDto(Product product);
}
