package com.cosmo.vendorservice.vendorUser.model.requestDto;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchVendorUserDetails extends ModelBase{
    @NotBlank(message = "Name is required.")
    private String username;
}