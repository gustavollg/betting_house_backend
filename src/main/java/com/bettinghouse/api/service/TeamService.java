package com.bettinghouse.api.service;

import com.bettinghouse.api.architecture.service.CRUDService;
import com.bettinghouse.api.model.Sport;
import com.bettinghouse.api.model.Team;
import com.bettinghouse.api.repository.SportRepository;
import com.bettinghouse.api.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService extends CRUDService<Team> {
    
    private TeamRepository teamRepository;
    private SportRepository sportRepository;

    public TeamService(TeamRepository teamRepository, SportRepository sportRepository) {
        super(teamRepository);
        this.teamRepository = teamRepository;
        this.sportRepository = sportRepository;
    }
    
    public List<Team> findTeamsBySportId(Long id) {
        Sport sport = sportRepository.findById(id).get();
        return teamRepository.findAllBySport(sport);
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
