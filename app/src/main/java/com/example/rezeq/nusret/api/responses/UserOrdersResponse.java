package com.example.rezeq.nusret.api.responses;

import com.example.rezeq.nusret.models.Order;

import java.util.ArrayList;

/**
 * Created by Rezeq on 12/26/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class UserOrdersResponse extends ApiResponse {

    private Result result;
    private String error_num;
    private String error;

    public UserOrdersResponse() {
    }

    public UserOrdersResponse(boolean status, Result result, String error_num, String error) {
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
        private ArrayList<Order> orders;
        private String orders_count;

        public Result() {
        }

        public Result(ArrayList<Order> orders, String orders_count) {
            this.orders = orders;
            this.orders_count = orders_count;
        }

        public ArrayList<Order> getOrders() {
            return orders;
        }

        public void setOrders(ArrayList<Order> orders) {
            this.orders = orders;
        }

        public String getOrders_count() {
            return orders_count;
        }

        public void setOrders_count(String orders_count) {
            this.orders_count = orders_count;
        }
    }

}
