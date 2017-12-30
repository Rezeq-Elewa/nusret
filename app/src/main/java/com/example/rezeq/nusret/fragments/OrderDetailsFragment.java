package com.example.rezeq.nusret.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.activities.LoginActivity;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.responses.OrderDetailsResponse;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomTextView;

public class OrderDetailsFragment extends Fragment {

    CustomTextView number, value, itemCount, status, date, time, totalPrice;
    TableLayout table;
    Toolbar toolbar;
    AppCompatActivity activity;
    Util util;

    public OrderDetailsFragment() {
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
        Api api = new Api(getContext());

        if( ! util.isLoggedIn()){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        int id = 1;
        if(getArguments() != null){
            id = getArguments().getInt("orderId", 1);
        }

        View view = inflater.inflate(R.layout.fragment_order_details, container, false);
        number = view.findViewById(R.id.number);
        value = view.findViewById(R.id.orderValue);
        itemCount = view.findViewById(R.id.itemCount);
        status = view.findViewById(R.id.status);
        date = view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);
        totalPrice = view.findViewById(R.id.totalPrice);
        table = view.findViewById(R.id.table);
        activity = ((AppCompatActivity) getActivity());

        api.orderDetails(id , new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                OrderDetailsResponse orderDetailsResponse = (OrderDetailsResponse) response;
                if(orderDetailsResponse.isSuccess()){
                    //TODO assign order details
                }else {
                    //TODO show error
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getContext(), msg,Toast.LENGTH_LONG).show();
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
        toolbarTitle.setText("تفاصيل الطلب");
        toolbarTitle.setVisibility(View.VISIBLE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.GONE);

        ConstraintLayout cart = activity.findViewById(R.id.cart);
        cart.setVisibility(View.GONE);

        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

}
