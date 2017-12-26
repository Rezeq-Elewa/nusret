package com.example.rezeq.nusret.fragments;

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
import android.widget.TextView;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.adapters.CategoriesAdapter;
import com.example.rezeq.nusret.models.Category;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class MainFragment extends Fragment {

    BannerSlider slider;
    RecyclerView recyclerView;
    Toolbar toolbar;
    AppCompatActivity activity;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        slider = view.findViewById(R.id.slider);
        recyclerView = view.findViewById(R.id.recycler);
        activity = ((AppCompatActivity) getActivity());

        //TODO read ads from api and update the slider
        List<Banner> banners=new ArrayList<>();
        banners.add(new RemoteBanner("https://about.canva.com/wp-content/uploads/sites/3/2017/02/congratulations_-banner.png"));
        banners.add(new RemoteBanner("https://about.canva.com/wp-content/uploads/sites/3/2015/02/Etsy-Banners.png"));
        banners.add(new RemoteBanner("http://www.bigdaysigns.com/wp-content/uploads/2015/12/21st-birthday-banner-white-18-1.png"));
        slider.setBanners(banners);

        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category(1,"MEAT","http://agrodaily.com/wp-content/uploads/2015/10/meat.jpg"));
        categories.add(new Category(1,"FRUITS","https://wonderopolis.org/wp-content/uploads/2016/12/Plants_Make_Fruits_and_Vegetablesdreamstime_xxl_50188610.jpg"));
        categories.add(new Category(1,"VEGETABLES","https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/12_powerhouse_vegetables_slideshow/intro_cream_of_crop.jpg"));

        CategoriesAdapter mAdapter = new CategoriesAdapter(categories , getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //TODO  read categories from api and update recyclerView

        editToolbar();

        return view;
    }

    public void editToolbar() {

        toolbar = activity.findViewById(R.id.toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setVisibility(View.GONE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.VISIBLE);

        ConstraintLayout cart = activity.findViewById(R.id.cart);

        int itemInCartCount = new Util().itemInCartCount();
        if(itemInCartCount > 0){
            cart.setVisibility(View.VISIBLE);
            TextView itemCount = activity.findViewById(R.id.count);
            itemCount.setText(String.valueOf(itemInCartCount));
        } else {
            cart.setVisibility(View.INVISIBLE);
        }

    }
}
