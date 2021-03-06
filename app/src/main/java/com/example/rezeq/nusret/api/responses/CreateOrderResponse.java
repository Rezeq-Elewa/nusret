package com.example.rezeq.nusret.api.responses;

/**
 * Created by Rezeq on 12/26/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class CreateOrderResponse extends ApiResponse {

    private Result result;
    private String error_num;
    private String error;

    public CreateOrderResponse() {
    }

    public CreateOrderResponse(boolean status, Result result, String error_num, String error) {
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
        private String order_id;

        public Result() {
        }

        public Result(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
    }
}
