package com.exhibitionCalendar.model.entities;

import java.util.Objects;

public class CreditCard {

    private int creditCardID;
    private String number;
    private int CVV;
    private double balance;
    private int month;
    private int year;
    private int userID;

    //default constructor
    public CreditCard() {
    }

    private CreditCard(Builder builder) {
        creditCardID = builder.creditCardID;
        number = builder.number;
        CVV = builder.CVV;
        balance = builder.balance;
        month = builder.month;
        year = builder.year;
        userID = builder.userID;
    }

    // Builder starts
    public static class Builder {

        private int creditCardID;
        private String number;
        private int CVV;
        private double balance;
        private int month;
        private int year;
        private int userID;

        public Builder() {
        }

        public Builder setCreditCardID(int creditCardID) {
            this.creditCardID = creditCardID;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setCVV(int CVV) {
            this.CVV = CVV;
            return this;
        }

        public Builder setBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder setMonth(int month) {
            this.month = month;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setUserID(int userID) {
            this.userID = userID;
            return this;
        }

        public CreditCard build() {
            return new CreditCard(this);
        }
    }
    // Builder ends

    // Getters
    public int getCreditCardID() {
        return creditCardID;
    }

    public String getNumber() {
        return number;
    }

    public int getCVV() {
        return CVV;
    }

    public double getBalance() {
        return balance;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getUserID() {
        return userID;
    }

    // Setters
    public void setCreditCardID(int creditCardID) {
        this.creditCardID = creditCardID;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    // Hash & equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return creditCardID == that.creditCardID &&
                CVV == that.CVV &&
                Double.compare(that.balance, balance) == 0 &&
                month == that.month &&
                year == that.year &&
                userID == that.userID &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardID, number, CVV, balance, month, year, userID);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardID=" + creditCardID +
                ", number='" + number + '\'' +
                ", CVV=" + CVV +
                ", balance=" + balance +
                ", month=" + month +
                ", year=" + year +
                ", userID=" + userID +
                '}';
    }
}
