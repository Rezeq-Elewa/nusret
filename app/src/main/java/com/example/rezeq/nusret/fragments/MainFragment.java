package com.example.rezeq.nusret.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.activities.LoginActivity;
import com.example.rezeq.nusret.adapters.CategoriesAdapter;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.Urls;
import com.example.rezeq.nusret.api.responses.GetCartResponse;
import com.example.rezeq.nusret.api.responses.HomePageResponse;
import com.example.rezeq.nusret.models.Ad;
import com.example.rezeq.nusret.models.Category;
import com.example.rezeq.nusret.utility.BackPressListener;
import com.example.rezeq.nusret.utility.BackPressListenerActivity;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.events.OnBannerClickListener;
import ss.com.bannerslider.views.BannerSlider;

public class MainFragment extends Fragment {

    BannerSlider slider;
    RecyclerView recyclerView;
    Toolbar toolbar;
    ProgressBar progressBar;
    AppCompatActivity activity;
    ConstraintLayout cart;
    Util util;
    Api api;

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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        util = new Util(getContext());
        if (!util.isLoggedIn()) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        slider = view.findViewById(R.id.slider);
        recyclerView = view.findViewById(R.id.recycler);
        progressBar = view.findViewById(R.id.progressBar);
        activity = ((AppCompatActivity) getActivity());


        final List<Banner> banners = new ArrayList<>();
        ArrayList<Ad> ads = new ArrayList<>();
        final ArrayList<Category> categories = new ArrayList<>();

        Bundle bundle = getArguments();
        if (bundle != null) {
            ads = bundle.getParcelableArrayList("ads");
            ArrayList<Category> cat = bundle.getParcelableArrayList("categories");
            categories.addAll(cat);
        }
        if (ads != null) {
            for (Ad ad : ads) {
                Banner banner = new RemoteBanner(Urls.IMAGE_URL + ad.getImg());
                banners.add(banner);
            }
        }else{
            ads = new ArrayList<>();
        }
        slider.setBanners(banners);
        final ArrayList<Ad> finalAds = ads;
        slider.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onClick(int position) {
                String url = finalAds.get(position).getLink();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        final CategoriesAdapter mAdapter = new CategoriesAdapter(categories, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        api = new Api(getContext());

        if (categories.isEmpty() || ads.isEmpty()) {

            api.homePage(new ApiCallback() {
                @Override
                public void onSuccess(Object response) {
                    HomePageResponse homePageResponse = (HomePageResponse) response;
                    if (homePageResponse.isSuccess()) {
                        List<Banner> banners = new ArrayList<>();
                        ArrayList<Ad> ads;

                        ads = homePageResponse.getResult().getAds();
                        categories.clear();
                        categories.addAll(homePageResponse.getResult().getCategoies());
                        for (Ad ad : ads) {
                            Banner banner = new RemoteBanner(Urls.IMAGE_URL + ad.getImg());
                            banners.add(banner);
                        }
                        slider.removeAllBanners();
                        slider.setBanners(banners);
                        final ArrayList<Ad> finalAds1 = ads;
                        slider.setOnBannerClickListener(new OnBannerClickListener() {
                            @Override
                            public void onClick(int position) {
                                String url = finalAds1.get(position).getLink();
                                if (!url.startsWith("http://") && !url.startsWith("https://"))
                                    url = "http://" + url;
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(browserIntent);
                            }
                        });
                        mAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(String msg) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
        }
        editToolbar();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        editToolbar();
    }

    public void editToolbar() {

        toolbar = activity.findViewById(R.id.toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setVisibility(View.GONE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.VISIBLE);

        ImageView back = toolbar.findViewById(R.id.back);
        back.setVisibility(View.GONE);

        cart = activity.findViewById(R.id.cart);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment newFragment = new CartFragment();
                transaction.replace(R.id.fragment, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        cart.setVisibility(View.INVISIBLE);
        setCart();

        ((BackPressListenerActivity) activity).setListener(new BackPressListener() {
            @Override
            public void backPressed() {
                activity.finish();
            }
        });
    }

    private void setCart(){
        api.getCart(new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                GetCartResponse cartResponse = (GetCartResponse) response;
                if(cartResponse.isSuccess()){
                    if (cartResponse.getResult().getCart().size() > 0) {
                        cart.setVisibility(View.VISIBLE);
                        TextView itemCount = activity.findViewById(R.id.count);
                        itemCount.setText(String.valueOf(cartResponse.getResult().getCart().size()));
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(activity, msg,Toast.LENGTH_LONG).show();
            }
        });
    }
}
