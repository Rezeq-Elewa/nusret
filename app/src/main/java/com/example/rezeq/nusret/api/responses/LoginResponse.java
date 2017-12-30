package com.example.rezeq.nusret.api.responses;

import com.example.rezeq.nusret.models.Ad;
import com.example.rezeq.nusret.models.Category;
import com.example.rezeq.nusret.models.Profile;

import java.util.ArrayList;

/**
 * Created by Rezeq on 12/26/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class LoginResponse extends ApiResponse {
    private Result result;
    private String error_num;
    private String error;

    public LoginResponse() {
    }

    public LoginResponse(boolean status, Result result, String error_num, String error) {
        this.setStatus(status);
        this.result = result;
        this.error_num = error_num;
        this.error = error;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getError_num() {
        return error_num;
    }

    public void setError_num(String error_num) {
        this.error_num = error_num;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public class Result{
        String token;
        Profile user;
        ArrayList<Ad> ads;
        ArrayList<Category> categoies;
        String unseen_count;

        public Result() {
        }

        public Result(String token, Profile user, ArrayList<Ad> ads, ArrayList<Category> categoies, String unseen_count) {
            this.token = token;
            this.user = user;
            this.ads = ads;
            this.categoies = categoies;
            this.unseen_count = unseen_count;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Profile getUser() {
            return user;
        }

        public void setUser(Profile user) {
            this.user = user;
        }

        public ArrayList<Ad> getAds() {
            return ads;
        }

        public void setAds(ArrayList<Ad> ads) {
            this.ads = ads;
        }

        public ArrayList<Category> getCategoies() {
            return categoies;
        }

        public void setCategoies(ArrayList<Category> categoies) {
            this.categoies = categoies;
        }

        public String getUnseen_count() {
            return unseen_count;
        }

        public void setUnseen_count(String unseen_count) {
            this.unseen_count = unseen_count;
        }
    }

}

