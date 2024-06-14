package com.cosmo.vendorservice.vendorBusinesshour.mapper;

import com.cosmo.authentication.user.entity.Vendor;
import com.cosmo.authentication.user.repo.VendorRepository;
import com.cosmo.common.exception.BadRequestException;
import com.cosmo.common.exception.InvalidInputException;
import com.cosmo.common.exception.NotFoundException;
import com.cosmo.vendorservice.vendorBusinesshour.entity.BusinessHours;
import com.cosmo.vendorservice.vendorBusinesshour.model.BusinessHourDetailModel;
import com.cosmo.vendorservice.vendorBusinesshour.model.SetBusinessHour;
import com.cosmo.vendorservice.vendorBusinesshour.model.UpdateBusinessHourModel;
import com.cosmo.vendorservice.vendorBusinesshour.repo.BusinessHoursRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BusinessHoursMapper {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private BusinessHoursRepository businessHoursRepository;
    public BusinessHours toEntity(SetBusinessHour setBusinessHour, Long vendorId){
        if ( setBusinessHour == null && vendorId == null ) {
            throw new InvalidInputException("Empty request");
        }

        BusinessHours businessHours = new BusinessHours();
        if ( setBusinessHour != null ) {

            if ( setBusinessHour.getDay() != null ) {
                businessHours.setDay( Enum.valueOf( DayOfWeek.class, setBusinessHour.getDay() ) );
            }
            if ( setBusinessHour.isClosed() ) {
                if ( setBusinessHour.getStartTime() != null || setBusinessHour.getEndTime() != null ) {
                    throw new BadRequestException("Start time and end time should not be provided when also marked as closed");
                }
            } else {
                if ( setBusinessHour.getStartTime() != null ) {
                    if ( setBusinessHour.getEndTime() == null ) {
                        throw new BadRequestException("End time must be provided when start time is provided");
                    }
                    businessHours.setStartTime( LocalTime.parse( setBusinessHour.getStartTime() ) );
                    businessHours.setEndTime( LocalTime.parse( setBusinessHour.getEndTime() ) );
                }else if (setBusinessHour.getEndTime() != null) {
                    throw new BadRequestException("Start time must be provided when End time is provided");
                }
                else {
                    throw new BadRequestException("Start time and end time must be provided when not marked as closed");
                }
            }
            businessHours.setClosed( setBusinessHour.isClosed() );
        }
        businessHours.setVendor(vendorRepository.findById(vendorId).orElseThrow(() -> new NotFoundException("Vendor not found")));
        return businessHoursRepository.save(businessHours);
    }


    public BusinessHours updatetoEntity(UpdateBusinessHourModel updateBusinessHourModel, BusinessHours businessHours, Long vendorId){

        if ( updateBusinessHourModel == null  && vendorId == null ) {
            throw new InvalidInputException("Empty request");
        }
        if ( updateBusinessHourModel != null ) {
            if ( updateBusinessHourModel.getDay() != null ) {
                businessHours.setDay( Enum.valueOf( DayOfWeek.class, updateBusinessHourModel.getDay() ) );
            }
            if ( updateBusinessHourModel.isClosed() ) {
                if (updateBusinessHourModel.getStartTime() != null || updateBusinessHourModel.getEndTime() != null) {
                    throw new BadRequestException("Start time and end time should not be provided when also marked as closed");
                }
            }else {
                if ( updateBusinessHourModel.getStartTime() != null ) {
                    if ( updateBusinessHourModel.getEndTime() == null ) {
                        throw new BadRequestException("End time must be provided when start time is provided");
                    }
                    businessHours.setStartTime( LocalTime.parse( updateBusinessHourModel.getStartTime() ) );
                    businessHours.setEndTime( LocalTime.parse( updateBusinessHourModel.getEndTime() ) );
                }else if (updateBusinessHourModel.getEndTime() != null) {
                    throw new BadRequestException("Start time must be provided when end time is provided");
                }
            }
            businessHours.setClosed( updateBusinessHourModel.isClosed() );
        }
        businessHours.setVendor(vendorRepository.findById(vendorId).orElseThrow(() -> new NotFoundException("Vendor not found")));
        return businessHoursRepository.save(businessHours);
    }

    public abstract BusinessHourDetailModel toDetailModel(BusinessHours businessHours);
}

