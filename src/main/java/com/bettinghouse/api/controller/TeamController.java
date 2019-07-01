package com.bettinghouse.api.controller;

import com.bettinghouse.api.architecture.controller.CRUDController;
import com.bettinghouse.api.model.Team;
import com.bettinghouse.api.service.TeamService;
import com.bettinghouse.api.validator.TeamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController extends CRUDController<Team> {
    
    private TeamValidator teamValidator;
    private TeamService teamService;

    @Autowired
    public TeamController(TeamValidator teamValidator, TeamService teamService) {
        super(teamValidator, teamService);
        this.teamValidator = teamValidator;
        this.teamService = teamService;
    }
    
    @GetMapping("find-teams-by-sport-id/{id}")
    public ResponseEntity<List<Team>> findAllTeamsBySportId(@PathVariable Long id) {
        teamValidator.validateBeforeFetchTeamsBySportId(id);
        List<Team> teams = teamService.findTeamsBySportId(id);
        return ResponseEntity.ok(teams);
    }
    
    @PostMapping("save")
    public ResponseEntity<Team> saveTeam(@RequestBody @Valid Team team) {
        teamValidator.validateBeforeSave(team);
        Team teamPersisted = teamService.save(team);
        return ResponseEntity.ok(teamPersisted);
    } 
}
