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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Rezeq on 12/26/2017.
 * Email : rezeq.elewa@gmail.com
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST(Urls.REGISTER)
    Call<RegisterResponse> register (
            @Field("name") String name,
            @Field("mobile") String mobile
    );

    @POST(Urls.REQUEST_LOGIN_CODE)
    @FormUrlEncoded
    Call<RequestLoginCodeResponse> requestLoginCode (
            @Field("mobile") String mobile
    );

    @POST(Urls.LOGIN)
    @FormUrlEncoded
    Call<LoginResponse> login (
            @Field("mobile") String mobile,
            @Field("code") String code,
            @Field("device_type") int deviceType,
            @Field("device_token") String deviceToken,
            @Field("device_lang") String deviceLanguage
    );

    @GET(Urls.USER_PROFILE)
    Call<UserProfileResponse> userProfile (
            @Header("Authorization") String authHeader
    );

    @POST(Urls.EDIT_USER_PROFILE)
    @FormUrlEncoded
    Call<EditUserProfileResponse> editUserProfile (
            @Header("Authorization") String authHeader,
            @Field("name") String name,
            @Field("email") String email,
            @Field("avatar") String avatar,
            @Field("country") String country,
            @Field("city") String city,
            @Field("region") String town
    );

    @GET(Urls.LOGOUT)
    Call<LogoutResponse> logout (
            @Header("Authorization") String authHeader
    );

    @GET(Urls.HOME_PAGE)
    Call<HomePageResponse> homePage (
            @Header("Authorization") String authHeader
    );

    @GET(Urls.CATEGORY_PAGE)
    Call<CategoryPageResponse> categoryPage (
            @Header("Authorization") String authHeader,
            @Path("category_id") int categoryId
    );

    @GET(Urls.MORE_PRODUCTS)
    Call<CategoryPageResponse> moreProducts (
            @Header("Authorization") String authHeader,
            @Path("category_id") int categoryId,
            @Path("page") int page
    );

    @GET(Urls.SHOW_PRODUCT)
    Call<ShowProductResponse> showProduct (
            @Header("Authorization") String authHeader,
            @Path("product_id") int productId
    );

    @GET(Urls.ADD_TO_CART)
    Call<CartResponse> addToCart (
            @Header("Authorization") String authHeader,
            @Path("product_id") int productId
    );

    @GET(Urls.REMOVE_CART_ITEM)
    Call<CartResponse> removeFromCart (
            @Header("Authorization") String authHeader,
            @Path("item_id") int productId
    );

    @GET(Urls.GET_CART)
    Call<GetCartResponce> getCart (
            @Header("Authorization") String authHeader
    );

    @POST(Urls.CREATE_ORDER)
    @FormUrlEncoded
    Call<CreateOrderResponse> createOrder (
            @Header("Authorization") String authHeader,
            @Field("name") String name,
            @Field("email") String email,
            @Field("country") String country,
            @Field("city") String city,
            @Field("region") String town,
            @Field("delivery_way") String deliveryWay,
            @Field("pay_way") String payWay
    );

    @GET(Urls.USER_ORDERS)
    Call<UserOrdersResponse> userOrders (
            @Header("Authorization") String authHeader
    );

    @GET(Urls.MORE_ORDERS)
    Call<UserOrdersResponse> moreOrders (
            @Header("Authorization") String authHeader,
            @Path("page") int page
    );

    @GET(Urls.ORDER_DETAILS)
    Call<OrderDetailsResponse> orderDetails (
            @Header("Authorization") String authHeader,
            @Path("id") int orderId
    );
}