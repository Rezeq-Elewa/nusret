package com.example.rezeq.nusret.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.utility.BackPressListener;
import com.example.rezeq.nusret.utility.BackPressListenerActivity;
import com.example.rezeq.nusret.views.CustomTextView;


public class MediaFragment extends Fragment {

    String facebookLink, twitterLink, instagramLink, snapchatLink, title;
    ImageView ivFacebook, ivTwitter, ivInstagram, ivSnapchat;
    Toolbar toolbar;
    AppCompatActivity activity;

    public MediaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            facebookLink = getArguments().getString("facebook","");
            twitterLink = getArguments().getString("twitter","");
            instagramLink = getArguments().getString("instagram","");
            snapchatLink = getArguments().getString("snapchat","");
            title = getArguments().getString("title","");
        }else {
            title = "";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_media, container, false);
        ivFacebook = view.findViewById(R.id.iv_facebook);
        ivTwitter = view.findViewById(R.id.iv_twitter);
        ivInstagram = view.findViewById(R.id.iv_instagram);
        ivSnapchat = view.findViewById(R.id.iv_snapchat);
        activity = (AppCompatActivity) getActivity();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(facebookLink != null && !facebookLink.equalsIgnoreCase("")){
            ivFacebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!facebookLink.startsWith("http://") && !facebookLink.startsWith("https://"))
                        facebookLink = "http://" + facebookLink;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookLink));
                    startActivity(browserIntent);
                }
            });
        }

        if(twitterLink != null && !twitterLink.equalsIgnoreCase("")){
            ivTwitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!twitterLink.startsWith("http://") && !twitterLink.startsWith("https://"))
                        twitterLink = "http://" + twitterLink;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterLink));
                    startActivity(browserIntent);
                }
            });
        }

        if(instagramLink != null && !instagramLink.equalsIgnoreCase("")){
            ivInstagram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!instagramLink.startsWith("http://") && !instagramLink.startsWith("https://"))
                        instagramLink = "http://" + instagramLink;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramLink));
                    startActivity(browserIntent);
                }
            });
        }

        if(snapchatLink != null && !snapchatLink.equalsIgnoreCase("")){
            ivSnapchat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!snapchatLink.startsWith("http://") && !snapchatLink.startsWith("https://"))
                        snapchatLink = "http://" + snapchatLink;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(snapchatLink));
                    startActivity(browserIntent);
                }
            });
        }
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
        toolbarTitle.setText(title);
        toolbarTitle.setVisibility(View.VISIBLE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.GONE);

        ImageView back = toolbar.findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new ContactFragment());
            }
        });

        ConstraintLayout cart = activity.findViewById(R.id.cart);
        cart.setVisibility(View.GONE);

        ((BackPressListenerActivity) activity).setListener(new BackPressListener() {
            @Override
            public void backPressed() {
                replaceFragment(new ContactFragment());
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fragment, fragment);
        ft.commit();

    }
}
