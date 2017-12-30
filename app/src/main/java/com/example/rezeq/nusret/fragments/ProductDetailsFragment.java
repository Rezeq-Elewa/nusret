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
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.activities.LoginActivity;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.responses.ShowProductResponse;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomButton;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.ArrayList;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.views.BannerSlider;


public class ProductDetailsFragment extends Fragment {

    BannerSlider slider;
    CustomTextView name, price, amount, date, time, details;
    ImageView increaseAmount, decreaseAmount;
    CustomButton addToCart;
    Toolbar toolbar;
    AppCompatActivity activity;
    int amountToAdd = 1;
    Util util;

    public ProductDetailsFragment() {
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
        if( ! util.isLoggedIn()){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        int id = 1;
        if(getArguments() != null){
            id = getArguments().getInt("productId", 1);
        }

        View view =  inflater.inflate(R.layout.fragment_product_details, container, false);
        slider = view.findViewById(R.id.slider);
        name = view.findViewById(R.id.productName);
        price = view.findViewById(R.id.productPrice);
        amount = view.findViewById(R.id.productAmount);
        date = view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);
        details = view.findViewById(R.id.details);
        increaseAmount = view.findViewById(R.id.increaseAmount);
        decreaseAmount = view.findViewById(R.id.decreaseAmount);
        addToCart = view.findViewById(R.id.addToCart);


        final ArrayList<Banner> banners = new ArrayList<>();

        slider.setBanners(banners);

        increaseAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountToAdd++;
                amount.setText(String.valueOf(amountToAdd));
                //TODO increase amount
            }
        });

        decreaseAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(amountToAdd > 1){
                    amountToAdd--;
                    amount.setText(String.valueOf(amountToAdd));
                    //TODO decrease amount
                }
            }
        });

        activity = ((AppCompatActivity) getActivity());

        editToolbar();

        Api api = new Api(getContext());
        api.showProduct(id, new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                ShowProductResponse productResponse = (ShowProductResponse) response;
                if(productResponse.isSuccess()){
                    //TODO assign product details
                    slider.removeAllBanners();
                    slider.setBanners(banners);
                }else {
                    //TODO show error
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getContext(), msg,Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    public void editToolbar() {

        toolbar = activity.findViewById(R.id.toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setVisibility(View.GONE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.VISIBLE);

        ConstraintLayout cart = activity.findViewById(R.id.cart);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment newFragment = new CartFragment();
                transaction.replace(R.id.fragment, newFragment);
                transaction.commit();
            }
        });

        int itemInCartCount = util.itemInCartCount();

        if(itemInCartCount > 0){
            cart.setVisibility(View.VISIBLE);
            TextView itemCount = activity.findViewById(R.id.count);
            itemCount.setText(String.valueOf(itemInCartCount));
        } else {
            cart.setVisibility(View.INVISIBLE);
        }

        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment, new ProductsFragment());
                transaction.commit();
            }
        });
    }
}
