package com.cosmo.futsalService.court.repo;

import com.cosmo.common.model.SearchParam;
import com.cosmo.common.repository.SearchRepository;
import com.cosmo.futsalService.court.entity.Court;

import java.util.List;
import java.util.function.Function;

public interface CourtSearchRepository extends SearchRepository<Court> {
    Function<SearchParam, List<Court>> findByVendorCode(SearchParam searchParam, String vendorCode);
}
