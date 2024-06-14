package com.cosmo.authentication.role.service;

import com.cosmo.authentication.role.entity.Roles;
import com.cosmo.common.model.ApiResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RolesService {

    List<Roles> getAllRoles();

    Mono<ApiResponse<?>> getAllAvailableRoles();

}
