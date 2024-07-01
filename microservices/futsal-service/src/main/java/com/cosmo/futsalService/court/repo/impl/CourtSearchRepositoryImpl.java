package com.cosmo.futsalService.court.repo.impl;

import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.SearchParamUtil;
import com.cosmo.futsalService.court.entity.Court;
import com.cosmo.futsalService.court.repo.CourtSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static com.cosmo.common.constant.SearchParamConstant.NAME;
import static com.cosmo.common.constant.SearchParamConstant.STATUS;

@Service
@RequiredArgsConstructor
public class CourtSearchRepositoryImpl implements CourtSearchRepository {
    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(cd.id) " +
                        "from Court  cd " +
                        "join Status s on s.id=cd.status.id " +
                        " where " +
                        "(:name is null or cd.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getSingleResult();
    }

    @Override
    public List<Court> getAll(SearchParam searchParam) {
        return em.createQuery("select cd " +
                        "from Court  cd " +
                        "join Status s on s.id=cd.status.id " +
                        " where " +
                        "(:name is null or cd.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getResultList();
    }

    @Override
    public Function<SearchParam, List<Court>> findByVendorCode(SearchParam searchParam, String vendorCode) {
        return param -> em.createQuery("select cd " +
                        "from Court  cd " +
                        "join Status s on s.id=cd.status.id " +
                        " where " +
                        "(:name is null or cd.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) and " +
                        "(:vendorCode is null or cd.vendorCode=:vendorCode)", Court.class)
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .setParameter("vendorCode", vendorCode)
                .getResultList();
    }
}
