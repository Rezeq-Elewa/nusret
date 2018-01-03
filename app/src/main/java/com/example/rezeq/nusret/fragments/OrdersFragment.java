package com.example.rezeq.nusret.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.activities.LoginActivity;
import com.example.rezeq.nusret.adapters.OrdersAdapter;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.LoadMoreListener;
import com.example.rezeq.nusret.api.responses.UserOrdersResponse;
import com.example.rezeq.nusret.models.Order;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.ArrayList;


public class OrdersFragment extends Fragment {

    RecyclerView recyclerView;
    Toolbar toolbar;
    AppCompatActivity activity;
    Util util;
    ProgressBar progressBar;
    int page;
    boolean isLoading;

    public OrdersFragment() {
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
        isLoading = true;

        if( ! util.isLoggedIn()){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        progressBar = view.findViewById(R.id.progressBar);
        activity = ((AppCompatActivity) getActivity());

        final ArrayList<Order> orders = new ArrayList<>();
        final OrdersAdapter adapter = new OrdersAdapter(orders,getActivity());
        adapter.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void loadMore() {
                if( !isLoading && orders.size() % 20 == 0) {
                    page = orders.size() / 20;
                    api.moreOrders(page, new ApiCallback() {
                        @Override
                        public void onSuccess(Object response) {
                            UserOrdersResponse userOrdersResponse = (UserOrdersResponse) response;
                            if (userOrdersResponse.isSuccess()) {
                                orders.addAll(userOrdersResponse.getResult().getOrders());
                                adapter.notifyDataSetChanged();
                                isLoading = false;
                            }
                        }

                        @Override
                        public void onFailure(String msg) {
                            Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
                            isLoading = false;
                        }
                    });
                    isLoading = true;
                }
            }
        });

        api.userOrders(new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                UserOrdersResponse userOrdersResponse = (UserOrdersResponse) response;
                if(userOrdersResponse.isSuccess()){
                    orders.addAll(userOrdersResponse.getResult().getOrders());
                    adapter.notifyDataSetChanged();
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

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        editToolbar();
        return view;
    }





    public void editToolbar() {

        toolbar = activity.findViewById(R.id.toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.my_order_title);
        toolbarTitle.setVisibility(View.VISIBLE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.GONE);

        ConstraintLayout cart = activity.findViewById(R.id.cart);
        cart.setVisibility(View.GONE);

    }


}
