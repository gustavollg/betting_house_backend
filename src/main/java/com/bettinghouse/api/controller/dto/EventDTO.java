package com.bettinghouse.api.controller.dto;

import com.bettinghouse.api.model.Sport;

import javax.validation.constraints.NotNull;
import java.util.List;

public class EventDTO {
    
    private String information;
    
    @NotNull(message = "IS_RESTRICTED_IS_NULL")
    private boolean isRestricted;

    @NotNull(message = "SPORT_IS_NULL")
    private Sport sport;
    
    @NotNull(message = "ODDDTO_IS_NULL")
    private List<OddDTO> oddDTOs;

    @NotNull(message = "HAS_DRAW_IS_NULL")
    private boolean hasDraw;
    
    public EventDTO() {
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

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public List<OddDTO> getOddDTOs() {
        return oddDTOs;
    }

    public void setOddDTOs(List<OddDTO> oddDTOs) {
        this.oddDTOs = oddDTOs;
    }

    public boolean isHasDraw() {
        return hasDraw;
    }

    public void setHasDraw(boolean hasDraw) {
        this.hasDraw = hasDraw;
    }
}
