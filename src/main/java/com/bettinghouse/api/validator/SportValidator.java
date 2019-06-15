package com.bettinghouse.api.validator;

import com.bettinghouse.api.architecture.validator.CRUDValidator;
import com.bettinghouse.api.architecture.validator.dto.ApiErrorCode;
import com.bettinghouse.api.model.Sport;
import com.bettinghouse.api.validator.helper.SportValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SportValidator extends CRUDValidator<Sport> {
    
    private SportValidatorHelper sportValidatorHelper;

    @Autowired
    public SportValidator(SportValidatorHelper sportValidatorHelper) {
        this.sportValidatorHelper = sportValidatorHelper;
    }

    @Override
    public void validateBeforeSave(Sport sport) {
        checkError(sportValidatorHelper.doesSportNameAlreadyExist(sport), ApiErrorCode.SPORT_ALREADY_EXIST);
    }

    @Override
    public void validateBeforeRemove(Sport sport) {
    }
}
