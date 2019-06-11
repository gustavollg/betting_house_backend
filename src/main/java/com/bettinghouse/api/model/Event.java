package com.bettinghouse.api.model;

import com.bettinghouse.api.architecture.model.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "event")
@SequenceGenerator(name = "default_generator", sequenceName = "event_id_seq", allocationSize = 1)
public class Event extends AbstractEntity {
    
    private String information;
    
    @NotNull(message = "IS_RESTRICTED_IS_NULL")
    private boolean isRestricted;
    
    @NotNull(message = "IS_OPEN_IS_NULL")
    private boolean isOpen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id", foreignKey = @ForeignKey(name = "fk_event_sport_id"), nullable = false)
    @NotNull(message = "SPORT_IS_NULL")
    private Sport sport;

    public Event() {
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public boolean isRestricted() {
        return isRestricted;
    }

    public void setRestricted(boolean restricted) {
        isRestricted = restricted;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
