package com.example.rezeq.nusret.models;

/**
 * Created by Rezeq on 12/27/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class CreateOrder {
    private String name;
    private String email;
    private String country;
    private String city;
    private String town;
    private String deliveryWay;
    private String payWay;

    public CreateOrder() {
    }

    public CreateOrder(String name, String email, String country, String city, String town, String deliveryWay, String payWay) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.city = city;
        this.town = town;
        this.deliveryWay = deliveryWay;
        this.payWay = payWay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDeliveryWay() {
        return deliveryWay;
    }

    public void setDeliveryWay(String deliveryWay) {
        this.deliveryWay = deliveryWay;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public boolean validate(){
        return name != null && ! name.equalsIgnoreCase("") && !name.equalsIgnoreCase(" ")
                && email != null && ! email.equalsIgnoreCase("") && !email.equalsIgnoreCase(" ")
                && email.contains("@") && email.contains(".")
                && country != null && ! country.equalsIgnoreCase("") && !country.equalsIgnoreCase(" ")
                && city != null && ! city.equalsIgnoreCase("") && !city.equalsIgnoreCase(" ")
                && town != null && ! town.equalsIgnoreCase("") && !town.equalsIgnoreCase(" ")
                && deliveryWay != null && ! deliveryWay.equalsIgnoreCase("") && !deliveryWay.equalsIgnoreCase(" ")
                && payWay != null && ! payWay.equalsIgnoreCase("") && !payWay.equalsIgnoreCase(" ");
    }
}
