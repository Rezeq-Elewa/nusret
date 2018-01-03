package com.example.rezeq.nusret.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.models.OrderItem;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.List;

/**
 * Created by Rezeq on 1/1/2018.
 * Email : rezeq.elewa@gmail.com
 */

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder> {

    private List<OrderItem> items;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CustomTextView name, amount, price;

        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.itemName);
            amount = view.findViewById(R.id.itemAmount);
            price = view.findViewById(R.id.itemPrice);
        }
    }

    public TableAdapter(List<OrderItem> items) {
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
        holder.price.setText(item.getTotal());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}