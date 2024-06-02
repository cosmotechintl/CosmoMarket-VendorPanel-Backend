package com.cosmo.adminservice.accessGroup.controller;

import com.cosmo.adminservice.accessGroup.model.AccessGroupResponse;
import com.cosmo.adminservice.accessGroup.service.AccessGroupService;
import com.cosmo.authentication.role.entity.AccessGroup;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.adminservice.accessGroup.model.AccessGroupModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = ApiConstant.ACCESS_GROUP)
@RequiredArgsConstructor
public class AccessGroupController {
    private final AccessGroupService accessGroupService;

    @PostMapping(path = ApiConstant.CREATE_ACCESS_GROUP)
    public ResponseEntity<AccessGroupResponse> createAccessGroup(@RequestBody AccessGroupModel accessGroupModel) {
        return ResponseEntity.ok(accessGroupService.createAccessGroup(accessGroupModel));
    }
}