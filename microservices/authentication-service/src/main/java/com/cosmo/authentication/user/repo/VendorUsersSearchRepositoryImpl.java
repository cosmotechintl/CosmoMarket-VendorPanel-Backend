package com.cosmo.authentication.user.repo;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.service.impl.VendorUserHelper;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.SearchParamUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cosmo.common.constant.SearchParamConstant.NAME;
import static com.cosmo.common.constant.SearchParamConstant.STATUS;

@Repository
@RequiredArgsConstructor
public class VendorUsersSearchRepositoryImpl implements VendorUsersSearchRepository {

    @PersistenceContext
    protected EntityManager em;

    private final VendorUserHelper vendorUserHelper;

    @Override
    public Long count(SearchParam searchParam) {
        Long vendorId = vendorUserHelper.getCurrentUserVendorId();
        return (Long) em.createQuery("select COUNT(vu.id) " +
                        "from VendorUser vu " +
                        "join Status s on s.id=vu.status.id " +
//                        "join AccessGroup ag on ag.id=vu.accessGroup.id " +
                        " where " +
                        "vu.vendor.id = :vendorId and " +
                        "(:name is null or vu.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status)"
                        )
//                        "(:accessGroupId is null or ag.id = :accessGroupId)")
                .setParameter("vendorId", vendorId)
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
//                .setParameter("accessGroupId", SearchParamUtil.getLong(searchParam, ACCESS_GROUP_ID))
                .getSingleResult();
    }

    @Override
    public List<VendorUser> getAll(SearchParam searchParam) {
        Long vendorId = vendorUserHelper.getCurrentUserVendorId();
        return em.createQuery("select vu " +
                        "from VendorUser vu " +
                        "join Status s on s.id=vu.status.id " +
//                        "join AccessGroup ag on ag.id=vu.accessGroup.id " +
                        " where " +
                        "vu.vendor.id = :vendorId and " +
                        "(:name is null or vu.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) " , VendorUser.class)

                .setParameter("vendorId", vendorId)
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getResultList();
    }
}
