package com.cosmo.vendorservice.businesshour.mapper;

import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.authentication.vendor.repository.VendorRepository;
import com.cosmo.common.exception.BadRequestException;
import com.cosmo.common.exception.NotFoundException;
import com.cosmo.vendorservice.businesshour.entity.BusinessHours;
import com.cosmo.vendorservice.businesshour.model.BusinessHourDetailModel;
import com.cosmo.vendorservice.businesshour.model.BusinessHourRequest;
import com.cosmo.vendorservice.businesshour.model.UpdateBusinessHourModel;
import com.cosmo.vendorservice.businesshour.repo.BusinessHoursRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BusinessHoursMapper {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private BusinessHoursRepository businessHoursRepository;

    public BusinessHours toEntity(BusinessHourRequest businessHourRequest, String vendorCode) {
        if (businessHourRequest == null && vendorCode == null) {
            return null;
        }


        BusinessHours businessHours = new BusinessHours();
        if (businessHourRequest != null) {
            Vendor vendor = vendorRepository.findByCode(vendorCode).orElseThrow(() -> new NotFoundException("Vendor not found"));
            businessHours.setVendor(vendor);
            if (businessHourRequest.getDay() != null) {
                businessHours.setDay(Enum.valueOf(DayOfWeek.class, businessHourRequest.getDay()));
            }
            if (businessHourRequest.isClosed()) {
                if (businessHourRequest.getStartTime() != null || businessHourRequest.getEndTime() != null) {
                    throw new BadRequestException("Start time and end time should not be provided when also marked as closed");
                }
            } else {
                if (businessHourRequest.getStartTime() != null) {
                    if (businessHourRequest.getEndTime() == null) {
                        throw new BadRequestException("End time must be provided when start time is provided");
                    }
                    businessHours.setStartTime(LocalTime.parse(businessHourRequest.getStartTime()));
                    businessHours.setEndTime(LocalTime.parse(businessHourRequest.getEndTime()));
                } else if (businessHourRequest.getEndTime() != null) {
                    throw new BadRequestException("End time must be provided when start time is provided");
                }
            }
            businessHours.setClosed(businessHourRequest.isClosed());
        }


        return businessHoursRepository.save(businessHours);
    }


    public BusinessHours updateToEntity(UpdateBusinessHourModel updateBusinessHourModel, BusinessHours businessHours, String vendorCode) {

        if (updateBusinessHourModel != null) {
            if (updateBusinessHourModel.getDay() != null) {
                businessHours.setDay(Enum.valueOf(DayOfWeek.class, updateBusinessHourModel.getDay()));
            }
            if (updateBusinessHourModel.getStartTime() != null) {
                businessHours.setStartTime(LocalTime.parse(updateBusinessHourModel.getStartTime()));
            }
            if (updateBusinessHourModel.getEndTime() != null) {
                businessHours.setEndTime(LocalTime.parse(updateBusinessHourModel.getEndTime()));
            }
            businessHours.setClosed(updateBusinessHourModel.isClosed());
        }
        businessHours.setVendor(vendorRepository.findByCode(vendorCode).orElseThrow(() -> new NotFoundException("Vendor not found")));
        return businessHours;
    }

    public BusinessHourDetailModel toDetailModel(BusinessHours businessHours){
        if ( businessHours == null ) {
            throw new NotFoundException("Business hour not found");
        }
        String day = null;
        String startTime = null;
        String endTime = null;

        if ( businessHours.getDay() != null ) {
            day = businessHours.getDay().name();
        }
        if ( businessHours.getStartTime() != null ) {
            startTime = DateTimeFormatter.ISO_LOCAL_TIME.format( businessHours.getStartTime() );
        }
        if ( businessHours.getEndTime() != null ) {
            endTime = DateTimeFormatter.ISO_LOCAL_TIME.format( businessHours.getEndTime() );
        }
        boolean isClosed = false;
        String vendorCode = businessHours.getVendor().getCode();
        BusinessHourDetailModel businessHourDetailModel = new BusinessHourDetailModel( vendorCode, day, startTime, endTime, isClosed );

        businessHourDetailModel.setClosed( businessHours.isClosed() );

        return businessHourDetailModel;
    }
}

