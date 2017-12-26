package com.example.rezeq.nusret.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.models.CartItem;
import com.example.rezeq.nusret.views.CustomTextView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.MyViewHolder> {

    private List<CartItem> cartItems;

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image, increase, decrease;
        CustomTextView name, price, discount, amount;
        RoundedImageView delete;

        MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            increase = view.findViewById(R.id.increaseAmount);
            decrease = view.findViewById(R.id.decreaseAmount);
            name = view.findViewById(R.id.name);
            price = view.findViewById(R.id.price);
            discount = view.findViewById(R.id.discountValue);
            amount = view.findViewById(R.id.amount);
            delete = view.findViewById(R.id.delete);
        }
    }


    public CartItemsAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public CartItemsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_cart, parent, false);
        return new CartItemsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CartItemsAdapter.MyViewHolder holder, int position) {
        final CartItem cartItem = cartItems.get(position);
        Picasso.with(holder.image.getContext())
                .load(cartItem.getImage()).fit().centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.image);
        holder.name.setText(cartItem.getName());
        holder.price.setText(String.valueOf(cartItem.getTotalPrice()));
        holder.discount.setText(String.valueOf(cartItem.getDiscount()));
        holder.amount.setText(String.valueOf(cartItem.getAmount()));
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItem.setAmount(cartItem.getAmount() + 1);
                holder.amount.setText(String.valueOf(cartItem.getAmount()));
                cartItem.saveOrUpdate();
            }
        });
        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartItem.getAmount() > 1){
                    cartItem.setAmount(cartItem.getAmount() - 1);
                    holder.amount.setText(String.valueOf(cartItem.getAmount()));
                    cartItem.saveOrUpdate();
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItem.delete();
                cartItems.remove(cartItem);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
