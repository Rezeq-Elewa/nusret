package com.example.rezeq.nusret.api.responses;

import com.example.rezeq.nusret.models.Ad;
import com.example.rezeq.nusret.models.Category;

import java.util.ArrayList;

/**
 * Created by Rezeq on 12/26/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class HomePageResponse extends ApiResponse {
    private Result result;
    private String error_num;
    private String error;

    public HomePageResponse() {
    }

    public HomePageResponse(Result result, String error_num, String error) {
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
        ArrayList<Ad> ads;
        ArrayList<Category> categoies;

        public Result() {
        }

        public Result(ArrayList<Ad> ads, ArrayList<Category> categoies) {
            this.ads = ads;
            this.categoies = categoies;
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
    }

}

