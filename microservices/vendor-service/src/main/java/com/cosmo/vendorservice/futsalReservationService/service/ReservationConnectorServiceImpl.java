package com.cosmo.vendorservice.futsalReservationService.service;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.constant.ServiceConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.vendorservice.config.AbstractConnectorService;
import com.cosmo.vendorservice.config.ConnectorService;
import com.cosmo.vendorservice.config.PropertiesFileValue;
import com.cosmo.vendorservice.futsalReservationService.model.CreateReservationModel;
import com.cosmo.vendorservice.futsalReservationService.model.FetchReservationDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Qualifier(ServiceConstant.RESERVATION_SERVICE)
@RequiredArgsConstructor
@Service
public class ReservationConnectorServiceImpl extends AbstractConnectorService implements ReservationService, ConnectorService {
    private final PropertiesFileValue propertiesFileValue;
    private final VendorUserRepository vendorUserRepository;

    @Override
    protected String getBaseUrl() {
        return propertiesFileValue.getFutsalReservationServiceUrl();
    }

    @Override
    public Mono<ApiResponse<Object>> createReservation(CreateReservationModel createReservationModel, Principal connectedUser) {
        Optional<VendorUser> checkVendorUser = vendorUserRepository.findByUsername(connectedUser.getName());
        if (checkVendorUser.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor user not found"));
        }
        VendorUser vendorUser = checkVendorUser.get();
        String code = vendorUser.getVendor().getCode();
        createReservationModel.setVendorCode(code);
        return connectToService(createReservationModel,
                ApiConstant.RESERVATION+ApiConstant.SLASH+ApiConstant.CREATE,
                new ParameterizedTypeReference<>(){
                }
        );
    }

    @Override
    public Mono<ApiResponse<Object>> getReservationSlots(FetchReservationDetails fetchReservationDetails,Principal connectedUser) {
        Optional<VendorUser> checkVendorUser = vendorUserRepository.findByUsername(connectedUser.getName());
        if (checkVendorUser.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor user not found"));
        }
        VendorUser vendorUser = checkVendorUser.get();
        String code = vendorUser.getVendor().getCode();
        LocalDate date = fetchReservationDetails.getDate();
        fetchReservationDetails.setVendorCode(code);
        fetchReservationDetails.setDate(date);
        return connectToService(fetchReservationDetails,
                ApiConstant.RESERVATION+ApiConstant.SLASH+ApiConstant.GET_ALL_RESERVATION,
                new ParameterizedTypeReference<>(){
                }
        );
    }
}
