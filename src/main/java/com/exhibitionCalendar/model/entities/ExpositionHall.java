package com.exhibitionCalendar.model.entities;

import java.util.Objects;

public class ExpositionHall {

    private int hallID;
    private String name;
    private String address;

    //default constructor
    public ExpositionHall() {
    }

    public ExpositionHall(int hallID, String name, String address) {
        this.hallID = hallID;
        this.name = name;
        this.address = address;
    }

    // Getters
    public int getHallID() {
        return hallID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setHallID(int hallID) {
        this.hallID = hallID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Hash & equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpositionHall that = (ExpositionHall) o;
        return hallID == that.hallID &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hallID, name, address);
    }

    @Override
    public String toString() {
        return "ExpositionHall{" +
                "hallID=" + hallID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
