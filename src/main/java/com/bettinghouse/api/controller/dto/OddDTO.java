package com.bettinghouse.api.controller.dto;

import com.bettinghouse.api.model.Team;

import javax.validation.constraints.NotNull;

public class OddDTO {
    
    @NotNull(message = "ODD_IS_NULL")
    private double odd;
    
    private Team team;

    public OddDTO() {
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
