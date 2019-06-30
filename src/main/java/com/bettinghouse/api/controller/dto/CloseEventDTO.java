package com.bettinghouse.api.controller.dto;

public class CloseEventDTO {

    private double odd;
    private Long eventId;

    public CloseEventDTO() {
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
