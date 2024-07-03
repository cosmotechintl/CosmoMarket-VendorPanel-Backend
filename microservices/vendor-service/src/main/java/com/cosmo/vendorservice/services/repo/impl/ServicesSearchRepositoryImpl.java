package com.cosmo.vendorservice.services.repo.impl;

import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.SearchParamUtil;
import com.cosmo.vendorservice.services.entity.Services_Details;
import com.cosmo.vendorservice.services.repo.ServicesSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cosmo.common.constant.SearchParamConstant.NAME;
import static com.cosmo.common.constant.SearchParamConstant.STATUS;


@Repository
@RequiredArgsConstructor
public class ServicesSearchRepositoryImpl implements ServicesSearchRepository {
    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(sd.id) " +
                        "from Services_Details  sd " +
                        "join Status s on s.id=sd.status.id " +
                        " where " +
                        "(:name is null or sd.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getSingleResult();
    }

    @Override
    public List<Services_Details> getAll(SearchParam searchParam) {
        return em.createQuery("select sd " +
                        "from Services_Details  sd " +
                        "join Status s on s.id=sd.status.id " +
                        " where " +
                        "(:name is null or sd.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam,NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getResultList();
    }

}

