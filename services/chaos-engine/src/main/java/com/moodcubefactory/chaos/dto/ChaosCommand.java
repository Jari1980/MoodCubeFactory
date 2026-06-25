package com.moodcubefactory.chaos.dto;

public class ChaosCommand {
    private String type;

    public ChaosCommand() {
    }

    public ChaosCommand(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
