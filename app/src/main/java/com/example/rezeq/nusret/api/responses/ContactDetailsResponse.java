package com.example.rezeq.nusret.api.responses;

/**
 * Created by Rezeq on 2/6/2018.
 * Email : rezeq.elewa@gmail.com
 */

public class ContactDetailsResponse extends ApiResponse {

    private Result result;
    private String error_num;
    private String error;

    public ContactDetailsResponse() {
    }

    public ContactDetailsResponse(Result result, String error_num, String error) {
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
        String email;
        String mobile;
        String facebook;
        String twitter;
        String instagram;
        String snapchat;

        public Result() {
        }

        public Result(String email, String mobile, String facebook, String twitter, String instagram, String snapchat) {
            this.email = email;
            this.mobile = mobile;
            this.facebook = facebook;
            this.twitter = twitter;
            this.instagram = instagram;
            this.snapchat = snapchat;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }

        public String getInstagram() {
            return instagram;
        }

        public void setInstagram(String instagram) {
            this.instagram = instagram;
        }

        public String getSnapchat() {
            return snapchat;
        }

        public void setSnapchat(String snapchat) {
            this.snapchat = snapchat;
        }
    }
}
