package com.example.rezeq.nusret.fragments;

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
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.utility.BackPressListener;
import com.example.rezeq.nusret.utility.BackPressListenerActivity;
import com.example.rezeq.nusret.views.CustomTextView;

public class WebFragment extends Fragment {

    WebView wv;
    String content, title;
    Toolbar toolbar;
    AppCompatActivity activity;

    public WebFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        wv = view.findViewById(R.id.web_view);
        activity = ((AppCompatActivity) getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            content = args.getString("content", "");
            title = args.getString("title", "");
        } else {
            content = "";
            title = "";
        }
        wv.loadData(content, "text/html; charset=utf-8", "utf-8");
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
