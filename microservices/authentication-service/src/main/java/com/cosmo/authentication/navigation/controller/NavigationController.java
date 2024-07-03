package com.cosmo.authentication.navigation.controller;

import com.cosmo.authentication.navigation.service.NavigationService;
import com.cosmo.authentication.role.service.RolesService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
@RequestMapping(ApiConstant.NAVIGATION)
@RequiredArgsConstructor
public class NavigationController {

    private final NavigationService navigationService;

    @GetMapping
    public Mono<ApiResponse> getAllNavigation() {
        return navigationService.getAllNavigation();
    }
}
