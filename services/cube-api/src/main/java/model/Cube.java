package model;

import java.sql.Timestamp;

public class Cube {

    private int id;
    private String color;
    private String mood;
    private int energy;
    private Timestamp updatedAt;

    public Cube() {
    }

    public Cube(
            int id,
            String color,
            String mood,
            int energy,
            Timestamp updatedAt) {

        this.id = id;
        this.color = color;
        this.mood = mood;
        this.energy = energy;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
