package com.cosmo.productsservice.product.service.impl;

import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.productsservice.product.entity.Product;
import com.cosmo.productsservice.product.mapper.ProductMapper;
import com.cosmo.productsservice.product.model.CreateProductModel;
import com.cosmo.productsservice.product.model.FetchProductDetail;
import com.cosmo.productsservice.product.model.ProductDetailDto;
import com.cosmo.productsservice.product.model.SearchProductResponse;
import com.cosmo.productsservice.product.model.request.DeleteProductRequest;
import com.cosmo.productsservice.product.model.request.UpdateProductRequest;
import com.cosmo.productsservice.product.repository.ProductRepository;
import com.cosmo.productsservice.product.repository.ProductSearchRepository;
import com.cosmo.productsservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final StatusRepository statusRepository;
    private final ProductSearchRepository productSearchRepository;
    private final SearchResponse searchResponse;

    @Override
    public Mono<ApiResponse> createProduct(CreateProductModel createProductModel) {
        productMapper.mapToEntity(createProductModel);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product created successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> deleteProduct(DeleteProductRequest deleteProductRequest) {
        Optional<Product> checkProduct = productRepository.findByCode(deleteProductRequest.getCode());
        if (checkProduct.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Product not found"));
        } else {
            Product product = checkProduct.get();
            if (StatusConstant.BLOCKED.getName().equals(product.getStatus().getName()) || StatusConstant.DELETED.getName().equals(product.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Product not found"));
            }
            product.setStatus(statusRepository.findByName(StatusConstant.DELETED.getName()));
            product.setInStock(false);
            product.setUpdatedAt(new Date());
            productRepository.save(product);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product deleted successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> viewProductDetail(FetchProductDetail fetchProductDetail) {
        Optional<Product> checkProduct = productRepository.findByCode(fetchProductDetail.getCode());
        if (checkProduct.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Product not found"));
        } else {
            ProductDetailDto productDetailDto = productMapper.getProductDetails(checkProduct.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(productDetailDto, "Product details fetched successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> viewProductList(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<Product, SearchProductResponse> responseBuilder = SearchResponseWithMapperBuilder.<Product, SearchProductResponse>builder()
                .count(productSearchRepository::count).searchData(productSearchRepository::getAll)
                .mapperFunction(this.productMapper::getProductResponses).searchParam(searchParam)
                .build();
        PageableResponse<SearchProductResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Products fetched successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> updateProduct(UpdateProductRequest updateProductRequest) {
        Optional<Product> existedProduct = productRepository.findByCode(updateProductRequest.getCode());
        if(existedProduct.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("Product not found"));
        }
        else {
            Product product= existedProduct.get();
            if(StatusConstant.BLOCKED.getName().equals(product.getStatus().getName()) || StatusConstant.DELETED.getName().equals(product.getStatus().getName())){
                return Mono.just(ResponseUtil.getNotFoundResponse("Product not found"));
            }
            else {
                Product updatedProduct = productMapper.updateProduct(updateProductRequest,product);
                productRepository.save(updatedProduct);
            }
        }
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product updated successfully"));
    }
}
