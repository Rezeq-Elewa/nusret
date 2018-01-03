package com.example.rezeq.nusret.api;

/**
 * Created by Rezeq on 12/26/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class Urls {
    public static final String BASE_URL = "http://139.59.160.147/";
    public static final String IMAGE_URL = "http://139.59.160.147/uploads/";
    public static final String REGISTER = "api/register";
    public static final String REQUEST_LOGIN_CODE = "api/request_login";
    public static final String LOGIN = "api/login";
    public static final String LOGOUT = "api/logout";
    public static final String CHANGE_USER_LANGUAGE = "api/change_lang";
    public static final String USER_PROFILE = "api/profile";
    public static final String EDIT_USER_PROFILE = "api/profile";
    public static final String GET_NOTIFICATION = "api/get_notification";
    public static final String GET_MORE_NOTIFICATION = "api/get_notification/{page_num}";
    public static final String GET_COUNT_OF_UNSEEN_NOTIFICATION = "api/notification_count";
    public static final String HOME_PAGE = "api/home";
    public static final String CATEGORY_PAGE = "api/products/{category_id}";
    public static final String MORE_PRODUCTS = "api/products/{category_id}/{page}";
    public static final String SHOW_PRODUCT = "api/product/{product_id}";
    public static final String ADD_TO_CART = "api/addToCart/{product_id}";
    public static final String REMOVE_CART_ITEM = "api/removeItem/{item_id}";
    public static final String GET_CART = "api/getCart";
    public static final String APPLY_COUPON = "api/applyCoupon/{coupon_code}";
    public static final String CREATE_ORDER = "api/createOrder";
    public static final String USER_ORDERS = "api/my_orders";
    public static final String MORE_ORDERS = "api/my_orders/{page}";
    public static final String ORDER_DETAILS = "api/order/{id}";
    public static final String SET_AMOUNT = "api/set_amount";
}
