package com.cosmo.vendorservice.vendorUser.Service;

import com.cosmo.vendorservice.vendorUser.model.VendorUserRequestDto;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;

public interface VendorService {
    Object addVendorUser(VendorUserRequestDto vendorUserRequestDto);

    void deleteVendorUser(String username);

    ApiResponse<?> getAllVendorUsers(SearchParam searchParam);

    Object updateVendorUser(VendorUserRequestDto vendorUserRequestDto);


}
