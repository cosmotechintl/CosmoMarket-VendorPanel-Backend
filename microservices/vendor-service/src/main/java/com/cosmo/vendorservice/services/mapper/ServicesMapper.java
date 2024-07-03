package com.cosmo.vendorservice.services.mapper;

import com.cosmo.vendorservice.services.entity.Services_Details;
import com.cosmo.vendorservice.services.model.SearchServicesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ServicesMapper {
    public abstract SearchServicesResponse entityToResponse(Services_Details servicesDetails);

    public List<SearchServicesResponse> getServicesResponses(List<Services_Details> servicesList) {
        return servicesList.stream().map(this::entityToResponse).collect(toList());
    }
}

