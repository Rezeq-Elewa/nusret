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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.activities.LoginActivity;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.Urls;
import com.example.rezeq.nusret.api.responses.CartResponse;
import com.example.rezeq.nusret.api.responses.GetCartResponse;
import com.example.rezeq.nusret.api.responses.ShowProductResponse;
import com.example.rezeq.nusret.models.Product;
import com.example.rezeq.nusret.utility.BackPressListener;
import com.example.rezeq.nusret.utility.BackPressListenerActivity;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomButton;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.ArrayList;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;


public class ProductDetailsFragment extends Fragment {

    BannerSlider slider;
    CustomTextView name, price, amount, date, time, details;
    ImageView increaseAmount, decreaseAmount;
    CustomButton addToCart;
    Toolbar toolbar;
    AppCompatActivity activity;
    ProgressBar progressBar;
    int amountToAdd = 1;
    Util util;
    Api api;
    ConstraintLayout cart;
    Product product;

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
        api = new Api(getContext());

        if( ! util.isLoggedIn()){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }


        View view =  inflater.inflate(R.layout.fragment_product_details, container, false);
        slider = view.findViewById(R.id.slider);
        name = view.findViewById(R.id.productName);
        price = view.findViewById(R.id.productPrice);
        amount = view.findViewById(R.id.productAmount);
        amount.setText(String.valueOf(amountToAdd));
        date = view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);
        details = view.findViewById(R.id.details);
        increaseAmount = view.findViewById(R.id.increaseAmount);
        decreaseAmount = view.findViewById(R.id.decreaseAmount);
        addToCart = view.findViewById(R.id.addToCart);
        progressBar = view.findViewById(R.id.progressBar);
        activity = ((AppCompatActivity) getActivity());


        final ArrayList<Banner> banners = new ArrayList<>();

        int id = 0 ;
        if(getArguments() != null){
            ShowProductResponse productResponse = getArguments().getParcelable("productDetails");
            if(productResponse != null){
                product = productResponse.getResult().getProduct();
                id = Integer.parseInt(product.getId());
                name.setText(product.getName());
                price.setText(product.getPrice());
                String[] timeDate = product.getUpdated_at().split(" ");
                String dateText = timeDate[0].replaceAll("-","/");
                String timeText = timeDate[1].substring(0,timeDate[1].indexOf(":",3));
                date.setText(dateText);
                time.setText(timeText);
                details.setText(product.getDescription());
                banners.add(new RemoteBanner(Urls.IMAGE_URL + product.getImg()));
                slider.setBanners(banners);
            }else{
                activity.onBackPressed();
            }
        } else {
            activity.onBackPressed();
        }

        increaseAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountToAdd++;
                amount.setText(String.valueOf(amountToAdd));
            }
        });

        decreaseAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(amountToAdd > 1){
                    amountToAdd--;
                    amount.setText(String.valueOf(amountToAdd));
                }
            }
        });

        final int finalId = id;
        addToCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                api.addToCart(finalId,amountToAdd, new ApiCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        CartResponse cartResponse = (CartResponse) response;
                        if (cartResponse.isSuccess()){
                            Toast.makeText(getContext(), R.string.successfully_added_to_cart,Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            setCart();
                        }
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        Toast.makeText(getContext(), errorMsg,Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
        editToolbar();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        editToolbar();
    }

    public void editToolbar() {

        toolbar = activity.findViewById(R.id.toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setVisibility(View.GONE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.VISIBLE);

        ImageView back = toolbar.findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });

        cart = activity.findViewById(R.id.cart);

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

        cart.setVisibility(View.INVISIBLE);
        setCart();

        ((BackPressListenerActivity) activity).setListener(new BackPressListener() {
            @Override
            public void backPressed() {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment newFragment = new ProductsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId",Integer.parseInt(product.getCategory_id()));
                newFragment.setArguments(bundle);
                transaction.replace(R.id.fragment, newFragment);
                transaction.commit();
            }
        });
    }

    private void setCart(){
        api.getCart(new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                GetCartResponse cartResponse = (GetCartResponse) response;
                if(cartResponse.isSuccess()){
                    if (cartResponse.getResult().getCart().size() > 0) {
                        cart.setVisibility(View.VISIBLE);
                        TextView itemCount = activity.findViewById(R.id.count);
                        itemCount.setText(String.valueOf(cartResponse.getResult().getCart().size()));
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getContext(), msg,Toast.LENGTH_LONG).show();
            }
        });
    }
}
