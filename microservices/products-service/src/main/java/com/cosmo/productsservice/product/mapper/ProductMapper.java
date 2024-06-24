package com.cosmo.productsservice.product.mapper;

import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.productsservice.category.repo.ProductCategoryRepository;
import com.cosmo.productsservice.product.entity.Product;
import com.cosmo.productsservice.product.model.CreateProductModel;
import com.cosmo.productsservice.product.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ProductMapper {

    @Autowired
    protected ProductCategoryRepository productCategoryRepository;
    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected StatusRepository statusRepository;

    public Product mapToEntity(CreateProductModel create){
            Product product= new Product();
            product.setName(create.getName());
            product.setDescription(create.getDescription());
            product.setPrice(create.getPrice());
            product.setBrand(create.getBrand());
            product.setColor(create.getColor());
            product.setImage(create.getImage());
            product.setSize(create.getSize());
            product.setQuantity(create.getQuantity());
            product.setCategory(productCategoryRepository.findByName(create.getCategory().getName()).orElseThrow(
                    ()-> new ResourceNotFoundException("Category not found")
            ));
            product.setCreatedAt(new Date());
            product.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
            product.setCode(UUID.randomUUID().toString());

            productRepository.save(product);
            return product;
        }
}
