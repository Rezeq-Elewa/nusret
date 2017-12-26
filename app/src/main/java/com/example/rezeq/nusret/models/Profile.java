package com.example.rezeq.nusret.models;

/**
 * Created by Rezeq on 12/27/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class Profile {
    private String phone;
    private String name;
    private String email;
    private String avatar;
    private String country;
    private String city;
    private String town;

    public Profile() {
    }

    public Profile(String phone, String name, String email, String avatar, String country, String city, String town) {
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.country = country;
        this.city = city;
        this.town = town;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
}
