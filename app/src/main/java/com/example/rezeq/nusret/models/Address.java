package com.example.rezeq.nusret.models;

import java.util.ArrayList;

/**
 * Created by Rezeq on 2/6/2018.
 * Email : rezeq.elewa@gmail.com
 */

public class Address {
    private String id;
    private String name;
    private String currency;
    private String code;
    private ArrayList<City> cities;

    public Address() {
    }

    public Address(String id, String name, String currency, String code, ArrayList<City> cities) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.code = code;
        this.cities = cities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return name;
    }
}
