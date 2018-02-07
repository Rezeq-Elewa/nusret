package com.example.rezeq.nusret.models;

/**
 * Created by Rezeq on 2/6/2018.
 * Email : rezeq.elewa@gmail.com
 */

public class City {
    private String id;
    private String country_id;
    private String name;

    public City() {
    }

    public City(String id, String country_id, String name) {
        this.id = id;
        this.country_id = country_id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
