package com.example.rezeq.nusret.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.api.LoadMoreListener;
import com.example.rezeq.nusret.fragments.OrderDetailsFragment;
import com.example.rezeq.nusret.models.Order;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.List;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    private List<Order> orders;
    private FragmentActivity activity;
    private LoadMoreListener loadMoreListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CustomTextView number, value, itemCount, date, time, status;
        private View view;

        MyViewHolder(View view) {
            super(view);
            number = view.findViewById(R.id.number);
            value = view.findViewById(R.id.orderValue);
            itemCount = view.findViewById(R.id.itemCount);
            date = view.findViewById(R.id.date);
            time = view.findViewById(R.id.time);
            status = view.findViewById(R.id.status);
            this.view = view;
        }
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public OrdersAdapter(List<Order> orders , FragmentActivity activity) {
        this.orders = orders;
        this.activity = activity;
    }

    @Override
    public OrdersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_order, parent, false);
        return new OrdersAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrdersAdapter.MyViewHolder holder, int position) {
        final Order order = orders.get(position);
        holder.number.setText(String.valueOf(order.getNumber()));
        holder.value.setText(String.valueOf(order.getValue()));
        holder.itemCount.setText(String.valueOf(order.getItemCount()));
        holder.date.setText(order.getDate());
        holder.time.setText(order.getTime());
        holder.status.setText(order.getStatus());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment newFragment = new OrderDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("orderId" , order.getNumber());
                newFragment.setArguments(bundle);
                transaction.replace(R.id.fragment, newFragment);
//                    transaction.addToBackStack("home");
                transaction.commit();

            }
        });

        if (position == orders.size()-1){
            loadMoreListener.loadMore();
        }

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}