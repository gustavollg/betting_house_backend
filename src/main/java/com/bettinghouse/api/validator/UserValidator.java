package com.bettinghouse.api.validator;

import com.bettinghouse.api.architecture.validator.CRUDValidator;
import com.bettinghouse.api.architecture.validator.dto.ApiErrorCode;
import com.bettinghouse.api.model.User;
import com.bettinghouse.api.model.util.EnumProfile;
import com.bettinghouse.api.validator.helper.UserValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator extends CRUDValidator<User> {

    private UserValidatorHelper userValidatorHelper;

    @Autowired
    public UserValidator(UserValidatorHelper userValidatorHelper) {
        this.userValidatorHelper = userValidatorHelper;
    }
    
    public void validateUserBeforeAddCoins(double coins) {
        checkError(coins <= 0, ApiErrorCode.COINS_MUST_BE_POSITIVE);
    }
    
    public void validateUserBeforeBecomingVIP() {
        checkError(userValidatorHelper.isUserCoinsBelow50(), ApiErrorCode.NOT_ENOUGH_COINS);
        checkError(userValidatorHelper.isUserAlreadyVIP(), ApiErrorCode.USER_ALREADY_VIP);
    }

    @Override
    public void validateBeforeSave(User user) {
        checkError(!userValidatorHelper.isInformedPasswordsEqual(user), ApiErrorCode.PASSWORDS_DONT_MATCH);
        checkError(userValidatorHelper.isLoginAlreadyInformed(user), ApiErrorCode.LOGIN_ALREADY_INFORMED);
        checkError(userValidatorHelper.isEmailAlreadyInformed(user), ApiErrorCode.EMAIL_ALREADY_INFORMED);
    }

    @Override
    public void validateBeforeRemove(User user) {

    }
}
