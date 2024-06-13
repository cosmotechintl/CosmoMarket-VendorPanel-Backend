package com.cosmo.common.exception.handler;

import com.cosmo.common.exception.BadRequestException;
import com.cosmo.common.exception.NotFoundException;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    protected ApiResponse<Object> handleNotFoundException(NotFoundException ex) {
        log.error("------- RESOURCE NOT FOUND EXCEPTION -------");
        return ResponseUtil.getNotFoundResponse(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<String>> handleBadRequestException(BadRequestException ex) {
        log.error("------- BAD REQUEST EXCEPTION -------");
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }



}
