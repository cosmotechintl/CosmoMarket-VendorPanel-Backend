package com.cosmo.futsalService.businesshour.service.Impl;

import com.cosmo.common.exception.NotFoundException;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.futsalService.businesshour.entity.BusinessHours;
import com.cosmo.futsalService.businesshour.mapper.BusinessHoursMapper;
import com.cosmo.futsalService.businesshour.model.*;
import com.cosmo.futsalService.businesshour.repo.BusinessHoursRepository;
import com.cosmo.futsalService.businesshour.service.BusinessHourService;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessHourServiceImpl implements BusinessHourService {
    private final BusinessHoursRepository businessHoursRepository;
    private final BusinessHoursMapper businessHoursMapper;

    @Override
    public Mono<ApiResponse> addBusinessHour(CreateBusinessHourRequestModel createBusinessHourRequestModel) {
        String vendorCode = createBusinessHourRequestModel.getVendorCode();
        DayOfWeek day = DayOfWeek.valueOf(createBusinessHourRequestModel.getDay().toUpperCase());

        Optional<BusinessHours> existingBusinessHour = businessHoursRepository.findByVendorCodeAndDay(vendorCode, day);

        if (existingBusinessHour.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse(("Business hour already exists for the specified vendor and day")));
        }

        BusinessHours businessHours = businessHoursMapper.mapToEntity(createBusinessHourRequestModel);
        businessHoursRepository.save(businessHours);

        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Business hours created successfully"));
    }


    @Override
    public Mono<ApiResponse> updateBusinessHour(UpdateBusinessHourModel updateBusinessHourModel) {
        String vendorCode = updateBusinessHourModel.getVendorCode();
        DayOfWeek day = DayOfWeek.valueOf(updateBusinessHourModel.getDay().toUpperCase());

        BusinessHours existingBusinessHour = businessHoursRepository.findByVendorCodeAndDay(vendorCode, day)
                .orElseThrow(() -> new NotFoundException("No business hours found for the specified vendor and day"));

        BusinessHours updatedBusinessHour = businessHoursMapper.updateToEntity(updateBusinessHourModel, existingBusinessHour, vendorCode);

        businessHoursRepository.save(updatedBusinessHour);

        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Business hour updated successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> getVendorsBusinessHours(BusinessHourDetailModel businessHourDetailModel) {
        String vendorCode = businessHourDetailModel.getVendorCode();
        List<BusinessHours> businessHours = businessHoursRepository.findByVendorCode(vendorCode);
        List<BusinessHourDetailModel> businessHourDetails = businessHours.stream()
                .map(businessHoursMapper::toDetailModel)
                .collect(Collectors.toList());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(businessHourDetails,"Business Hours Fetched successfully"));
    }
    @Override
    public Mono<ApiResponse<?>> getBusinessHours(SearchParam searchParam) {
        return Mono.fromCallable(() -> {
            List<BusinessHours> businessHoursList = businessHoursRepository.findAll();
            if (businessHoursList.isEmpty()) {
                throw new NotFoundException("No business hours found");
            }
            List<BusinessHourDetailModel> businessHourDetails = businessHoursList.stream()
                    .map(businessHoursMapper::toDetailModel)
                    .collect(Collectors.toList());
            return ResponseUtil.getSuccessfulApiResponse(businessHourDetails, "Business hours fetched successfully");
        });
    }

}
