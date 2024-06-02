package com.cosmo.adminservice.accessGroup.mapper;

import com.cosmo.adminservice.accessGroup.model.AccessGroupModel;
import com.cosmo.adminservice.accessGroup.model.AccessGroupResponse;
import com.cosmo.authentication.role.entity.AccessGroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccessGroupMapper {
    AccessGroup dtoToEntity(AccessGroupModel accessGroupModel);
    AccessGroupResponse entityToDto(AccessGroup accessGroup);
}
