package com.cosmo.authentication.user.repo;

import com.cosmo.authentication.user.entity.VendorUser;
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
public class VendorUsersSearchRepositoryImpl implements VendorUsersSearchRepository<VendorUser> {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(vu.id) " +
                        "from VendorUser vu " +
                        "join Status s on s.id=vu.status.id " +
                        " where " +
                        "(:name is null or vu.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getSingleResult();
    }

    @Override
    public List<VendorUser> getAll(SearchParam searchParam) {
        return em.createQuery("select vu " +
                        "from VendorUser vu " +
                        "join Status s on s.id=vu.status.id " +
                        " where " +
                        "(:name is null or vu.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ", VendorUser.class)
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getResultList();
    }
}
