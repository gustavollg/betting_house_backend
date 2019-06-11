package com.bettinghouse.api.validator;

import com.bettinghouse.api.architecture.validator.CRUDValidator;
import com.bettinghouse.api.model.Sport;
import org.springframework.stereotype.Component;

@Component
public class SportValidator extends CRUDValidator<Sport> {
    
    @Override
    public void validateBeforeSave(Sport entity) {
        
    }

    @Override
    public void validateBeforeRemove(Sport entity) {

    }
}
