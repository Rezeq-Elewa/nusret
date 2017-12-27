package com.example.rezeq.nusret.api;

import com.example.rezeq.nusret.api.responses.CartResponse;
import com.example.rezeq.nusret.api.responses.CategoryPageResponse;
import com.example.rezeq.nusret.api.responses.CreateOrderResponse;
import com.example.rezeq.nusret.api.responses.EditUserProfileResponse;
import com.example.rezeq.nusret.api.responses.GetCartResponce;
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

    private static Api instance;
    private ApiInterface client;

    private Api() {
        String API_BASE_URL = "http://eservices.mtit.gov.ps/";
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        client = retrofit.create(ApiInterface.class);
    }

    public static Api getInstance() {
        if (instance == null)
            instance = new Api();
        return instance;
    }

    public void register(String name, String mobile, final ApiCallback callback) {
        Call<RegisterResponse> mCall = client.register(name, mobile);
        mCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void requestLoginCode(String mobile, final ApiCallback callback) {
        Call<RequestLoginCodeResponse> mCall = client.requestLoginCode(mobile);
        mCall.enqueue(new Callback<RequestLoginCodeResponse>() {
            @Override
            public void onResponse(Call<RequestLoginCodeResponse> call, Response<RequestLoginCodeResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<RequestLoginCodeResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void login(String mobile, String code, final ApiCallback callback) {
        Call<LoginResponse> mCall = client.login(mobile, code, 1,deviceToken(),deviceLanguage());
        mCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void userProfile(final ApiCallback callback) {
        Call<UserProfileResponse> mCall = client.userProfile(authHeader());
        mCall.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void editUserProfile(Profile userProfile, final ApiCallback callback) {
        Call<EditUserProfileResponse> mCall = client.editUserProfile(authHeader(), userProfile.getName(), userProfile.getEmail(), userProfile.getAvatar(), userProfile.getCountry(), userProfile.getCity(), userProfile.getTown());
        mCall.enqueue(new Callback<EditUserProfileResponse>() {
            @Override
            public void onResponse(Call<EditUserProfileResponse> call, Response<EditUserProfileResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<EditUserProfileResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void logout(final ApiCallback callback) {
        Call<LogoutResponse> mCall = client.logout(authHeader());
        mCall.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void homePage(final ApiCallback callback) {
        Call<HomePageResponse> mCall = client.homePage(authHeader());
        mCall.enqueue(new Callback<HomePageResponse>() {
            @Override
            public void onResponse(Call<HomePageResponse> call, Response<HomePageResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<HomePageResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void categoryPage(int categoryId, final ApiCallback callback) {
        Call<CategoryPageResponse> mCall = client.categoryPage(authHeader(), categoryId);
        mCall.enqueue(new Callback<CategoryPageResponse>() {
            @Override
            public void onResponse(Call<CategoryPageResponse> call, Response<CategoryPageResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<CategoryPageResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void moreProducts(int categoryId, int page, final ApiCallback callback) {
        Call<CategoryPageResponse> mCall = client.moreProducts(authHeader(), categoryId, page);
        mCall.enqueue(new Callback<CategoryPageResponse>() {
            @Override
            public void onResponse(Call<CategoryPageResponse> call, Response<CategoryPageResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<CategoryPageResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void showProduct(int productId, final ApiCallback callback) {
        Call<ShowProductResponse> mCall = client.showProduct(authHeader(), productId);
        mCall.enqueue(new Callback<ShowProductResponse>() {
            @Override
            public void onResponse(Call<ShowProductResponse> call, Response<ShowProductResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ShowProductResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void addToCart(int productId, final ApiCallback callback) {
        Call<CartResponse> mCall = client.addToCart(authHeader(), productId);
        mCall.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void removeFromCart(int productId, final ApiCallback callback) {
        Call<CartResponse> mCall = client.removeFromCart(authHeader(), productId);
        mCall.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void getCart(final ApiCallback callback) {
        Call<GetCartResponce> mCall = client.getCart(authHeader());
        mCall.enqueue(new Callback<GetCartResponce>() {
            @Override
            public void onResponse(Call<GetCartResponce> call, Response<GetCartResponce> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<GetCartResponce> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void createOrder(CreateOrder order, final ApiCallback callback) {
        Call<CreateOrderResponse> mCall = client.createOrder(authHeader(),order.getName(),order.getEmail(),order.getCountry(),order.getCity(),order.getTown(),order.getDeliveryWay(),order.getPayWay());
        mCall.enqueue(new Callback<CreateOrderResponse>() {
            @Override
            public void onResponse(Call<CreateOrderResponse> call, Response<CreateOrderResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<CreateOrderResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void userOrders (final ApiCallback callback) {
        Call<UserOrdersResponse> mCall = client.userOrders(authHeader());
        mCall.enqueue(new Callback<UserOrdersResponse>() {
            @Override
            public void onResponse(Call<UserOrdersResponse> call, Response<UserOrdersResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<UserOrdersResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void moreOrders (int page, final ApiCallback callback) {
        Call<UserOrdersResponse> mCall = client.moreOrders(authHeader(), page);
        mCall.enqueue(new Callback<UserOrdersResponse>() {
            @Override
            public void onResponse(Call<UserOrdersResponse> call, Response<UserOrdersResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<UserOrdersResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void orderDetails (int orderId, final ApiCallback callback) {
        Call<OrderDetailsResponse> mCall = client.orderDetails(authHeader(), orderId);
        mCall.enqueue(new Callback<OrderDetailsResponse>() {
            @Override
            public void onResponse(Call<OrderDetailsResponse> call, Response<OrderDetailsResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<OrderDetailsResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    private String authHeader() {
        Util util = new Util();
        return "Bearer" + util.getAccessToken();
    }

    private String deviceToken(){
        return " ";
    }

    private String deviceLanguage(){
        Util util = new Util();
        return util.getDeviceLanguage();
    }
}
