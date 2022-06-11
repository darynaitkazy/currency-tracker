package ru.freedomfinance.model;

import javax.persistence.*;

@Entity
@Table(name = "usd")
public class Usd extends AbstractBaseEntity{
    private String date;
    private String price;
    private String difference;

    public Usd() {

    }

    public Usd(String date, String price, String difference) {
        this.date = date;
        this.price = price;
        this.difference = difference;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }
}
