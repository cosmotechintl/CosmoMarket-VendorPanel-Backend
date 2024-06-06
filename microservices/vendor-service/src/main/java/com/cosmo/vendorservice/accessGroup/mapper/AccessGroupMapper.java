package com.cosmo.vendorservice.accessGroup.mapper;

import com.cosmo.vendorservice.accessGroup.model.AccessGroupModel;
import com.cosmo.vendorservice.accessGroup.model.AccessGroupResponse;
import com.cosmo.authentication.role.entity.AccessGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessGroupMapper {
    AccessGroup dtoToEntity(AccessGroupModel accessGroupModel);
    AccessGroupResponse entityToDto(AccessGroup accessGroup);
}
