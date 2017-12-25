package com.example.rezeq.nusret.fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomTextView;

public class ProductsFragment extends Fragment {

    Toolbar toolbar;
    AppCompatActivity activity;
    RecyclerView recyclerView;

    public ProductsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        activity = ((AppCompatActivity) getActivity());
        recyclerView = view.findViewById(R.id.recycler);
        editToolbar();
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

        int itemInCartCount = new Util().itemInCartCount();

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
                activity.onBackPressed();
            }
        });
    }

}
