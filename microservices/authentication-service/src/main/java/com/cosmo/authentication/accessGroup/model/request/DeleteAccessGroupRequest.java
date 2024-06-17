package com.cosmo.authentication.accessGroup.model.request;

import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteAccessGroupRequest extends ModelBase {
    private Long id;
}