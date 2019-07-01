package com.bettinghouse.api.controller.dto;

import com.bettinghouse.api.model.Bet;
import com.bettinghouse.api.model.Event;

public class HistoryDTO {

    private Event event;
    private Bet bet;
    
    public HistoryDTO() {
    }

    public HistoryDTO(Event event, Bet bet) {
        this.event = event;
        this.bet = bet;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }
}
