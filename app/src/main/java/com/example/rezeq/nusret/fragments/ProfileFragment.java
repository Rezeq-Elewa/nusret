package com.example.rezeq.nusret.fragments;

import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.views.CustomButton;
import com.example.rezeq.nusret.views.CustomEditText;
import com.example.rezeq.nusret.views.CustomTextView;

public class ProfileFragment extends Fragment {

    CustomEditText nameText, phoneText, emailText;
    CustomButton saveButton;
    Toolbar toolbar;
    AppCompatActivity activity;

    public ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        nameText = view.findViewById(R.id.name);
        phoneText = view.findViewById(R.id.phone);
        emailText = view.findViewById(R.id.email);
        saveButton = view.findViewById(R.id.save);
        activity = ((AppCompatActivity) getActivity());

        //TODO set initial text
        nameText.setText("رزق عليوة");
        phoneText.setText("0592228898");
        emailText.setText("rezeq.elewa@gmail.com");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Button Clicked",Toast.LENGTH_LONG).show();
            }
        });

        editToolbar();
        return view;
    }

    public void editToolbar() {

        toolbar = activity.findViewById(R.id.toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("حسابي");
        toolbarTitle.setVisibility(View.VISIBLE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.GONE);

        ConstraintLayout cart = activity.findViewById(R.id.cart);
        cart.setVisibility(View.GONE);

    }

}
