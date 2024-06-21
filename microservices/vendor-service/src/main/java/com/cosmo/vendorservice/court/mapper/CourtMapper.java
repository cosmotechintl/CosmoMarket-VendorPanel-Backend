package com.cosmo.vendorservice.court.mapper;

import com.cosmo.common.repository.StatusRepository;
import com.cosmo.vendorservice.core.constant.StatusConstant;
import com.cosmo.vendorservice.court.entity.CourtDetails;
import com.cosmo.vendorservice.court.model.CreateCourtRequestModel;
import com.cosmo.vendorservice.court.model.UpdateCourtRequest;
import com.cosmo.common.entity.Status;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CourtMapper {

        @Autowired
        StatusRepository statusRepository;

     public CourtDetails toEntity(CreateCourtRequestModel model) {
        CourtDetails courtDetails = new CourtDetails();
        courtDetails.setName(model.getName());
        courtDetails.setImage(model.getImage());
        courtDetails.setDescription(model.getDescription());
        courtDetails.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
        return courtDetails;
    }

    public static void updateEntity(CourtDetails entity, UpdateCourtRequest model) {
        entity.setName(model.getName());
        entity.setImage(model.getImage());
        entity.setDescription(model.getDescription());

    }
}
