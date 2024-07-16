package com.cosmo.futsalService.businesshour.mapper;

import com.cosmo.common.exception.BadRequestException;
import com.cosmo.common.exception.NotFoundException;
import com.cosmo.futsalService.businesshour.entity.BusinessHours;
import com.cosmo.futsalService.businesshour.model.BusinessHourDetailModel;
import com.cosmo.futsalService.businesshour.model.CreateBusinessHourRequestModel;
import com.cosmo.futsalService.businesshour.model.UpdateBusinessHourModel;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BusinessHoursMapper {

    public BusinessHours mapToEntity(CreateBusinessHourRequestModel createBusinessHourRequestModel) {
        if (createBusinessHourRequestModel == null) {
            return null;
        }
        BusinessHours businessHours = new BusinessHours();
        businessHours.setVendorCode(createBusinessHourRequestModel.getVendorCode());

        if (createBusinessHourRequestModel.getDay() != null) {
            businessHours.setDay(DayOfWeek.valueOf(createBusinessHourRequestModel.getDay().toUpperCase()));
        }

        if (createBusinessHourRequestModel.isClosed()) {
            if (createBusinessHourRequestModel.getStartTime() != null || createBusinessHourRequestModel.getEndTime() != null) {
                throw new BadRequestException("Start time and end time should not be provided when also marked as closed");
            }
        } else {
            if (createBusinessHourRequestModel.getStartTime() != null) {
                if (createBusinessHourRequestModel.getEndTime() == null) {
                    throw new BadRequestException("End time must be provided when start time is provided");
                }
                businessHours.setStartTime(LocalTime.parse(createBusinessHourRequestModel.getStartTime()));
                businessHours.setEndTime(LocalTime.parse(createBusinessHourRequestModel.getEndTime()));
            } else if (createBusinessHourRequestModel.getEndTime() != null) {
                throw new BadRequestException("End time must be provided when start time is provided");
            }
        }
        businessHours.setClosed(createBusinessHourRequestModel.isClosed());
        businessHours.setUuid(UUID.randomUUID().toString());

        return businessHours;
    }



    public BusinessHours updateToEntity(UpdateBusinessHourModel updateBusinessHourModel, BusinessHours businessHours, String vendorCode) {
        if (updateBusinessHourModel != null) {
            if (updateBusinessHourModel.getStartTime() != null) {
                businessHours.setStartTime(LocalTime.parse(updateBusinessHourModel.getStartTime()));
            }
            if (updateBusinessHourModel.getEndTime() != null) {
                businessHours.setEndTime(LocalTime.parse(updateBusinessHourModel.getEndTime()));
            }
            businessHours.setClosed(updateBusinessHourModel.isClosed());
        }
        businessHours.setVendorCode(vendorCode);
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
        String vendorCode = businessHours.getVendorCode();
        BusinessHourDetailModel businessHourDetailModel = new BusinessHourDetailModel( vendorCode, day, startTime, endTime, isClosed );

        businessHourDetailModel.setClosed( businessHours.isClosed() );

        return businessHourDetailModel;
    }
}

