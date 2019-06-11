package com.bettinghouse.api.service;

import com.bettinghouse.api.architecture.service.CRUDService;
import com.bettinghouse.api.model.Bet;
import com.bettinghouse.api.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetService extends CRUDService<Bet> {
    
    private BetRepository betRepository;

    @Autowired
    public BetService(BetRepository betRepository) {
        super(betRepository);
        this.betRepository = betRepository;
    }

    @Override
    public void executeBeforeSave(Bet entity) {
        
    }

    @Override
    public void executeAfterSave(Bet entity) {

    }

    @Override
    public void executeBeforeDelete(Bet entity) {

    }

    @Override
    public void executeAfterDelete(Bet entity) {

    }
}
