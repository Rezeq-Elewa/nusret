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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.LoadMoreListener;
import com.example.rezeq.nusret.api.responses.OrderDetailsResponse;
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

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CustomTextView number, value, itemCount, date, time, status;
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
        holder.number.setText(String.format("# %s", String.valueOf(order.getId())));
        holder.value.setText(String.valueOf(order.getDiscount_total()));
        holder.itemCount.setText(String.valueOf(order.getItems()));
        String[] timeDate = order.getCreated_at().split(" ");
        String dateText = timeDate[0].replaceAll("-","/");
        String timeText = timeDate[1];
        holder.date.setText(dateText);
        holder.time.setText(timeText);
        switch (order.getStatus()){
            case "0":
                holder.status.setText("قيد الانتظار");
                holder.status.setBackgroundResource(R.drawable.discount_background);
                break;
            case "1":
                holder.status.setText("تم التجهيز");
                holder.status.setBackgroundResource(R.drawable.status_opened_background);
                break;
            default:
                holder.status.setText("تم التسليم");
                holder.status.setBackgroundResource(R.drawable.status_closed_background);
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressBar progressBar = activity.findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
                Api api = new Api(activity);
                api.orderDetails(Integer.parseInt(order.getId()), new ApiCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        OrderDetailsResponse orderDetailsResponse = (OrderDetailsResponse) response;
                        if(orderDetailsResponse.isSuccess()){
                            progressBar.setVisibility(View.GONE);
                            FragmentManager fragmentManager = activity.getSupportFragmentManager();
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            Fragment newFragment = new OrderDetailsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("orderDetails" , orderDetailsResponse);
                            newFragment.setArguments(bundle);
                            transaction.replace(R.id.fragment, newFragment);
                            transaction.addToBackStack("orders");
                            transaction.commit();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(activity, msg,Toast.LENGTH_LONG).show();
                    }
                });


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