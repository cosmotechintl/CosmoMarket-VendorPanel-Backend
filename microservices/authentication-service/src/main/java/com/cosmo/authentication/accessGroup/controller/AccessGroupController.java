package com.cosmo.authentication.accessGroup.controller;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.authentication.accessGroup.model.my.AccessGroupListResponseDto;
import com.cosmo.authentication.accessGroup.service.AccessGroupService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.authentication.accessGroup.model.my.AccessGroupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = ApiConstant.ACCESS_GROUP)
@RequiredArgsConstructor
public class AccessGroupController {
    private final AccessGroupService accessGroupService;

    @PostMapping(path = ApiConstant.CREATE)
    public ResponseEntity<ApiResponse<AccessGroupListResponseDto>> createAccessGroup(@RequestBody AccessGroupRequestDto accessGroupRequestDto) {
        return ResponseEntity.ok(accessGroupService.createAccessGroup(accessGroupRequestDto));
    }

    @PostMapping(path = ApiConstant.MODIFY)
    public ResponseEntity<ApiResponse<AccessGroupListResponseDto>> updateAccessGroup(@RequestBody AccessGroupRequestDto accessGroupRequestDto) {
        return ResponseEntity.ok(accessGroupService.updateAccessGroup(accessGroupRequestDto));
    }

    @GetMapping(ApiConstant.GET)
    public ResponseEntity<ApiResponse<?>> getAllAccessGroups(@RequestBody SearchParam searchParam) {
        return ResponseEntity.ok().body(accessGroupService.getallProducts(searchParam));
    }
}