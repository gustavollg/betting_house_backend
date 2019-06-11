package com.bettinghouse.api.validator;

import com.bettinghouse.api.architecture.exception.ApiErrorException;
import com.bettinghouse.api.architecture.validator.dto.ApiErrorCode;
import com.bettinghouse.api.controller.dto.LoginParametersDTO;
import com.bettinghouse.api.validator.helper.UserValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

    private UserValidatorHelper userValidatorHelper;

    @Autowired
    public LoginValidator(UserValidatorHelper userValidatorHelper) {
        this.userValidatorHelper = userValidatorHelper;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return LoginParametersDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LoginParametersDTO loginParametersDTO = (LoginParametersDTO) o;
        if (!userValidatorHelper.isUserCredentialsCorrect(loginParametersDTO)) {
            throw new ApiErrorException(ApiErrorCode.INCORRECT_LOGIN_OR_PASSWORD);
        }
    }
}
