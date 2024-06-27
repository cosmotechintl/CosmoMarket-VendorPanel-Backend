package com.cosmo.vendorservice.court.repo.impl;

import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.SearchParamUtil;
import com.cosmo.vendorservice.court.entity.CourtDetails;
import com.cosmo.vendorservice.court.repo.VendorCourtSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cosmo.common.constant.SearchParamConstant.NAME;
import static com.cosmo.common.constant.SearchParamConstant.STATUS;

@Service
@RequiredArgsConstructor
public class VendorCourtSearchRepositoryImpl implements VendorCourtSearchRepository {
    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(cd.id) " +
                        "from CourtDetails  cd " +
                        "join Status s on s.id=cd.status.id " +
                        " where " +
                        "(:name is null or cd.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getSingleResult();
    }

    @Override
    public List<CourtDetails> getAll(SearchParam searchParam) {
        return em.createQuery("select cd " +
                        "from CourtDetails  cd " +
                        "join Status s on s.id=cd.status.id " +
                        " where " +
                        "(:name is null or cd.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getResultList();
    }
}
