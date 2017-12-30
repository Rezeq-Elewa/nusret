package com.example.rezeq.nusret.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Rezeq on 12/27/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class Profile extends RealmObject{

    @PrimaryKey
    private int id;

    private String name;
    private String email;
    private String mobile;
    private String avatar;
    private String country;
    private String city;
    private String region;
    private String accessToken;

    public Profile() {
    }

    public Profile(int id, String name, String email, String mobile, String avatar, String country, String city, String region, String accessToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.avatar = avatar;
        this.country = country;
        this.city = city;
        this.region = region;
        this.accessToken = accessToken;
    }

    public Profile(Profile profile){
        this.setId(profile.getId());
        this.setName(profile.getName());
        this.setEmail(profile.getEmail());
        this.setMobile(profile.getMobile());
        this.setAvatar(profile.getAvatar());
        this.setCountry(profile.getCountry());
        this.setCity(profile.getCity());
        this.setRegion(profile.getRegion());
        this.setAccessToken(profile.getAccessToken());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
