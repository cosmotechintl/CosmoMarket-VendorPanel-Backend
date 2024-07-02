package com.cosmo.futsalService.futsal.repo.impl;

import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.SearchParamUtil;
import com.cosmo.futsalService.futsal.entity.Futsal;
import com.cosmo.futsalService.futsal.repo.FutsalSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import static com.cosmo.common.constant.SearchParamConstant.*;

@Repository
@RequiredArgsConstructor
public class FutsalSearchRepositoryImpl implements FutsalSearchRepository {

    @PersistenceContext
    protected EntityManager em;


    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(f.id) " +
                        "from Futsal  f " +
                        "join Status s on s.id=f.status.id " +
                        " where " +
                        "(:name is null or f.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) and " +
                         "(:location is null or f.location like CONCAT('%', :location, '%')) and " +
                        "(:price is null or f.price = :price)")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .setParameter("location",SearchParamUtil.getString(searchParam,LOCATION))
                .setParameter("price",SearchParamUtil.getString(searchParam,PRICE))
                .getSingleResult();
    }

    @Override
    public List<Futsal> getAll(SearchParam searchParam) {
        return em.createQuery("select f " +
                        "from Futsal  f " +
                        "join Status s on s.id=f.status.id " +
                        " where " +
                        "(:name is null or f.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) and " +
                        "(:location is null or f.location like CONCAT('%', :location, '%')) and " +
                        "(:price is null or f.price = :price)")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .setParameter("location",SearchParamUtil.getString(searchParam,LOCATION))
                .setParameter("price",SearchParamUtil.getString(searchParam,PRICE))
                .getResultList();
    }
}

