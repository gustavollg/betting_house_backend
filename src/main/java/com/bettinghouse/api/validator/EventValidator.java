package com.bettinghouse.api.validator;

import com.bettinghouse.api.architecture.validator.CRUDValidator;
import com.bettinghouse.api.architecture.validator.dto.ApiErrorCode;
import com.bettinghouse.api.controller.dto.EventDTO;
import com.bettinghouse.api.model.Event;
import com.bettinghouse.api.validator.helper.EventValidatorHelper;
import com.bettinghouse.api.validator.helper.SportValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventValidator extends CRUDValidator<Event> {

    private SportValidatorHelper sportValidatorHelper;
    private EventValidatorHelper eventValidatorHelper;

    @Autowired
    public EventValidator(SportValidatorHelper sportValidatorHelper, EventValidatorHelper eventValidatorHelper) {
        this.sportValidatorHelper = sportValidatorHelper;
        this.eventValidatorHelper = eventValidatorHelper;
    }
    
    public void validateBeforeSaveEventDTO(EventDTO eventDTO) {
        checkError(!sportValidatorHelper.isSportPresentOnDatabase(eventDTO.getSport().getId()), ApiErrorCode.SPORT_NOT_ON_DATABASE);
        checkError(eventDTO.getOddDTOs().size() == 0, ApiErrorCode.TEAM_NOT_SELECTED);
    }

    @Override
    public void validateBeforeSave(Event event) {
        checkError(!sportValidatorHelper.isSportPresentOnDatabase(event.getSport().getId()), ApiErrorCode.SPORT_NOT_ON_DATABASE);
    }

    @Override
    public void validateBeforeRemove(Event event) {

    }
}
