package com.example.rezeq.nusret.api;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.rezeq.nusret.api.responses.CartResponse;
import com.example.rezeq.nusret.api.responses.CategoryPageResponse;
import com.example.rezeq.nusret.api.responses.CreateOrderResponse;
import com.example.rezeq.nusret.api.responses.EditUserProfileResponse;
import com.example.rezeq.nusret.api.responses.GetCartResponse;
import com.example.rezeq.nusret.api.responses.HomePageResponse;
import com.example.rezeq.nusret.api.responses.LoginResponse;
import com.example.rezeq.nusret.api.responses.LogoutResponse;
import com.example.rezeq.nusret.api.responses.OrderDetailsResponse;
import com.example.rezeq.nusret.api.responses.RegisterResponse;
import com.example.rezeq.nusret.api.responses.RequestLoginCodeResponse;
import com.example.rezeq.nusret.api.responses.ShowProductResponse;
import com.example.rezeq.nusret.api.responses.UserOrdersResponse;
import com.example.rezeq.nusret.api.responses.UserProfileResponse;
import com.example.rezeq.nusret.models.CreateOrder;
import com.example.rezeq.nusret.models.Profile;
import com.example.rezeq.nusret.utility.Util;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rezeq on 12/26/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class Api {

    private ApiInterface client;
    private Util util;

    public Api(Context context) {
        String API_BASE_URL = Urls.BASE_URL;
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        client = retrofit.create(ApiInterface.class);
        util = new Util(context);
    }


    public void register(String name, String mobile, final ApiCallback callback) {
        Call<RegisterResponse> mCall = client.register(name, mobile);
        mCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {

                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 422:
                            callback.onFailure("The mobile has already been taken");
                            break;
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void requestLoginCode(String mobile, final ApiCallback callback) {
        Call<RequestLoginCodeResponse> mCall = client.requestLoginCode(mobile);
        mCall.enqueue(new Callback<RequestLoginCodeResponse>() {
            @Override
            public void onResponse(@NonNull Call<RequestLoginCodeResponse> call, @NonNull Response<RequestLoginCodeResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    if(response.isSuccessful()){
                        callback.onSuccess(response.body());
                    } else {
                        switch (response.code()){
                            case 401:
                                callback.onFailure("invalid mobile number");
                                break;
                            default:
                                callback.onFailure("unknown error");
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RequestLoginCodeResponse> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void login(String mobile, String code, final ApiCallback callback) {
        Call<LoginResponse> mCall = client.login(mobile, code, 1,deviceToken(),deviceLanguage());
        mCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 401:
                            callback.onFailure("Incorrect validation code");
                            break;
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void userProfile(final ApiCallback callback) {
        Call<UserProfileResponse> mCall = client.userProfile(authHeader());
        mCall.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserProfileResponse> call,@NonNull  Response<UserProfileResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 400:
                            callback.onFailure("Invalid token");
                            break;
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserProfileResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void editUserProfile(Profile userProfile, final ApiCallback callback) {
        Call<EditUserProfileResponse> mCall = client.editUserProfile(authHeader(), userProfile.getName(), userProfile.getEmail(), userProfile.getAvatar(), userProfile.getCountry(), userProfile.getCity(), userProfile.getRegion());
        mCall.enqueue(new Callback<EditUserProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<EditUserProfileResponse> call,@NonNull  Response<EditUserProfileResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 400:
                            callback.onFailure("Invalid token");
                            break;
                        case 422:
                            callback.onFailure("The name field is required");
                            break;
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<EditUserProfileResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void logout(final ApiCallback callback) {
        Call<LogoutResponse> mCall = client.logout(authHeader());
        mCall.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(@NonNull Call<LogoutResponse> call,@NonNull  Response<LogoutResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LogoutResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void homePage(final ApiCallback callback) {
        Call<HomePageResponse> mCall = client.homePage(authHeader());
        mCall.enqueue(new Callback<HomePageResponse>() {
            @Override
            public void onResponse(@NonNull Call<HomePageResponse> call,@NonNull  Response<HomePageResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<HomePageResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void categoryPage(int categoryId, final ApiCallback callback) {
        Call<CategoryPageResponse> mCall = client.categoryPage(authHeader(), categoryId);
        mCall.enqueue(new Callback<CategoryPageResponse>() {
            @Override
            public void onResponse(@NonNull Call<CategoryPageResponse> call,@NonNull  Response<CategoryPageResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 404:
                            callback.onFailure("Invalid category");
                            break;
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CategoryPageResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void moreProducts(int categoryId, int page, final ApiCallback callback) {
        Call<CategoryPageResponse> mCall = client.moreProducts(authHeader(), categoryId, page);
        mCall.enqueue(new Callback<CategoryPageResponse>() {
            @Override
            public void onResponse(@NonNull Call<CategoryPageResponse> call,@NonNull  Response<CategoryPageResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 404:
                            callback.onFailure("Invalid category");
                            break;
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CategoryPageResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void showProduct(int productId, final ApiCallback callback) {
        Call<ShowProductResponse> mCall = client.showProduct(authHeader(), productId);
        mCall.enqueue(new Callback<ShowProductResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShowProductResponse> call,@NonNull  Response<ShowProductResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 404:
                            callback.onFailure("no product id");
                            break;
                        case 422:
                            callback.onFailure("Product not found");
                            break;
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ShowProductResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void addToCart(int productId, final ApiCallback callback) {
        Call<CartResponse> mCall = client.addToCart(authHeader(), productId);
        mCall.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(@NonNull Call<CartResponse> call,@NonNull  Response<CartResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 404:
                            callback.onFailure("no product id");
                            break;
                        case 500:
                            callback.onFailure("invalid token");
                            break;
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CartResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void setAmount(int productId, int amount, final ApiCallback callback) {
        Call<CartResponse> mCall = client.setAmount(authHeader(), productId, amount);
        mCall.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(@NonNull Call<CartResponse> call,@NonNull  Response<CartResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 422:
                            callback.onFailure("incorrect product id");
                            break;
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CartResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void removeFromCart(int productId, final ApiCallback callback) {
        Call<CartResponse> mCall = client.removeFromCart(authHeader(), productId);
        mCall.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(@NonNull Call<CartResponse> call,@NonNull  Response<CartResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 404:
                            callback.onFailure("no product id");
                            break;
                        case 500:
                            callback.onFailure("invalid token");
                            break;
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CartResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getCart(final ApiCallback callback) {
        Call<GetCartResponse> mCall = client.getCart(authHeader());
        mCall.enqueue(new Callback<GetCartResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetCartResponse> call, @NonNull  Response<GetCartResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 500:
                            callback.onFailure("invalid token");
                            break;
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetCartResponse> call, @NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void createOrder(CreateOrder order, final ApiCallback callback) {
        Call<CreateOrderResponse> mCall = client.createOrder(authHeader(),order.getName(),order.getEmail(),order.getCountry(),order.getCity(),order.getTown(),order.getDeliveryWay(),order.getPayWay());
        mCall.enqueue(new Callback<CreateOrderResponse>() {
            @Override
            public void onResponse(@NonNull Call<CreateOrderResponse> call,@NonNull  Response<CreateOrderResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 422:
                            callback.onFailure("please add item to cart and fill all fields");
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CreateOrderResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void userOrders (final ApiCallback callback) {
        Call<UserOrdersResponse> mCall = client.userOrders(authHeader());
        mCall.enqueue(new Callback<UserOrdersResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserOrdersResponse> call,@NonNull  Response<UserOrdersResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 500:
                            callback.onFailure("invalid token");
                            break;
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserOrdersResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void moreOrders (int page, final ApiCallback callback) {
        Call<UserOrdersResponse> mCall = client.moreOrders(authHeader(), page);
        mCall.enqueue(new Callback<UserOrdersResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserOrdersResponse> call,@NonNull  Response<UserOrdersResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){

                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserOrdersResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void orderDetails (int orderId, final ApiCallback callback) {
        Call<OrderDetailsResponse> mCall = client.orderDetails(authHeader(), orderId);
        mCall.enqueue(new Callback<OrderDetailsResponse>() {
            @Override
            public void onResponse(@NonNull Call<OrderDetailsResponse> call,@NonNull  Response<OrderDetailsResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()){
                        case 404:
                            callback.onFailure("no offer id");
                            break;
                        case 422:
                            callback.onFailure("offer not found");
                            break;
                        default:
                            callback.onFailure("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<OrderDetailsResponse> call,@NonNull  Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    private String authHeader() {
        return "Bearer" + util.getAccessToken();
    }

    private String deviceToken(){
        return " ";
    }

    private String deviceLanguage(){
        return util.getDeviceLanguage();
    }
}
