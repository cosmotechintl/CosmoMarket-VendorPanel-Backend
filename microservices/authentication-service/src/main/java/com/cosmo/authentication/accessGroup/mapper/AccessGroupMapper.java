package com.cosmo.authentication.accessgroup.mapper;

import com.cosmo.authentication.accessgroup.entity.AccessGroup;
import com.cosmo.authentication.accessgroup.entity.Type;
import com.cosmo.authentication.accessgroup.model.AccessGroupDetailDto;
import com.cosmo.authentication.accessgroup.model.CreateAccessGroupModel;
import com.cosmo.authentication.accessgroup.model.SearchAccessGroupResponse;
import com.cosmo.authentication.accessgroup.model.UpdateAccessGroupModel;
import com.cosmo.authentication.accessgroup.repo.AccessGroupRepository;
import com.cosmo.authentication.accessgroup.repo.TypeRepository;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.exception.ConflictException;
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

    @Autowired
    protected TypeRepository typeRepository;

    public AccessGroup toEntity(CreateAccessGroupModel createAccessGroupModel) {
        if (accessGroupRepository.findByName(createAccessGroupModel.getName()).isPresent()) {
            throw new ConflictException("An AccessGroup with this name already exists");
        }
        AccessGroup accessGroup = new AccessGroup();
        accessGroup.setName(createAccessGroupModel.getName());
        accessGroup.setDescription(createAccessGroupModel.getDescription());
        accessGroup.setCreatedAt(new Date());
        accessGroup.setAdminGroup(false);
        accessGroup.setType(typeRepository.findByName(createAccessGroupModel.getType().getName()));
        accessGroup.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
        return accessGroupRepository.save(accessGroup);
    }

    public abstract SearchAccessGroupResponse entityToDto(AccessGroup accessGroup);

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
