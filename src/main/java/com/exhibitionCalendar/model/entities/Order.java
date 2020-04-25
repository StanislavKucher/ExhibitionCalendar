package com.exhibitionCalendar.model.entities;

import java.sql.Date;
import java.util.Objects;

public class Order {

    private int orderID;
    private int userID;
    private int expositionID;
    private Date date;

    //default constructor
    public Order() {
    }

    public Order(int orderID, int userID, int expositionID, Date date) {
        this.orderID = orderID;
        this.userID = userID;
        this.expositionID = expositionID;
        this.date = date;
    }

    // Getters
    public int getOrderID() {
        return orderID;
    }

    public int getUserID() {
        return userID;
    }

    public int getExpositionID() {
        return expositionID;
    }

    public Date getDate() {
        return date;
    }

    // Setters
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setExpositionID(int expositionID) {
        this.expositionID = expositionID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Hash & equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderID == order.orderID &&
                userID == order.userID &&
                expositionID == order.expositionID &&
                Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, userID, expositionID, date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                ", expositionID=" + expositionID +
                ", date=" + date +
                '}';
    }
}
