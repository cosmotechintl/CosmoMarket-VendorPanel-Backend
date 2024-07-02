package com.cosmo.futsalService.futsal.repo;

import com.cosmo.common.model.SearchParam;
import com.cosmo.common.repository.SearchRepository;
import com.cosmo.futsalService.futsal.entity.Futsal;

import java.util.List;
import java.util.function.Function;

public interface FutsalSearchRepository extends SearchRepository<Futsal> {
    Function<SearchParam, List<Futsal>> findByVendorCode(SearchParam searchParam, String futsalCode);
}
