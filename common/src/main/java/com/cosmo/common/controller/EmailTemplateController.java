package com.cosmo.common.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.CreateEmailTemplate;
import com.cosmo.common.service.EmailTemplateService;
import com.cosmo.common.model.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.EMAIL_TEMPLATE)
@RequiredArgsConstructor
public class EmailTemplateController {
    private final EmailTemplateService emailTemplateService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> createEmailTemplate(@RequestBody @Valid CreateEmailTemplate createEmailTemplate, Principal connectedUser){
        return emailTemplateService.createEmailTemplate(createEmailTemplate, connectedUser);
    }
}
