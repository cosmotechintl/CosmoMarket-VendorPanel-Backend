package com.cosmo.vendorservice.vendorBusinesshour.mapper;

import com.cosmo.vendorservice.vendorBusinesshour.entity.BusinessHours;
import com.cosmo.vendorservice.vendorBusinesshour.model.SetBusinessHour;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BusinessHoursMapper {
    public abstract BusinessHours toEntity(SetBusinessHour setBusinessHour, Long vendorId);
}

