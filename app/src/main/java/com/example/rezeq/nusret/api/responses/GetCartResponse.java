package com.example.rezeq.nusret.api.responses;

import com.example.rezeq.nusret.models.CartItem;

import java.util.ArrayList;

/**
 * Created by Rezeq on 12/26/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class GetCartResponse extends ApiResponse {

    private Result result;
    private String error_num;
    private String error;

    public GetCartResponse() {
    }

    public GetCartResponse(boolean status, Result result, String error_num, String error) {
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
        private ArrayList<CartItem> cart;
        private String total;

        public Result() {
        }

        public Result(ArrayList<CartItem> cart, String total) {
            this.cart = cart;
            this.total = total;
        }

        public ArrayList<CartItem> getCart() {
            return cart;
        }

        public void setCart(ArrayList<CartItem> cart) {
            this.cart = cart;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }

}
