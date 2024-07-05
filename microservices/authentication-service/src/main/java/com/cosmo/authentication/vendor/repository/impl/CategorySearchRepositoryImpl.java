package com.cosmo.authentication.vendor.repository.impl;

import com.cosmo.authentication.vendor.entity.Category;
import com.cosmo.authentication.vendor.repository.CategorySearchRepository;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.SearchParamUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cosmo.common.constant.SearchParamConstant.NAME;

@Repository
@RequiredArgsConstructor
public class CategorySearchRepositoryImpl implements CategorySearchRepository {
    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(c.id) " +
                        "from Category  c "+
                        " where " +
                        "(:name is null or c.name = :name)")
//                        "(:name is null or c.name like CONCAT('%', :name, '%')")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .getSingleResult();
    }

    @Override
    public List<Category> getAll(SearchParam searchParam) {
        return em.createQuery("select c " +
                        "from Category  c "+
                        " where " +
                        "(:name is null or c.name = :name)")
//                        "(:name is null or c.name like CONCAT('%', :name, '%')")

                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .getResultList();
    }
}