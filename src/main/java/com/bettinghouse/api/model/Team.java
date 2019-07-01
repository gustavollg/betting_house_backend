package com.bettinghouse.api.model;

import com.bettinghouse.api.architecture.model.AbstractEntity;
import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "team")
@SequenceGenerator(name = "default_generator", sequenceName = "team_id_seq", allocationSize = 1)
public class Team extends AbstractEntity {

    @NotBlank(message = "NAME_IS_NULL")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sport_id", foreignKey = @ForeignKey(name = "fk_team_sport_id"), nullable = false)
    @NotNull(message = "SPORT_IS_NULL")
    private Sport sport;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equal(name, team.name) &&
                Objects.equal(sport, team.sport);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, sport);
    }

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
