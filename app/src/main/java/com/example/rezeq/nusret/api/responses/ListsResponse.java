package com.example.rezeq.nusret.api.responses;

import com.example.rezeq.nusret.models.Address;
import com.example.rezeq.nusret.models.Cutting;

import java.util.ArrayList;

/**
 * Created by Rezeq on 2/6/2018.
 * Email : rezeq.elewa@gmail.com
 */

public class ListsResponse extends ApiResponse {

    private Result result;
    private String error_num;
    private String error;

    public ListsResponse() {
    }

    public ListsResponse(Result result, String error_num, String error) {
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

    public class Result {
        ArrayList<Address> address;
        ArrayList<Cutting> cutting;

        public Result() {
        }

        public Result(ArrayList<Address> address, ArrayList<Cutting> cutting) {
            this.address = address;
            this.cutting = cutting;
        }

        public ArrayList<Address> getAddress() {
            return address;
        }

        public void setAddress(ArrayList<Address> address) {
            this.address = address;
        }

        public ArrayList<Cutting> getCutting() {
            return cutting;
        }

        public void setCutting(ArrayList<Cutting> cutting) {
            this.cutting = cutting;
        }
    }
}
