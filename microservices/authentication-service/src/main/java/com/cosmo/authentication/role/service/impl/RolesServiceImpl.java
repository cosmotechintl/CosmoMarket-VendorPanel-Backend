package com.cosmo.authentication.role.service.impl;

import com.cosmo.authentication.role.entity.Roles;
import com.cosmo.authentication.role.mapper.GroupRoleBuilder;
import com.cosmo.authentication.role.model.RolesResponse;
import com.cosmo.authentication.role.repository.RolesRepository;
import com.cosmo.authentication.role.service.RolesService;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;
    private final GroupRoleBuilder groupRoleBuilder;

    @Override
    public List<Roles> getAllRoles() {
        return rolesRepository.getAllRoles();
    }

    @Override
    public Mono<ApiResponse<?>> getAllAvailableRoles() {
        List<Roles> rolesList = rolesRepository.findAll();
        List<RolesResponse> data = groupRoleBuilder.buildRolesResponse(rolesList, new ArrayList<>());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(data, "Navigation fetched succesfully."));
    }
}
