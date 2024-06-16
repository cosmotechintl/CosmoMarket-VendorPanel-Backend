package com.cosmo.vendorservice.businesshour.mapper;

import com.cosmo.authentication.user.entity.Vendor;
import com.cosmo.authentication.user.repo.VendorRepository;
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

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BusinessHoursMapper {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private BusinessHoursRepository businessHoursRepository;

    public BusinessHours toEntity(BusinessHourRequest businessHourRequest, Long vendorId){
        if ( businessHourRequest == null && vendorId == null ) {
            return null;
        }

        BusinessHours businessHours = new BusinessHours();
        if ( businessHourRequest != null ) {
            Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(() -> new NotFoundException("Vendor not found"));
            businessHours.setVendor(vendor);
            if ( businessHourRequest.getDay() != null ) {
                businessHours.setDay( Enum.valueOf( DayOfWeek.class, businessHourRequest.getDay() ) );
            }
            if ( businessHourRequest.isClosed() ) {
                if ( businessHourRequest.getStartTime() != null || businessHourRequest.getEndTime() != null ) {
                    throw new BadRequestException("Start time and end time should not be provided when also marked as closed");
                }
            } else {
                if ( businessHourRequest.getStartTime() != null ) {
                    if ( businessHourRequest.getEndTime() == null ) {
                        throw new BadRequestException("End time must be provided when start time is provided");
                    }
                    businessHours.setStartTime( LocalTime.parse( businessHourRequest.getStartTime() ) );
                    businessHours.setEndTime( LocalTime.parse( businessHourRequest.getEndTime() ) );
                }else if (businessHourRequest.getEndTime() != null) {
                    throw new BadRequestException("End time must be provided when start time is provided");
                }
            }
            businessHours.setClosed( businessHourRequest.isClosed() );
        }


        return businessHoursRepository.save(businessHours);
    }


    public BusinessHours updatetoEntity(UpdateBusinessHourModel updateBusinessHourModel, BusinessHours businessHours, Long vendorId){

        if ( updateBusinessHourModel != null ) {
            if ( updateBusinessHourModel.getDay() != null ) {
                businessHours.setDay( Enum.valueOf( DayOfWeek.class, updateBusinessHourModel.getDay() ) );
            }
            if ( updateBusinessHourModel.getStartTime() != null ) {
                businessHours.setStartTime( LocalTime.parse( updateBusinessHourModel.getStartTime() ) );
            }
            if ( updateBusinessHourModel.getEndTime() != null ) {
                businessHours.setEndTime( LocalTime.parse( updateBusinessHourModel.getEndTime() ) );
            }
            businessHours.setClosed( updateBusinessHourModel.isClosed() );
        }
        businessHours.setVendor(vendorRepository.findById(vendorId).orElseThrow(() -> new NotFoundException("Vendor not found")));
        return businessHours;
    }

    public abstract BusinessHourDetailModel toDetailModel(BusinessHours businessHours);
}

