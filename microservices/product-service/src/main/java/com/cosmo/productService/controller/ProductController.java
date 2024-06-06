package com.cosmo.productService.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.productService.model.ProductRequestDto;
import com.cosmo.productService.model.ProductResponseDto;
import com.cosmo.productService.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(ApiConstant.CREATE)
    public ResponseEntity<ApiResponse<ProductResponseDto>> createProduct(
            @Valid @RequestBody ProductRequestDto productRequestDto
            , @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.ok(productService.createProduct(productRequestDto, token));
    }

    @DeleteMapping(ApiConstant.DELETE_PRODUCT)
    public ResponseEntity<ApiResponse<String>> deleteProduct(@RequestBody ProductRequestDto productRequestDto) {
        Long pid = productRequestDto.getId();
        return ResponseEntity.ok(productService.deleteProduct(pid));
    }

    @GetMapping(ApiConstant.GET)
    public ResponseEntity<ApiResponse<?>> getAllVendorUsers(@RequestBody SearchParam searchParam) {
        return ResponseEntity.ok().body(productService.getallProducts(searchParam));
    }
}

