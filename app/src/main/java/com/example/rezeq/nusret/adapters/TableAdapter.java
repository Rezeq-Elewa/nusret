package com.example.rezeq.nusret.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.fragments.OrderDetailsFragment;
import com.example.rezeq.nusret.models.OrderItem;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.List;
import java.util.Locale;

/**
 * Created by Rezeq on 1/1/2018.
 * Email : rezeq.elewa@gmail.com
 */

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder> {

    private OrderDetailsFragment fragment;
    private List<OrderItem> items;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CustomTextView name, discount, amount, price;

        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.itemName);
            discount = view.findViewById(R.id.discount);
            amount = view.findViewById(R.id.itemAmount);
            price = view.findViewById(R.id.itemPrice);
        }
    }

    public TableAdapter(OrderDetailsFragment fragment, List<OrderItem> items) {
        this.fragment = fragment;
        this.items = items;
    }

    @Override
    public TableAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_table_row, parent, false);
        return new TableAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TableAdapter.MyViewHolder holder, int position) {
        final OrderItem item = items.get(position);
        holder.name.setText(item.getName());
        holder.amount.setText(item.getAmount());
        if( ! item.getCoupon_discount().isEmpty() && item.getCoupon_discount().contains("%")){
            holder.discount.setText(String.format(Locale.getDefault(),"خصم %s",item.getCoupon_discount()));
            holder.discount.setBackgroundResource(R.drawable.discount_background);
            holder.price.setText(item.getCoupon_total());
        } else {
            holder.discount.setText("");
            holder.price.setText(item.getTotal());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
