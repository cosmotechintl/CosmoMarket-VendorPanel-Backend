package com.cosmo.futsalService.futsal.mapper;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.futsalService.futsal.entity.Futsal;
import com.cosmo.futsalService.futsal.model.CreateFutsalModel;
import com.cosmo.futsalService.futsal.model.FutsalDto;
import com.cosmo.futsalService.futsal.repo.FutsalRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class FutsalMapper {
    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected FutsalRepository futsalRepository;
    public Futsal mapToEntity(CreateFutsalModel create,String code){
        Futsal futsal =  new Futsal();
        futsal.setName(create.getName());
        futsal.setDescription(create.getDescription());
        futsal.setPrice(create.getPrice());
        futsal.setImage(create.getImage());
        futsal.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
        futsal.setLocation(create.getLocation());
        futsal.setVendorCode(code);
        return futsal;
    }
    public abstract FutsalDto getFutsalDetails(Futsal Futsal);
}
