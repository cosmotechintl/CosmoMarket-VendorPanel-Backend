package com.cosmo.authentication.vendor.repository.impl;


import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.authentication.vendor.repository.VendorSearchRepository;
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
public class VendorSearchRepositoryImpl implements VendorSearchRepository {
    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(ag.id) " +
                        "from Vendor  ag " +
                        "join Status s on s.id=ag.status.id " +
                        " where " +
                        "(:name is null or ag.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getSingleResult();
    }

    @Override
    public List<Vendor> getAll(SearchParam searchParam) {
        return em.createQuery("select ag " +
                        "from Vendor  ag " +
                        "join Status s on s.id=ag.status.id " +
                        " where " +
                        "(:name is null or ag.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getResultList();
    }
}
