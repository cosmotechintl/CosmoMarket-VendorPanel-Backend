package com.cosmo.vendorservice.futsalService.service.impl;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.constant.ServiceConstant;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.vendorservice.config.AbstractConnectorService;
import com.cosmo.vendorservice.config.ConnectorService;
import com.cosmo.vendorservice.config.PropertiesFileValue;
import com.cosmo.vendorservice.futsalService.model.CreateFutsalModel;
import com.cosmo.vendorservice.futsalService.service.FutsalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Qualifier(ServiceConstant.FUTSAL_SERVICE)
@RequiredArgsConstructor
@Service
public class FutsalConnectorServiceImpl extends AbstractConnectorService implements FutsalService, ConnectorService {
    private final PropertiesFileValue propertiesFileValue;
    private final VendorUserRepository vendorUserRepository;
    private final StatusRepository statusRepository;

    @Override
    protected String getBaseUrl() {
        return propertiesFileValue.getFutsalServiceUrl();
    }

    @Override
    public Mono<ApiResponse<Object>> createFutsal(CreateFutsalModel createFutsalModel, Principal connectedUser) {
        Optional<VendorUser> checkVendorUSer = vendorUserRepository.findByUsername(connectedUser.getName());
        if (checkVendorUSer.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor user not found"));
        }
        VendorUser vendorUser = checkVendorUSer.get();
        String code = vendorUser.getVendor().getCode();
        createFutsalModel.setVendorCode(code);
        createFutsalModel.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
        return connectToService(createFutsalModel,
                ApiConstant.FUTSAL+ApiConstant.SLASH+ApiConstant.CREATE,
                new ParameterizedTypeReference<>(){
                }
        );
    }
}
