package com.cosmo.authentication.accessGroup.controller;

import com.cosmo.authentication.accessGroup.model.CreateAccessGroupModel;
import com.cosmo.authentication.accessGroup.model.UpdateAccessGroupModel;
import com.cosmo.authentication.accessGroup.model.request.DeleteAccessGroupRequest;
import com.cosmo.authentication.accessGroup.model.request.FetchAccessGroupDetail;
import com.cosmo.authentication.accessGroup.service.AccessGroupService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.ACCESS_GROUP)
@RequiredArgsConstructor
public class AccessGroupController {
    private final AccessGroupService accessGroupService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> addAccessGroup(@RequestBody @Valid CreateAccessGroupModel createAccessGroupModel) {
        return accessGroupService.createAccessGroup(createAccessGroupModel);
    }

    @PostMapping()
    public Mono<ApiResponse<?>> getAllAccessGroups(@RequestBody @Valid SearchParam searchParam) {
        return accessGroupService.getAllAccessGroup(searchParam);
    }

    @GetMapping(ApiConstant.GET + ApiConstant.SLASH + ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> getAccessGroupDetail(@RequestBody @Valid FetchAccessGroupDetail fetchAccessGroupDetail) {
        return accessGroupService.getAccessGroupDetail(fetchAccessGroupDetail);
    }

    @PostMapping(ApiConstant.EDIT)
    public Mono<ApiResponse> editAccessGroup(@RequestBody @Valid UpdateAccessGroupModel updateAccessGroupModel) {
        return accessGroupService.updateAccessGroup(updateAccessGroupModel);
    }

    @PostMapping(ApiConstant.DELETE)
    public Mono<ApiResponse> deleteAccessGroup(@RequestBody @Valid DeleteAccessGroupRequest deleteAccessGroupRequest) {
        return accessGroupService.deleteAccessGroup(deleteAccessGroupRequest);
    }
}
