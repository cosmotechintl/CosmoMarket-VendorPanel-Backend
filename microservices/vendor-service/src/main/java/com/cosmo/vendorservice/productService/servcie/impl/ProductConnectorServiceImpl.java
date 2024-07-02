package com.cosmo.vendorservice.productService.servcie.impl;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.constant.ServiceConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.vendorservice.config.AbstractConnectorService;
import com.cosmo.vendorservice.config.ConnectorService;
import com.cosmo.vendorservice.config.PropertiesFileValue;
import com.cosmo.vendorservice.productService.model.CreateProductModel;
import com.cosmo.vendorservice.productService.servcie.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Qualifier(ServiceConstant.PRODUCT_SERVICE)
@RequiredArgsConstructor
@Service
public class ProductConnectorServiceImpl extends AbstractConnectorService implements ProductService, ConnectorService {

    private final PropertiesFileValue propertiesFileValue;
    private final VendorUserRepository vendorUserRepository;

    @Override
    protected String getBaseUrl() {
        return propertiesFileValue.getProductServiceUrl();
    }

    @Override
    public Mono<ApiResponse<Object>> createProduct(CreateProductModel createProductModel, Principal connectedVendor) {
        Optional<VendorUser> checkVendor = vendorUserRepository.findByUsername(connectedVendor.getName());
        String code = checkVendor.get().getVendor().getCode();
        createProductModel.setVendorCode(code);
        return connectToService(createProductModel,
                ApiConstant.PRODUCT+ApiConstant.SLASH+ApiConstant.CREATE,
                new ParameterizedTypeReference<>(){
                }
        );

    }
}
