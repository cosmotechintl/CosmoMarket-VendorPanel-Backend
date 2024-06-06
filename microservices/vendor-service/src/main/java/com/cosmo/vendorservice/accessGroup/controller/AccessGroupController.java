package com.cosmo.vendorservice.accessGroup.controller;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.accessGroup.model.AccessGroupResponse;
import com.cosmo.vendorservice.accessGroup.service.AccessGroupService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.vendorservice.accessGroup.model.AccessGroupModel;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ApiResponse<AccessGroupResponse>> createAccessGroup(@RequestBody AccessGroupModel accessGroupModel) {
        return ResponseEntity.ok(accessGroupService.createAccessGroup(accessGroupModel));
    }
}