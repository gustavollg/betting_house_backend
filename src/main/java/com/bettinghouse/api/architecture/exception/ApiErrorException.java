package com.bettinghouse.api.architecture.exception;

import com.bettinghouse.api.architecture.validator.dto.ApiErrorCode;

public class ApiErrorException extends RuntimeException {

    public ApiErrorException(ApiErrorCode errorCode) {
        super(errorCode.toString());
    }
}