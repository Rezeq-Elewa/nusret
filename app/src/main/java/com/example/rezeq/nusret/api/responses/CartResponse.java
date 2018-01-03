package com.example.rezeq.nusret.api.responses;

import java.util.ArrayList;

/**
 * Created by Rezeq on 12/26/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class CartResponse extends ApiResponse {

    private ArrayList<String> result;
    private String error_num;
    private String error;

    public CartResponse() {
    }

    public CartResponse(boolean status, ArrayList<String> result, String error_num, String error) {
        this.setStatus(status);
        this.result = result;
        this.error_num = error_num;
        this.error = error;
    }

    public ArrayList<String> getResult() {
        return result;
    }

    public void setResult(ArrayList<String> result) {
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
}
