package com.cosmo.productService.service.serviceImpl;

import com.cosmo.authentication.core.service.JwtService;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.PaginationUtil;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.productService.entity.Product;
import com.cosmo.productService.mapper.ProductMapper;
import com.cosmo.productService.model.ProductRequestDto;
import com.cosmo.productService.model.ProductResponseDto;
import com.cosmo.productService.repo.ProductRepository;
import com.cosmo.productService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final JwtService jwtService;

    @Override
    public ApiResponse<ProductResponseDto> createProduct(ProductRequestDto productRequestDto, String token) {
        String username = jwtService.extractUsername(token);
        productRequestDto.setUpdatedBy(username);

        Product product = productMapper.dtotoEntity(productRequestDto);
        Product savedProduct = productRepository.save(product);
        ProductResponseDto productResponseDto = productMapper.entityToDto(savedProduct);
        return ResponseUtil.getSuccessfulApiResponseWithData(productResponseDto, "Product created successfully");
    }

    @Override
    @Transactional
    public ApiResponse deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product does not exist"));
        String productName = product.getName();
        productRepository.deleteById(id);
        return ResponseUtil.getSuccessfulApiResponse("Product " + productName + " deleted successfully.");
    }

    @Override
    public ApiResponse getallProducts(SearchParam searchParam) {
        Pageable pageable = PaginationUtil.getPageable(searchParam);
        Page<Product> pageList = productRepository.findAll(pageable);
        List<Product> allProducts = pageList.getContent();
        if (allProducts.isEmpty()) {
            return ResponseUtil.getFailureResponse("No Products found");
        } else {
            List<ProductResponseDto> productResponseDtos = allProducts.stream()
                    .map(productMapper::entityToDto)
                    .collect(Collectors.toList());
            return ResponseUtil.getSuccessfulApiResponse(productResponseDtos, "Products fetched successfully");
        }
    }

    @Override
    public ApiResponse<ProductResponseDto> updateProduct(ProductRequestDto productRequestDto, String token) {
        return null;
    }

}
