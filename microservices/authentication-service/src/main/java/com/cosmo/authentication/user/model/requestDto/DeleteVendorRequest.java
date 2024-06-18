package com.cosmo.authentication.user.model.requestDto;

import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteVendorRequest  extends ModelBase {
private String email;
}
