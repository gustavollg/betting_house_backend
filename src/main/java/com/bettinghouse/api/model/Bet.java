package com.bettinghouse.api.model;

import com.bettinghouse.api.architecture.model.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "bet")
@SequenceGenerator(name = "default_generator", sequenceName = "bet_id_seq", allocationSize = 1)
public class Bet extends AbstractEntity {
    
    @NotNull(message = "BET_IS_NULL")
    private double bet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", foreignKey = @ForeignKey(name = "fk_bet_event_id"), nullable = false)
    @NotNull(message = "EVENT_IS_NULL")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_bet_user_id"), nullable = false)
    @NotNull(message = "USER_IS_NULL")
    private User user;

    public Bet() {
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
