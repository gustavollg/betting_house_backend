package com.bettinghouse.api.architecture.validator;

import com.bettinghouse.api.architecture.exception.ApiErrorException;
import com.bettinghouse.api.architecture.validator.dto.ApiErrorCode;

public abstract class AbstractValidator {

    public void checkError(boolean hasError, ApiErrorCode errorCode) {
        if (hasError) {
            throw new ApiErrorException(errorCode);
        }
    }
}