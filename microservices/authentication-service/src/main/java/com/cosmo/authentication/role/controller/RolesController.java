package com.cosmo.authentication.role.controller;


import com.cosmo.authentication.role.service.RolesService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.ROLES)
@RequiredArgsConstructor
public class RolesController {

    private final RolesService rolesService;


    @GetMapping
    public Mono<ApiResponse<?>> getAllRoles() {
        return rolesService.getAllAvailableRoles();
    }
}
