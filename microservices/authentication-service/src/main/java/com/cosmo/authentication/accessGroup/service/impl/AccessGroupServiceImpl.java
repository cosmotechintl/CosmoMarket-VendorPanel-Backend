package com.cosmo.authentication.accessGroup.service.impl;

import com.cosmo.authentication.accessGroup.entity.AccessGroup;
import com.cosmo.authentication.accessGroup.mapper.AccessGroupMapper;
import com.cosmo.authentication.accessGroup.mapper.AccessGroupRoleMapMapper;
import com.cosmo.authentication.accessGroup.model.*;
import com.cosmo.authentication.accessGroup.model.request.DeleteAccessGroupRequest;
import com.cosmo.authentication.accessGroup.model.request.FetchAccessGroupDetail;
import com.cosmo.authentication.accessGroup.service.AccessGroupService;
import com.cosmo.authentication.accessGroup.repo.AccessGroupRepository;
import com.cosmo.authentication.accessGroup.repo.AccessGroupSearchRepository;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AccessGroupServiceImpl implements AccessGroupService {
    private final AccessGroupRepository accessGroupRepository;
    private final AccessGroupMapper accessGroupMapper;
    private final AccessGroupRoleMapMapper accessGroupRoleMapMapper;
    private final SearchResponse searchResponse;
    private final AccessGroupSearchRepository accessGroupSearchRepository;
    private final StatusRepository statusRepository;

    @Override
    @Transactional
    public Mono<ApiResponse> createAccessGroup(CreateAccessGroupModel createAccessGroupModel) {
        AccessGroup accessGroup = accessGroupMapper.toEntity(createAccessGroupModel);
        accessGroupRoleMapMapper.createAccessGroupRoleMap(accessGroup, createAccessGroupModel.getRoles());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Access group created"));
    }

    @Override
    public Mono<ApiResponse> updateAccessGroup(UpdateAccessGroupModel updateAccessGroupModel) {
        Optional<AccessGroup> oldAccessGroup = accessGroupRepository.findById(updateAccessGroupModel.getId());
        if (oldAccessGroup.isPresent()) {
            AccessGroup updatedAccessGroup = accessGroupMapper.updatetoEntity(oldAccessGroup.get(), updateAccessGroupModel);
            accessGroupRoleMapMapper.updateAccessGroupRoleMap(updatedAccessGroup, updateAccessGroupModel.getRoles());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Access group updated"));
        } else {
            return Mono.just(ResponseUtil.getNotFoundResponse("Access group not found"));
        }
    }

    @Override
    public Mono<ApiResponse> deleteAccessGroup(DeleteAccessGroupRequest deleteAccessGroupRequest) {
        Optional<AccessGroup> accessGroup = accessGroupRepository.findById(deleteAccessGroupRequest.getId());

        if (accessGroup.isPresent()) {
            AccessGroup existingAccessGroup = accessGroup.get();
            if (existingAccessGroup.getStatus().getName().equals(StatusConstant.DELETED.getName())) {
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("Access group not found"));
            }
            existingAccessGroup.setStatus(statusRepository.findByName(StatusConstant.DELETED.getName()));
            accessGroupRepository.save(existingAccessGroup);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Access group deleted"));
        }else {
            return Mono.just(ResponseUtil.getNotFoundResponse("Access group not found"));
        }

    }

    @Override
    public Mono<ApiResponse<?>> getAllAccessGroup(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<AccessGroup, SearchAccessGroupResponse> responseBuilder = SearchResponseWithMapperBuilder
                .<AccessGroup, SearchAccessGroupResponse>builder()
                .count(accessGroupSearchRepository::count)
                .searchData(accessGroupSearchRepository::getAll)
                .mapperFunction(this.accessGroupMapper::getAccessGroupResponses)
                .searchParam(searchParam)
                .build();
        PageableResponse<SearchAccessGroupResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Access groups fetched successfully."));
    }

    @Override
    public Mono<ApiResponse<?>> getAccessGroupDetail(FetchAccessGroupDetail fetchAccessGroupDetail) {
        Optional<AccessGroup> accessGroup = accessGroupRepository.findByName(fetchAccessGroupDetail.getName());
        if (accessGroup.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Access group  not found"));
        } else {
            AccessGroupDetailDto accessGroupDetailDto = accessGroupMapper.getAccessGroupDetailDto(accessGroup.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(accessGroupDetailDto, "access group fetched successfully"));
        }
    }


}
