package com.example.rezeq.nusret.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.rezeq.nusret.adapters.ProductsAdapter;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.LoadMoreListener;
import com.example.rezeq.nusret.api.responses.CategoryPageResponse;
import com.example.rezeq.nusret.api.responses.GetCartResponse;
import com.example.rezeq.nusret.models.Product;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {

    Toolbar toolbar;
    AppCompatActivity activity;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Util util;
    Api api;
    ConstraintLayout cart;
    int page;
    boolean isLoading;

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
        util = new Util(getContext());
        api = new Api(getContext());
        page = 1;
        isLoading = true;

        if( ! util.isLoggedIn()){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        activity = ((AppCompatActivity) getActivity());
        recyclerView = view.findViewById(R.id.recycler);
        progressBar = view.findViewById(R.id.progressBar);
        int id = 1;
        if(getArguments() != null){
            id = getArguments().getInt("categoryId", 1);
        }

        final ArrayList<Product> products = new ArrayList<>();
        final int finalId = id;
        final ProductsAdapter mAdapter = new ProductsAdapter(products, getActivity());
        mAdapter.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void loadMore() {
                if( !isLoading && products.size() % 20 == 0){
                    page = products.size() / 20;
                    api.moreProducts(finalId, page, new ApiCallback() {
                        @Override
                        public void onSuccess(Object response) {
                            CategoryPageResponse pageResponse = (CategoryPageResponse) response;
                            if(pageResponse.isSuccess()){
                                products.addAll(pageResponse.getResult().getProducts());
                                mAdapter.notifyDataSetChanged();
                                isLoading = false;
                            }
                        }

                        @Override
                        public void onFailure(String msg) {
                            Toast.makeText(getContext(), msg,Toast.LENGTH_LONG).show();
                            isLoading = false;
                        }
                    });
                    isLoading = true;
                }
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext() , 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        api.categoryPage(id, new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                CategoryPageResponse pageResponse = (CategoryPageResponse) response;
                if(pageResponse.isSuccess()){
                    products.addAll(pageResponse.getResult().getProducts());
                    progressBar.setVisibility(View.GONE);
                    isLoading = false;
                }
            }

            @Override
            public void onFailure(String msg) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), msg,Toast.LENGTH_LONG).show();
                isLoading = false;
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
        toolbarTitle.setVisibility(View.GONE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.VISIBLE);

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

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
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
