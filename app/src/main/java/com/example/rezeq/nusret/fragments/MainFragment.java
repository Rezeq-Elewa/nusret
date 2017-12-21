package com.example.rezeq.nusret.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rezeq.nusret.R;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class MainFragment extends Fragment {

    BannerSlider bannerSlider;
    RecyclerView recyclerView;

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

        bannerSlider = view.findViewById(R.id.bannerSlider);
        recyclerView = view.findViewById(R.id.recyclerView);

        List<Banner> banners=new ArrayList<>();
        banners.add(new RemoteBanner("Put banner image url here ..."));

        bannerSlider.setBanners(banners);


        return view;
    }

}
