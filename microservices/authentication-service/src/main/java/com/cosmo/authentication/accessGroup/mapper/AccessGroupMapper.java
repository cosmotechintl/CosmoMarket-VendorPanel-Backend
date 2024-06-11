package com.cosmo.authentication.accessgroup.mapper;

import com.cosmo.authentication.accessgroup.entity.AccessGroup;
import com.cosmo.authentication.accessgroup.model.AccessGroupDetailDto;
import com.cosmo.authentication.accessgroup.model.CreateAccessGroupModel;
import com.cosmo.authentication.accessgroup.model.SearchAccessGroupResponse;
import com.cosmo.authentication.accessgroup.model.UpdateAccessGroupModel;
import com.cosmo.authentication.accessgroup.repo.AccessGroupRepository;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.mapper.StatusMapper;
import com.cosmo.common.repository.StatusRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AccessGroupMapper {

    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected StatusMapper statusMapper;

    @Autowired
    protected AccessGroupRepository accessGroupRepository;

    public AccessGroup toEntity(CreateAccessGroupModel createAccessGroupModel) {
        AccessGroup accessGroup = new AccessGroup();
        accessGroup.setName(createAccessGroupModel.getName());
        accessGroup.setDescription(createAccessGroupModel.getDescription());
        accessGroup.setCreatedAt(new Date());
        accessGroup.setSuperAdminGroup(false);
        accessGroup.setType(createAccessGroupModel.getType());
        accessGroup.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
        return accessGroupRepository.save(accessGroup);
    }

    public abstract SearchAccessGroupResponse entityToDto(AccessGroup accessGroup);
//    {
//        SearchAccessGroupResponse response = new SearchAccessGroupResponse();
//        response.setName(accessGroup.getName());
//        response.setDescription(accessGroup.getDescription());
//        response.setCreatedAt(accessGroup.getCreatedAt());
//        response.setUpdatedAt(accessGroup.getUpdatedAt());
//        response.setStatus(statusMapper.entityToStatusDto(accessGroup.getStatus()));
//        response.setIsSuperAdminGroup(accessGroup.isSuperAdminGroup());
//        response.setRemarks(accessGroup.getRemarks());
//        return response;
//    }

    public List<SearchAccessGroupResponse> getAccessGroupResponses(List<AccessGroup> accessGroups) {
        return accessGroups.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public abstract AccessGroupDetailDto getAccessGroupDetailDto(AccessGroup accessGroup);


    public AccessGroup updatetoEntity(AccessGroup accessGroup,UpdateAccessGroupModel updateAccessGroupModel) {
        accessGroup.setName(updateAccessGroupModel.getName());
        accessGroup.setDescription(updateAccessGroupModel.getDescription());
        accessGroup.setUpdatedAt(new Date());
        return accessGroup;
    }
}
