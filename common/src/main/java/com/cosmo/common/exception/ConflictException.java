package com.cosmo.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Author: Sapana Rimal
 * Date: 4/29/2024
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    public ConflictException() {
        super();
    }

    public ConflictException(final String message) {
        super(message);
    }

}