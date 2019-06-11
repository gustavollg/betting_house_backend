package com.bettinghouse.api.validator;

import com.bettinghouse.api.architecture.validator.CRUDValidator;
import com.bettinghouse.api.architecture.validator.dto.ApiErrorCode;
import com.bettinghouse.api.model.Team;
import com.bettinghouse.api.validator.helper.SportValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamValidator extends CRUDValidator<Team> {
    
    private SportValidatorHelper sportValidatorHelper;

    @Autowired
    public TeamValidator(SportValidatorHelper sportValidatorHelper) {
        this.sportValidatorHelper = sportValidatorHelper;
    }

    @Override
    public void validateBeforeSave(Team team) {
        checkError(!sportValidatorHelper.isSportPresentOnDatabase(team.getSport()), ApiErrorCode.SPORT_NOT_ON_DATABASE);
    }

    @Override
    public void validateBeforeRemove(Team team) {

    }
}
