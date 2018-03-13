package com.example.rezeq.nusret.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.example.rezeq.nusret.utility.BackPressListener;
import com.example.rezeq.nusret.utility.BackPressListenerActivity;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.ArrayList;
import java.util.Locale;

public class OrderDetailsFragment extends Fragment {

    CustomTextView number, value, itemCount, status, date, time, totalPrice, discount;
    RecyclerView table;
    Toolbar toolbar;
    AppCompatActivity activity;
    Util util;
    ArrayList<OrderItem> items;

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
        discount = view.findViewById(R.id.fixes_discount);
        table = view.findViewById(R.id.table);
        activity = ((AppCompatActivity) getActivity());

        items = new ArrayList<>();
        final TableAdapter tableAdapter = new TableAdapter(this,items);
        table.setLayoutManager(new LinearLayoutManager(this.getContext()));
        table.setItemAnimator(new DefaultItemAnimator());
        table.setAdapter(tableAdapter);

        if(getArguments() != null){
            orderDetailsResponse = getArguments().getParcelable("orderDetails");
            if (orderDetailsResponse != null) {
                Order order = orderDetailsResponse.getResult().getOrder();
                number.setText(String.format("# %s", order.getId()));
                value.setText(order.getDiscount_total());
                itemCount.setText(order.getItems());
                switch (order.getStatus()){
                    case "0":
                        status.setText("قيد الانتظار");
                        status.setBackgroundResource(R.drawable.status_closed_background);
                        break;
                    case "1":
                        status.setText("تم التجهيز");
                        status.setBackgroundResource(R.drawable.discount_background);
                        break;
                    default:
                        status.setText("تم التسليم");
                        status.setBackgroundResource(R.drawable.status_opened_background);
                }
                String[] timeDate = order.getCreated_at().split(" ");
                String dateText = timeDate[0].replaceAll("-", "/");
                String timeText = timeDate[1];
                date.setText(dateText);
                time.setText(timeText);
                totalPrice.setText(order.getDiscount_total());
                items.addAll(orderDetailsResponse.getResult().getItems());
                setDiscount();
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
        toolbarTitle.setText(R.string.order_details_title);
        toolbarTitle.setVisibility(View.VISIBLE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.GONE);

        ImageView back = toolbar.findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });

        ConstraintLayout cart = activity.findViewById(R.id.cart);
        cart.setVisibility(View.GONE);

        ((BackPressListenerActivity) activity).setListener(new BackPressListener() {
            @Override
            public void backPressed() {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment newFragment = new OrdersFragment();
                transaction.replace(R.id.fragment, newFragment);
                transaction.commit();
            }
        });
    }

    public void setDiscount(){
        if ( ! items.isEmpty()){
            if ( ! items.get(0).getCoupon_discount().isEmpty() && ! items.get(0).getCoupon_discount().contains("%")){
                discount.setVisibility(View.VISIBLE);
                discount.setText(String.format(Locale.getDefault(),"خصم %s",items.get(0).getCoupon_discount()));
                return;
            }
        }
        discount.setVisibility(View.GONE);

    }

}
