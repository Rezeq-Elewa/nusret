package com.example.rezeq.nusret.api.responses;

import com.example.rezeq.nusret.models.Product;

import java.util.ArrayList;

/**
 * Created by Rezeq on 12/26/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class CategoryPageResponse extends ApiResponse {
    private Result result;
    private String error_num;
    private String error;

    public CategoryPageResponse() {
    }

    public CategoryPageResponse(boolean status, Result result, String error_num, String error) {
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
        ArrayList<Product> products;
        String count;

        public Result() {
        }

        public Result(ArrayList<Product> products, String count) {
            this.products = products;
            this.count = count;
        }

        public ArrayList<Product> getProducts() {
            return products;
        }

        public void setProducts(ArrayList<Product> products) {
            this.products = products;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
