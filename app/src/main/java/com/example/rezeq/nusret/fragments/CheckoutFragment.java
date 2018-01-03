package com.example.rezeq.nusret.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.activities.LoginActivity;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.responses.CreateOrderResponse;
import com.example.rezeq.nusret.api.responses.OrderDetailsResponse;
import com.example.rezeq.nusret.api.responses.UserProfileResponse;
import com.example.rezeq.nusret.models.CreateOrder;
import com.example.rezeq.nusret.models.Profile;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomButton;
import com.example.rezeq.nusret.views.CustomEditText;
import com.example.rezeq.nusret.views.CustomTextView;


public class CheckoutFragment extends Fragment {

    CustomEditText nameText, phoneText, emailText, countryText, cityText, townText, receiveText, payText;
    CustomButton checkoutButton;
    Toolbar toolbar;
    AppCompatActivity activity;
    Util util;

    public CheckoutFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        util = new Util(getContext());
        final Api api = new Api(getContext());

        if( ! util.isLoggedIn()){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        nameText = view.findViewById(R.id.name);
        phoneText = view.findViewById(R.id.phone);
        emailText = view.findViewById(R.id.email);
        countryText = view.findViewById(R.id.country);
        cityText = view.findViewById(R.id.city);
        townText = view.findViewById(R.id.town);
        receiveText = view.findViewById(R.id.receive);
        payText = view.findViewById(R.id.pay);
        checkoutButton = view.findViewById(R.id.checkout);
        activity = ((AppCompatActivity) getActivity());

        api.userProfile(new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                UserProfileResponse userProfileResponse = (UserProfileResponse) response;
                if(userProfileResponse.isSuccess()){
                    Profile profile = userProfileResponse.getResult().getProfile();
                    nameText.setText(profile.getName());
                    phoneText.setText(profile.getMobile());
                    emailText.setText(profile.getEmail());
                    countryText.setText(profile.getCountry());
                    cityText.setText(profile.getCity());
                    townText.setText(profile.getRegion());
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String email = emailText.getText().toString();
                String country = countryText.getText().toString();
                String city = cityText.getText().toString();
                String town = townText.getText().toString();
                String receiveWay = receiveText.getText().toString();
                String payWay = payText.getText().toString();
                CreateOrder order = new CreateOrder(name,email,country,city,town,receiveWay,payWay);
                api.createOrder(order , new ApiCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        CreateOrderResponse createOrderResponse = (CreateOrderResponse) response;
                        if(createOrderResponse.isSuccess()){
                            //TODO assign order details
                            api.orderDetails(Integer.parseInt(createOrderResponse.getResult().getOrder_id()), new ApiCallback() {
                                @Override
                                public void onSuccess(Object response) {
                                    OrderDetailsResponse orderDetailsResponse = (OrderDetailsResponse) response;
                                    if(orderDetailsResponse.isSuccess()){
                                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                                        Fragment newFragment = new OrderDetailsFragment();
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("orderDetails" , orderDetailsResponse);
                                        newFragment.setArguments(bundle);
                                        transaction.replace(R.id.fragment, newFragment);
                                        transaction.addToBackStack("orders");
                                        transaction.commit();
                                    }
                                }

                                @Override
                                public void onFailure(String msg) {
                                    Toast.makeText(activity, msg,Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        Toast.makeText(getContext(), msg,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        editToolbar();
        return view;
    }

    public void editToolbar() {

        toolbar = activity.findViewById(R.id.toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.cart_title);
        toolbarTitle.setVisibility(View.VISIBLE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.GONE);

        ConstraintLayout cart = activity.findViewById(R.id.cart);
        cart.setVisibility(View.GONE);


        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }
}
