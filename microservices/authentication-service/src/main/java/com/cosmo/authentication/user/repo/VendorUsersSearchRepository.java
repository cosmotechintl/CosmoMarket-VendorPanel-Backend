package com.cosmo.authentication.user.repo;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.repository.SearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorUsersSearchRepository extends SearchRepository<VendorUser> {

}
