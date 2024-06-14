package com.cosmo.vendorservice.vendorBusinesshour.service.Impl;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.authentication.user.entity.Vendor;
import com.cosmo.authentication.user.repo.VendorRepository;
import com.cosmo.vendorservice.vendorBusinesshour.entity.BusinessHours;
import com.cosmo.vendorservice.vendorBusinesshour.mapper.BusinessHoursMapper;
import com.cosmo.vendorservice.vendorBusinesshour.model.SetBusinessHour;
import com.cosmo.vendorservice.vendorBusinesshour.repo.BusinessHoursRepository;
import com.cosmo.vendorservice.vendorBusinesshour.service.BusinessHourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessHourServiceImpl implements BusinessHourService {
    private final BusinessHoursRepository businessHoursRepository;
    private final BusinessHoursMapper businessHoursMapper;
    private final VendorUserRepository vendorUserRepository;
    private final VendorRepository vendorRepository;
    @Override
    public Mono<ApiResponse<?>> addBusinessHour(List<SetBusinessHour> setBusinessHours, Principal connectedUser) {
        VendorUser vendorUser = vendorUserRepository.findVendorUserByusername(connectedUser.getName());
        Vendor vendor = vendorRepository.findByVendorUsers(vendorUser);
        Long vendorId = vendor.getId();
        setBusinessHours.forEach(setBusinessHour -> {
            BusinessHours businessHours = businessHoursMapper.toEntity(setBusinessHour, vendorId);
            businessHoursRepository.save(businessHours);
        });
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Business hour added"));
    }
}
