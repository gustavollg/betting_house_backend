package com.bettinghouse.api.model;

import com.bettinghouse.api.architecture.model.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "notification")
@SequenceGenerator(name = "default_generator", sequenceName = "notification_id_seq", allocationSize = 1)
public class Notification extends AbstractEntity {
    
    @NotNull(message = "IS_ACTIVE_IS_NULL")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", foreignKey = @ForeignKey(name = "fk_notification_event_id"), nullable = false)
    @NotNull(message = "EVENT_IS_NULL")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_notification_user_id"), nullable = false)
    @NotNull(message = "USER_IS_NULL")
    private User user;

    public Notification() {
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
