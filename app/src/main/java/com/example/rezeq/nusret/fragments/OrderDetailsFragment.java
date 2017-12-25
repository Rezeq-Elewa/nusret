package com.example.rezeq.nusret.fragments;

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

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.views.CustomTextView;
import com.makeramen.roundedimageview.RoundedImageView;

public class OrderDetailsFragment extends Fragment {

    CustomTextView number, value, itemCount, status, date, time, totalPrice;
    RoundedImageView statusImage;
    TableLayout table;
    Toolbar toolbar;
    AppCompatActivity activity;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_details, container, false);
        number = view.findViewById(R.id.number);
        value = view.findViewById(R.id.orderValue);
        itemCount = view.findViewById(R.id.itemCount);
        status = view.findViewById(R.id.status);
        date = view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);
        totalPrice = view.findViewById(R.id.totalPrice);
        statusImage = view.findViewById(R.id.statusImage);
        table = view.findViewById(R.id.table);
        activity = ((AppCompatActivity) getActivity());

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
