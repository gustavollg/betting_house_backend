package com.bettinghouse.api.validator;

import com.bettinghouse.api.architecture.validator.CRUDValidator;
import com.bettinghouse.api.architecture.validator.dto.ApiErrorCode;
import com.bettinghouse.api.model.Event;
import com.bettinghouse.api.validator.helper.SportValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventValidator extends CRUDValidator<Event> {

    private SportValidatorHelper sportValidatorHelper;

    @Autowired
    public EventValidator(SportValidatorHelper sportValidatorHelper) {
        this.sportValidatorHelper = sportValidatorHelper;
    }

    @Override
    public void validateBeforeSave(Event event) {
        checkError(!sportValidatorHelper.isSportPresentOnDatabase(event.getSport()), ApiErrorCode.SPORT_NOT_ON_DATABASE);
    }

    @Override
    public void validateBeforeRemove(Event event) {

    }
}
