package com.example.rezeq.nusret.api.responses;

/**
 * Created by Rezeq on 12/27/2017.
 * Email : rezeq.elewa@gmail.com
 */

public abstract class ApiResponse {
    private boolean status;

    public boolean isSuccess() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
