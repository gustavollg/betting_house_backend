package com.bettinghouse.api.controller;

import com.bettinghouse.api.architecture.controller.CRUDController;
import com.bettinghouse.api.model.Team;
import com.bettinghouse.api.service.TeamService;
import com.bettinghouse.api.validator.TeamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
