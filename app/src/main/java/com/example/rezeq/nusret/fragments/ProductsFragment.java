package com.example.rezeq.nusret.fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.adapters.CategoriesAdapter;
import com.example.rezeq.nusret.adapters.ProductsAdapter;
import com.example.rezeq.nusret.models.Product;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.ArrayList;

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
        int id;
        if(getArguments() != null){
            id = getArguments().getInt("categoryID", 1);
        }else{
            id = 1;
        }

        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(1,"دجاج",19.9,"https://cdn.shopify.com/s/files/1/1044/2210/products/Screenshot_2016-01-16_19.09.48_1024x1024.png?v=1452942625"));
        products.add(new Product(2,"لحم خروف",40,"https://teara.govt.nz/files/p19214pc.jpg"));
        products.add(new Product(3,"لحم بقر",35.5,"http://images.wisegeek.com/raw-beef-with-garnish.jpg"));
        products.add(new Product(4,"افخاد دجاج",18,"https://cdn.shopify.com/s/files/1/1044/2210/products/Screenshot_2016-01-16_19.09.48_1024x1024.png?v=1452942625"));
        products.add(new Product(5,"لحم ماعز",37,"https://teara.govt.nz/files/p19214pc.jpg"));
        products.add(new Product(6,"لحم عجل",32,"http://images.wisegeek.com/raw-beef-with-garnish.jpg"));

        ProductsAdapter mAdapter = new ProductsAdapter(products , getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext() , 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

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
