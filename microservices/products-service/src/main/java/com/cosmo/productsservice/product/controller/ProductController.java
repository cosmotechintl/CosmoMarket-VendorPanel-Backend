package com.cosmo.productsservice.product.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.productsservice.product.model.CreateProductModel;
import com.cosmo.productsservice.product.model.FetchProductDetail;
import com.cosmo.productsservice.product.model.request.DeleteProductRequest;
import com.cosmo.productsservice.product.model.request.UpdateProductRequest;
import com.cosmo.productsservice.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> createProduct(@RequestBody @Valid CreateProductModel create) {
        return productService.createProduct(create);
    }

    @PostMapping(ApiConstant.DELETE)
    public Mono<ApiResponse<?>> deleteProduct(@RequestBody @Valid DeleteProductRequest request) {
        return productService.deleteProduct(request);
    }

    @PostMapping(ApiConstant.GET + ApiConstant.SLASH + ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> viewProductDetail(@RequestBody @Valid FetchProductDetail fetchProductDetail){
        return productService.viewProductDetail(fetchProductDetail);
    }
    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> viewProductsList(@RequestBody @Valid SearchParam searchParam){
        return productService.viewProductList(searchParam);
    }
    @PostMapping(ApiConstant.UPDATE)
    public Mono<ApiResponse<?>> updateProduct(@RequestBody @Valid UpdateProductRequest request){
        return productService.updateProduct(request);
    }
}
