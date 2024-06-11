package com.cosmo.authentication.accessgroup.service.impl;

import com.cosmo.authentication.accessgroup.entity.AccessGroup;
import com.cosmo.authentication.accessgroup.entity.AccessGroupRoleMap;
import com.cosmo.authentication.accessgroup.mapper.AccessGroupMapper;
import com.cosmo.authentication.accessgroup.mapper.AccessGroupRoleMapMapper;
import com.cosmo.authentication.accessgroup.model.*;
import com.cosmo.authentication.accessgroup.model.request.FetchAccessGroupDetail;
import com.cosmo.authentication.accessgroup.service.AccessGroupService;
import com.cosmo.authentication.accessgroup.repo.AccessGroupRepository;
import com.cosmo.authentication.accessgroup.repo.AccessGroupSearchRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public Mono<ApiResponse> updateAccessGroup(Long id, UpdateAccessGroupModel updateAccessGroupModel) {
        Optional<AccessGroup> oldAccessGroup = accessGroupRepository.findById(id);
        if (oldAccessGroup.isPresent()) {
            AccessGroup updatedAccessGroup = accessGroupMapper.updatetoEntity(oldAccessGroup.get(), updateAccessGroupModel);
            List<AccessGroupRoleMap> oldAccessGroupRoleMaps = oldAccessGroup.get().getAccessGroupRoleMaps();
            List<AccessGroupRoleMap> updatedAccessGroupRoleMaps = oldAccessGroupRoleMaps.stream()
                    .map(oldAccessGroupRoleMap -> accessGroupRoleMapMapper.partialUpdate((AccessGroupRoleMapDto) updateAccessGroupModel.getAccessGroupRoleMapDtos(), oldAccessGroupRoleMap))
                    .collect(Collectors.toList());
            updatedAccessGroup.setAccessGroupRoleMaps(updatedAccessGroupRoleMaps);
            accessGroupRepository.save(updatedAccessGroup);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Access group updated"));
        } else {
            return Mono.just(ResponseUtil.getNotFoundResponse("Access group not found"));
        }
    }

    @Override
    public void deleteAccessGroup(Long id) {
        Optional<AccessGroup> accessGroup = accessGroupRepository.findById(id);
        if (accessGroup.isPresent()) {
            AccessGroup existingAccessGroup = accessGroup.get();
            existingAccessGroup.setStatus(statusRepository.findByName(StatusConstant.DELETED.getName()));
            accessGroupRepository.save(existingAccessGroup);

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
