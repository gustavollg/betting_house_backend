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
    
    public void validateBeforeFetchTeamsBySportId(Long id) {
        checkError(!sportValidatorHelper.isSportPresentOnDatabase(id), ApiErrorCode.SPORT_NOT_ON_DATABASE);
    }

    @Override
    public void validateBeforeSave(Team team) {
        checkError(!sportValidatorHelper.isSportPresentOnDatabase(team.getSport().getId()), ApiErrorCode.SPORT_NOT_ON_DATABASE);
        checkError(sportValidatorHelper.isTeamNameAlreadyRegistered(team, team.getSport().getId()), ApiErrorCode.TEAM_ALREADY_EXIST);
    }

    @Override
    public void validateBeforeRemove(Team team) {

    }
}
