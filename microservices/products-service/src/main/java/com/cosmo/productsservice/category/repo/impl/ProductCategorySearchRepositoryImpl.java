package com.cosmo.productsservice.category.repo.impl;

import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.SearchParamUtil;
import com.cosmo.productsservice.category.entity.ProductCategory;
import com.cosmo.productsservice.category.repo.ProductCategorySearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cosmo.common.constant.SearchParamConstant.NAME;
import static com.cosmo.common.constant.SearchParamConstant.STATUS;
@Service
@RequiredArgsConstructor
public class ProductCategorySearchRepositoryImpl implements ProductCategorySearchRepository {
    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(pc.id) " +
                        "from ProductCategory  pc " +
                        "join Status s on s.id=pc.status.id " +
                        " where " +
                        "(:name is null or pc.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getSingleResult();
    }

    @Override
    public List<ProductCategory > getAll(SearchParam searchParam) {
        return em.createQuery("select pc " +
                        "from ProductCategory  pc " +
                        "join Status s on s.id=pc.status.id " +
                        " where " +
                        "(:name is null or pc.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getResultList();
    }
}
