package com.bettinghouse.api.controller.dto;

import com.bettinghouse.api.model.Event;

public class BetDTO {
    
    private double bet;
    private double odd;
    private Event event;

    public BetDTO() {
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
