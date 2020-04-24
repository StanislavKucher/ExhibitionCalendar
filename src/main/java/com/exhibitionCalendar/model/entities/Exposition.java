package com.exhibitionCalendar.model.entities;

import java.sql.Date;

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

}
