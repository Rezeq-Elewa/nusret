package com.example.rezeq.nusret.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.Urls;
import com.example.rezeq.nusret.api.responses.CartResponse;
import com.example.rezeq.nusret.models.CartItem;
import com.example.rezeq.nusret.views.CustomTextView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.MyViewHolder> {

    private List<CartItem> cartItems;
    private Api api;
    private Context context;
    private CustomTextView totalPriceText;

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


    public CartItemsAdapter(ArrayList<CartItem> cartItems, Context context, CustomTextView totalPriceText) {
        this.totalPriceText = totalPriceText;
        this.cartItems = cartItems;
        this.context = context;
        api = new Api(context);
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
                .load(Urls.IMAGE_URL + cartItem.getImg()).fit().centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.image);
        holder.name.setText(cartItem.getName());
        holder.price.setText(String.valueOf(cartItem.getTotal()));
        if(cartItem.getCoupon_discount().length() > 0){
            holder.discount.setText(String.valueOf(cartItem.getCoupon_discount()));
        }else{
            holder.discount.setVisibility(View.INVISIBLE);
        }

        holder.amount.setText(String.valueOf(cartItem.getAmount()));
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItem.setAmount(String.valueOf(Integer.parseInt(cartItem.getAmount())+1));
                holder.amount.setText(String.valueOf(cartItem.getAmount()));
                api.setAmount(Integer.parseInt(cartItem.getProduct_id()), Integer.parseInt(cartItem.getAmount()), new ApiCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        updateTotal();
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
                        cartItem.setAmount(String.valueOf(Integer.parseInt(cartItem.getAmount())-1));
                        holder.amount.setText(String.valueOf(cartItem.getAmount()));
                    }
                });
            }
        });
        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt(cartItem.getAmount());
                if(amount > 1){
                    cartItem.setAmount(String.valueOf(Integer.parseInt(cartItem.getAmount())-1));
                    holder.amount.setText(String.valueOf(cartItem.getAmount()));
                    api.setAmount(Integer.parseInt(cartItem.getProduct_id()), Integer.parseInt(cartItem.getAmount()), new ApiCallback() {
                        @Override
                        public void onSuccess(Object response) {
                            updateTotal();
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
                            cartItem.setAmount(String.valueOf(Integer.parseInt(cartItem.getAmount())+1));
                            holder.amount.setText(String.valueOf(cartItem.getAmount()));
                        }
                    });
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.removeFromCart(Integer.parseInt(cartItem.getId()), new ApiCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        CartResponse cartResponse = (CartResponse) response;
                        if (cartResponse.isSuccess()){
                            cartItems.remove(cartItem);
                            notifyDataSetChanged();
                            updateTotal();
                        }
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public void updateTotal(){
        int total = 0 ;
        for (CartItem item : cartItems){
            total += Integer.parseInt(item.getTotal());
        }
        totalPriceText.setText(String.valueOf(total));
    }
}
