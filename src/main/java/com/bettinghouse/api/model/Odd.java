package com.bettinghouse.api.model;

import com.bettinghouse.api.architecture.model.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "odd")
@SequenceGenerator(name = "default_generator", sequenceName = "odd_id_seq", allocationSize = 1)
public class Odd extends AbstractEntity {

    @NotNull(message = "ODD_IS_NULL")
    private double odd;
    
    private double points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", foreignKey = @ForeignKey(name = "fk_odd_event_id"), nullable = false)
    @NotNull(message = "EVENT_IS_NULL")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", foreignKey = @ForeignKey(name = "fk_odd_team_id"), nullable = false)
    @NotNull(message = "TEAM_IS_NULL")
    private Team team;
    
    public Odd() {
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
