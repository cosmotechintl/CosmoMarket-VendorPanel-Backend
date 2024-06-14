package com.cosmo.authentication.user.repo;

import com.cosmo.common.model.SearchParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorUsersSearchRepository<T> {
    Long count(SearchParam searchParam);
   List<T> getAll(SearchParam searchParam);
//   List<VendorUser> getAll(SearchParam searchParam);
}
