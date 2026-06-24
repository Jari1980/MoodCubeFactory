package com.moodcubefactory.chaos.model;

public class CubeEvent {
    private int cubeId;
    private String eventType;
    private String eventData;

    public CubeEvent() {
    }

    public CubeEvent(int cubeId, String eventType, String eventData) {
        this.cubeId = cubeId;
        this.eventType = eventType;
        this.eventData = eventData;
    }

    public int getCubeId() {
        return cubeId;
    }

    public void setCubeId(int cubeId) {
        this.cubeId = cubeId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }
}
