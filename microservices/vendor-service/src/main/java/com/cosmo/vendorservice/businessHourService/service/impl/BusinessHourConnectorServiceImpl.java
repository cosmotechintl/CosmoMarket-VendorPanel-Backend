package com.cosmo.vendorservice.businessHourService.service.impl;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.constant.ServiceConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.vendorservice.businessHourService.model.BusinessHourDetailModel;
import com.cosmo.vendorservice.businessHourService.model.BusinessHourRequest;
import com.cosmo.vendorservice.businessHourService.model.UpdateBusinessHourModel;
import com.cosmo.vendorservice.businessHourService.service.BusinessHourService;
import com.cosmo.vendorservice.config.AbstractConnectorService;
import com.cosmo.vendorservice.config.ConnectorService;
import com.cosmo.vendorservice.config.PropertiesFileValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Qualifier(ServiceConstant.BUSINESS_HOUR_SERVICE)
@RequiredArgsConstructor
@Service
public class BusinessHourConnectorServiceImpl extends AbstractConnectorService implements BusinessHourService, ConnectorService {
    private final PropertiesFileValue propertiesFileValue;
    private final VendorUserRepository vendorUserRepository;

    @Override
    protected String getBaseUrl() {
        return propertiesFileValue.getBusinessHourServiceUrl();
    }


    @Override
    public Mono<ApiResponse<Object>> addBusinessHour(BusinessHourRequest businessHourRequests, Principal connectedUser) {
        Optional<VendorUser> checkVendorUser = vendorUserRepository.findByUsername(connectedUser.getName());
        if (checkVendorUser.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor user not found"));
        }
        VendorUser vendorUser = checkVendorUser.get();
        String code = vendorUser.getVendor().getCode();
        businessHourRequests.setVendorCode(code);
        return connectToService(businessHourRequests,
                ApiConstant.BUSINESS_HOUR+ApiConstant.SLASH+ApiConstant.CREATE,
                new ParameterizedTypeReference<>(){
                }
        );
    }

    @Override
    public Mono<ApiResponse<Object>> updateBusinessHour(UpdateBusinessHourModel updateBusinessHourModels, Principal connectedUser) {
        Optional<VendorUser> checkVendorUser = vendorUserRepository.findByUsername(connectedUser.getName());
        if (checkVendorUser.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor user not found"));
        }
        VendorUser vendorUser = checkVendorUser.get();
        String code = vendorUser.getVendor().getCode();
        updateBusinessHourModels.setVendorCode(code);
        return connectToService(updateBusinessHourModels,
                ApiConstant.BUSINESS_HOUR+ApiConstant.SLASH+ApiConstant.EDIT,
                new ParameterizedTypeReference<>(){
                }
        );
    }

    @Override
    public Mono<ApiResponse<Object>> getVendorBusinessHours(BusinessHourDetailModel businessHourDetailModel,Principal connectedUser) {
        Optional<VendorUser> checkVendorUser = vendorUserRepository.findByUsername(connectedUser.getName());
        if (checkVendorUser.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor user not found"));
        }
        VendorUser vendorUser = checkVendorUser.get();
        String code = vendorUser.getVendor().getCode();
        businessHourDetailModel.setVendorCode(code);
        return connectToService(businessHourDetailModel,
                ApiConstant.BUSINESS_HOUR+ApiConstant.SLASH+ApiConstant.DETAIL,
                new ParameterizedTypeReference<>(){
                }
        );
    }

    @Override
    public Mono<ApiResponse<Object>> getBusinessHours(SearchParam searchParam) {
        return connectToService(searchParam,
                ApiConstant.BUSINESS_HOUR ,
                new ParameterizedTypeReference<>() {
                }
        );
    }


}
