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
import com.example.rezeq.nusret.fragments.ProductDetailsFragment;
import com.example.rezeq.nusret.models.Product;
import com.example.rezeq.nusret.views.CustomTextView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private List<Product> products;
    private FragmentActivity activity;
    private LoadMoreListener loadMoreListener;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CustomTextView name, price;
        private RoundedImageView image;
        private View view;

        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.productName);
            price = view.findViewById(R.id.productPrice);
            image = view.findViewById(R.id.productImage);
            this.view = view;
        }
    }


    public ProductsAdapter(List<Product> products , FragmentActivity activity) {
        this.products = products;
        this.activity = activity;
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public ProductsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_product, parent, false);
        return new ProductsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductsAdapter.MyViewHolder holder, int position) {
        final Product product = products.get(position);
        holder.name.setText(product.getName());
        holder.price.setText(String.valueOf(product.getPrice()));
        Picasso.with(holder.image.getContext())
                .load(product.getImage()).fit().centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.image);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment newFragment = new ProductDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("productId" , product.getId());
                newFragment.setArguments(bundle);
                transaction.replace(R.id.fragment, newFragment);
//                    transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        if (position == products.size()-1){
            loadMoreListener.loadMore();
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}