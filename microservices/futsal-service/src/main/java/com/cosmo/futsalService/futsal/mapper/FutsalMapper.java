package com.cosmo.futsalService.futsal.mapper;

import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.futsalService.futsal.entity.Futsal;
import com.cosmo.futsalService.futsal.model.CreateFutsalModel;
import com.cosmo.futsalService.futsal.model.FutsalDto;
import com.cosmo.futsalService.futsal.model.SearchFutsalResponse;
import com.cosmo.futsalService.futsal.repo.FutsalRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class FutsalMapper {
    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected FutsalRepository futsalRepository;
    public Futsal mapToEntity(CreateFutsalModel create){
        Futsal futsal =  new Futsal();
        futsal.setName(create.getName());
        futsal.setDescription(create.getDescription());
        futsal.setPrice(create.getPrice());
        futsal.setImage(create.getImage());
        futsal.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
        futsal.setLocation(create.getLocation());
        return futsal;
    }
    public abstract FutsalDto getFutsalDetails(Futsal Futsal);
    public abstract SearchFutsalResponse entityToResponse(Futsal futsal);
    public List<SearchFutsalResponse> getFutsalResponses(List<Futsal> futsalList){
        return futsalList.stream().map(this::entityToResponse).collect(toList());
    }
}
