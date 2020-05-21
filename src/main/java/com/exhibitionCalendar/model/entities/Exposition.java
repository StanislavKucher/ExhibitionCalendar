package com.exhibitionCalendar.model.entities;

import java.sql.Date;
import java.util.Objects;

public class Exposition {

    private int expositionID;
    private String title;
    private String theme;
    private Date dateFrom;
    private Date dateTo;
    private double ticketPrice;
    private int hallID;
    private String description;

    //default constructor
    public Exposition() {
    }

    private Exposition(Builder builder) {
        expositionID = builder.expositionID;
        title = builder.title;
        theme = builder.theme;
        dateFrom = builder.dateFrom;
        dateTo = builder.dateTo;
        ticketPrice = builder.ticketPrice;
        hallID = builder.hallID;
        description = builder.description;
    }

    // Builder starts
    public static class Builder {
        private int expositionID;
        private String title;
        private String theme;
        private Date dateFrom;
        private Date dateTo;
        private double ticketPrice;
        private int hallID;
        private String description;

        public Builder() {
        }

        public Builder setExpositionID(int expositionID) {
            this.expositionID = expositionID;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTheme(String theme) {
            this.theme = theme;
            return this;
        }

        public Builder setDateFrom(Date dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public Builder setDateTo(Date dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public Builder setTicketPrice(double ticketPrice) {
            this.ticketPrice = ticketPrice;
            return this;
        }

        public Builder setHallID(int hallID) {
            this.hallID = hallID;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Exposition build() {
            return new Exposition(this);
        }
    }
    // Builder ends

    // Getters
    public int getExpositionID() {
        return expositionID;
    }

    public String getTitle() {
        return title;
    }

    public String getTheme() {
        return theme;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getHallID() {
        return hallID;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setExpositionID(int expositionID) {
        this.expositionID = expositionID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setHallID(int hallID) {
        this.hallID = hallID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Hash & equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exposition that = (Exposition) o;
        return expositionID == that.expositionID &&
                Double.compare(that.ticketPrice, ticketPrice) == 0 &&
                hallID == that.hallID &&
                Objects.equals(title, that.title) &&
                Objects.equals(theme, that.theme) &&
                Objects.equals(dateFrom, that.dateFrom) &&
                Objects.equals(dateTo, that.dateTo) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expositionID, title, theme, dateFrom, dateTo, ticketPrice, hallID, description);
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "expositionID=" + expositionID +
                ", title='" + title + '\'' +
                ", theme='" + theme + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", ticketPrice=" + ticketPrice +
                ", hallID=" + hallID +
                ", description='" + description + '\'' +
                '}';
    }
}
