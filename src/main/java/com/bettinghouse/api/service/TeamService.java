package com.bettinghouse.api.service;

import com.bettinghouse.api.architecture.service.CRUDService;
import com.bettinghouse.api.model.Team;
import com.bettinghouse.api.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService extends CRUDService<Team> {
    
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        super(teamRepository);
        this.teamRepository = teamRepository;
    }

    @Override
    public void executeBeforeSave(Team entity) {
        
    }

    @Override
    public void executeAfterSave(Team entity) {

    }

    @Override
    public void executeBeforeDelete(Team entity) {

    }

    @Override
    public void executeAfterDelete(Team entity) {

    }
}
