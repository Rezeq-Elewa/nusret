package com.example.rezeq.nusret.api.responses;

/**
 * Created by Rezeq on 3/6/2018.
 * Email : rezeq.elewa@gmail.com
 */

public class CouponResponse extends ApiResponse {
    private String errorNumber;
    private String error;

    public CouponResponse() {
    }

    public CouponResponse(String errorNumber, String error) {
        this.errorNumber = errorNumber;
        this.error = error;
    }

    public String getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(String errorNumber) {
        this.errorNumber = errorNumber;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
