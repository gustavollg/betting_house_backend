package com.bettinghouse.api.service;

import com.bettinghouse.api.architecture.service.CRUDService;
import com.bettinghouse.api.model.Sport;
import com.bettinghouse.api.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportService extends CRUDService<Sport> {
    
    private SportRepository sportRepository;

    @Autowired
    public SportService(SportRepository sportRepository) {
        super(sportRepository);
        this.sportRepository = sportRepository;
    }

    @Override
    public void executeBeforeSave(Sport entity) {
        
    }

    @Override
    public void executeAfterSave(Sport entity) {

    }

    @Override
    public void executeBeforeDelete(Sport entity) {

    }

    @Override
    public void executeAfterDelete(Sport entity) {

    }
}
