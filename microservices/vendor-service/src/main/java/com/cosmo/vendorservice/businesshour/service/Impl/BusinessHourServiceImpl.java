package com.cosmo.vendorservice.businesshour.service.Impl;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import com.cosmo.common.exception.NotFoundException;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.vendorservice.businesshour.entity.BusinessHours;
import com.cosmo.vendorservice.businesshour.mapper.BusinessHoursMapper;
import com.cosmo.vendorservice.businesshour.model.CreateBusinessHourRequestModel;
import com.cosmo.vendorservice.businesshour.model.UpdateBusinessHourModel;
import com.cosmo.vendorservice.businesshour.repo.BusinessHoursRepository;
import com.cosmo.vendorservice.businesshour.service.BusinessHourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessHourServiceImpl implements BusinessHourService {
    private final BusinessHoursRepository businessHoursRepository;
    private final BusinessHoursMapper businessHoursMapper;
    private final VendorUserRepository vendorUserRepository;

    @Override
    @Transactional
    public Mono<ApiResponse<?>> addBusinessHour(CreateBusinessHourRequestModel businessHourRequests, Principal connectedUser) {

        VendorUser vendorUser = vendorUserRepository.findByUsername(connectedUser.getName())
                .orElseThrow(() -> new NotFoundException("Invalid User"));
        Long vendorId = vendorUser.getVendor().getId();
        log.info("Vendor Id: {}", vendorId);

        businessHourRequests.getBusinessHours().forEach(setBusinessHour -> {
            BusinessHours businessHours = businessHoursMapper.toEntity(setBusinessHour, vendorId);
            log.info("Business Hour: {}", businessHours);

        });
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Business hour added"));
    }

    @Override
    public Mono<ApiResponse<?>> updateBusinessHour(List<UpdateBusinessHourModel> updateBusinessHourModels, Principal connectedUser) {
        VendorUser vendorUser = vendorUserRepository.findByUsername(connectedUser.getName())
                .orElseThrow(() -> new NotFoundException("Invalid User"));
        Long vendorId = vendorUser.getVendor().getId();
        log.info("Vendor Id: {}", vendorId);
        updateBusinessHourModels.forEach(updateBusinessHourModel -> {
            BusinessHours existingBusinessHours = businessHoursRepository.findById(Long.valueOf(updateBusinessHourModel.getId()))
                    .orElseThrow(() -> new NotFoundException("Business hour not found"));
            existingBusinessHours = businessHoursMapper.updatetoEntity(updateBusinessHourModel, existingBusinessHours, vendorId);
            businessHoursRepository.save(existingBusinessHours);

        });
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Business hour updated"));
    }
}