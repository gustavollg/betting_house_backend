package com.bettinghouse.api.validator.helper;

import com.bettinghouse.api.model.Sport;
import com.bettinghouse.api.model.Team;
import com.bettinghouse.api.repository.SportRepository;
import com.bettinghouse.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SportValidatorHelper {

    private SportRepository sportRepository;
    private TeamRepository teamRepository;

    @Autowired
    public SportValidatorHelper(SportRepository sportRepository, TeamRepository teamRepository) {
        this.sportRepository = sportRepository;
        this.teamRepository = teamRepository;
    }

    public boolean isSportPresentOnDatabase(Long id) {
        Optional<Sport> optionalSport = sportRepository.findById(id);
        return optionalSport.isPresent();
    }
    
    public boolean isTeamNameAlreadyRegistered(Team team, Long id) {
        Sport sport = sportRepository.findById(id).get();
        List<Team> teams = teamRepository.findAllBySport(sport);
        List<Team> teamsPresent = new ArrayList<>();
        teams.forEach(teamOnDatabase -> {
            if (teamOnDatabase.getName().equals(team.getName())) {
                teamsPresent.add(teamOnDatabase);
            }
        });
        return teamsPresent.size() > 0;
    }
    
    public boolean doesSportNameAlreadyExist(Sport sport) {
        List<Sport> sports = sportRepository.findAllByName(sport.getName());
        return sports.size() > 0;
    }
}
