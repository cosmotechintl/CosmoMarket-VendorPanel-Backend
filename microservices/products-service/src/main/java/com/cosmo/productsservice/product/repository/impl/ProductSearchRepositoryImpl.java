package com.cosmo.productsservice.product.repository.impl;

import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.SearchParamUtil;
import com.cosmo.productsservice.product.entity.Product;
import com.cosmo.productsservice.product.repository.ProductSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cosmo.common.constant.SearchParamConstant.NAME;
import static com.cosmo.common.constant.SearchParamConstant.STATUS;

@Repository
@RequiredArgsConstructor
public class ProductSearchRepositoryImpl implements ProductSearchRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(p.id) " +
                        "from Product  p " +
                        "join Status s on s.id=p.status.id " +
                        " where " +
                        "(:name is null or p.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getSingleResult();
    }

    @Override
    public List<Product> getAll(SearchParam searchParam) {
        return em.createQuery("select p " +
                        "from Product  p " +
                        "join Status s on s.id=p.status.id " +
                        " where " +
                        "(:name is null or p.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getResultList();
    }
}