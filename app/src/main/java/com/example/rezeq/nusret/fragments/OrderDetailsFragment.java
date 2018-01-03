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

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.activities.LoginActivity;
import com.example.rezeq.nusret.adapters.TableAdapter;
import com.example.rezeq.nusret.api.responses.OrderDetailsResponse;
import com.example.rezeq.nusret.models.Order;
import com.example.rezeq.nusret.models.OrderItem;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.ArrayList;

public class OrderDetailsFragment extends Fragment {

    CustomTextView number, value, itemCount, status, date, time, totalPrice;
    RecyclerView table;
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

        if( ! util.isLoggedIn()){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        OrderDetailsResponse orderDetailsResponse;


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

        final ArrayList<OrderItem> items = new ArrayList<>();
        final TableAdapter tableAdapter = new TableAdapter(items);
        table.setLayoutManager(new LinearLayoutManager(this.getContext()));
        table.setItemAnimator(new DefaultItemAnimator());
        table.setAdapter(tableAdapter);

        if(getArguments() != null){
            orderDetailsResponse = getArguments().getParcelable("orderDetails");
            if (orderDetailsResponse != null) {
                Order order = orderDetailsResponse.getResult().getOrder();
                number.setText(String.format("# %s", order.getId()));
                value.setText(order.getTotal());
                itemCount.setText(order.getItems());
                switch (order.getStatus()) {
                    case "0":
                        status.setText(R.string.open);
                        status.setBackgroundResource(R.drawable.status_opened_background);
                        break;
                    default:
                        status.setText(R.string.close);
                        status.setBackgroundResource(R.drawable.status_closed_background);
                }
                String[] timeDate = order.getCreated_at().split(" ");
                String dateText = timeDate[0].replaceAll("-", "/");
                String timeText = timeDate[1];
                date.setText(dateText);
                time.setText(timeText);
                totalPrice.setText(order.getTotal());
                items.addAll(orderDetailsResponse.getResult().getItems());
                tableAdapter.notifyDataSetChanged();
            } else {
                activity.onBackPressed();
            }
        } else {
            activity.onBackPressed();
        }


        editToolbar();
        return view;
    }

    public void editToolbar() {

        toolbar = activity.findViewById(R.id.toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.order_details_title);
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
