package com.cosmo.productsservice.product.mapper;

import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.productsservice.category.repo.ProductCategoryRepository;
import com.cosmo.productsservice.product.entity.Product;
import com.cosmo.productsservice.product.model.CreateProductModel;
import com.cosmo.productsservice.product.model.ProductDetailDto;
import com.cosmo.productsservice.product.model.SearchProductResponse;
import com.cosmo.productsservice.product.model.request.UpdateProductRequest;
import com.cosmo.productsservice.product.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

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
            product.setVendorCode(create.getVendorCode());
            productRepository.save(product);
            return product;
        }

    public abstract ProductDetailDto getProductDetails(Product product);

    public abstract SearchProductResponse entityToRes(Product product);
    public List<SearchProductResponse> getProductResponses(List<Product> products) {
        return products.stream().map(this::entityToRes).collect(toList());
    }
    public Product updateProduct(UpdateProductRequest request, Product product){
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImage(request.getImage());
        product.setQuantity(request.getQuantity());
        return product;
    }

}
