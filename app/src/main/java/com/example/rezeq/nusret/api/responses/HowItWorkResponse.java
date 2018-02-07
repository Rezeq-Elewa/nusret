package com.example.rezeq.nusret.api.responses;

/**
 * Created by Rezeq on 2/6/2018.
 * Email : rezeq.elewa@gmail.com
 */

public class HowItWorkResponse extends ApiResponse {

    private Result result;
    private String error_num;
    private String error;

    public HowItWorkResponse() {
    }

    public HowItWorkResponse(Result result, String error_num, String error) {
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
        String how_it_work;

        public Result() {
        }

        public Result(String how_it_work) {
            this.how_it_work = how_it_work;
        }

        public String getHow_it_work() {
            return how_it_work;
        }

        public void setHow_it_work(String how_it_work) {
            this.how_it_work = how_it_work;
        }
    }
}
