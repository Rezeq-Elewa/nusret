package com.example.rezeq.nusret.models;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class Order {
    private int number, value, itemCount;
    private String status, date, time;

    public Order() {
    }

    public Order(int number, int value, int itemCount, String status, String date, String time) {
        this.number = number;
        this.value = value;
        this.itemCount = itemCount;
        this.status = status;
        this.date = date;
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
