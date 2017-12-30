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
import android.widget.ImageView;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.api.Urls;
import com.example.rezeq.nusret.fragments.ProductsFragment;
import com.example.rezeq.nusret.models.Category;
import com.example.rezeq.nusret.views.CustomTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private List<Category> categories;
    private FragmentActivity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CustomTextView title;
        public ImageView image;
        private View view;

        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            image = view.findViewById(R.id.image);
            this.view = view;
        }
    }


    public CategoriesAdapter(List<Category> categories , FragmentActivity activity) {
        this.categories = categories;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_main_category, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Category category = categories.get(position);
        holder.title.setText(category.getName());
        Picasso.with(holder.image.getContext())
                .load(Urls.IMAGE_URL + category.getImg()).fit().centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.image);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment newFragment = new ProductsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId" , Integer.parseInt(category.getId()));
                newFragment.setArguments(bundle);
                transaction.replace(R.id.fragment, newFragment);
                transaction.addToBackStack("home");
                transaction.commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}