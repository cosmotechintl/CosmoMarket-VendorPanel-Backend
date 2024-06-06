package com.cosmo.productService.mapper.impl;

import com.cosmo.common.exception.BadRequestException;
import com.cosmo.common.exception.InvalidInputException;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.productService.entity.Category;
import com.cosmo.productService.entity.Product;
import com.cosmo.productService.mapper.ProductMapper;
import com.cosmo.productService.model.ProductRequestDto;
import com.cosmo.productService.model.ProductResponseDto;
import com.cosmo.productService.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper {
    private final CategoryRepository categoryRepository;

    @Override
    public Product dtotoEntity(ProductRequestDto productRequestDto) {
        if ( productRequestDto == null ) {
            throw new InvalidInputException("No product to add");
        }
        Product product = new Product();
        product.setName(productRequestDto.getName());
        if (productRequestDto.getCategoryId()!= null) {
            Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category does not exist"));
            product.setCategory(category);
        } else {
            throw new BadRequestException("Category cannot be null ");
        }
        product.setPrice(productRequestDto.getPrice());
        product.setDescription(productRequestDto.getDescription());
        product.setQuantity(productRequestDto.getQuantity());
        product.setImage(productRequestDto.getImage());
        product.setUpdatedBy(productRequestDto.getUpdatedBy());
        return product;
    }

    @Override
    public ProductResponseDto entityToDto(Product product) {
        if ( product == null ) {
            throw new ResourceNotFoundException("Product does not exist");
        }
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setQuantity(product.getQuantity());
        productResponseDto.setImage(product.getImage());
        productResponseDto.setCategoryName(product.getCategory().getName());
        productResponseDto.setUpdatedBy(product.getUpdatedBy());
        return productResponseDto;
    }
}
