package com.bettinghouse.api.validator;

import com.bettinghouse.api.architecture.validator.CRUDValidator;
import com.bettinghouse.api.model.Bet;
import org.springframework.stereotype.Component;

@Component
public class BetValidator extends CRUDValidator<Bet> {
    
    @Override
    public void validateBeforeSave(Bet entity) {
        
    }

    @Override
    public void validateBeforeRemove(Bet entity) {

    }
}
