package com.cosmo.productsservice.product.service.impl;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.productsservice.product.mapper.ProductMapper;
import com.cosmo.productsservice.product.model.CreateProductModel;
import com.cosmo.productsservice.product.repository.ProductRepository;
import com.cosmo.productsservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Mono<ApiResponse> createProduct(CreateProductModel createProductModel) {
        productMapper.mapToEntity(createProductModel);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product created successfully"));
    }
}
