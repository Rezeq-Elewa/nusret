package com.example.rezeq.nusret.api.responses;

/**
 * Created by Rezeq on 2/6/2018.
 * Email : rezeq.elewa@gmail.com
 */

public class AboutAppResponse extends ApiResponse {

    private Result result;
    private String error_num;
    private String error;

    public AboutAppResponse() {
    }

    public AboutAppResponse(Result result, String error_num, String error) {
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
        String about_app;

        public Result() {
        }

        public Result(String about_app) {
            this.about_app = about_app;
        }

        public String getAbout_app() {
            return about_app;
        }

        public void setAbout_app(String about_app) {
            this.about_app = about_app;
        }
    }
}
