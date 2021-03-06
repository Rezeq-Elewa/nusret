package com.example.rezeq.nusret.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.activities.LoginActivity;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.responses.EditUserProfileResponse;
import com.example.rezeq.nusret.api.responses.UserProfileResponse;
import com.example.rezeq.nusret.models.Profile;
import com.example.rezeq.nusret.utility.BackPressListener;
import com.example.rezeq.nusret.utility.BackPressListenerActivity;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomButton;
import com.example.rezeq.nusret.views.CustomEditText;
import com.example.rezeq.nusret.views.CustomTextView;

public class ProfileFragment extends Fragment {

    CustomEditText nameText, phoneText, emailText;
    CustomTextView logout;
    CustomButton saveButton;
    Toolbar toolbar;
    AppCompatActivity activity;
    Profile profile;
    ProgressBar progressBar;

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

        final Util util = new Util(getContext());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        nameText = view.findViewById(R.id.name);
        phoneText = view.findViewById(R.id.phone);
        phoneText.setEnabled(false);
        emailText = view.findViewById(R.id.email);
        saveButton = view.findViewById(R.id.save);
        progressBar = view.findViewById(R.id.progressBar);
        logout = view.findViewById(R.id.logoutText);
        activity = ((AppCompatActivity) getActivity());

        final Api api = new Api(getContext());
        api.userProfile(new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                UserProfileResponse userProfileResponse = (UserProfileResponse) response;
                if(userProfileResponse.isSuccess()){
                    progressBar.setVisibility(View.GONE);
                    profile = userProfileResponse.getResult().getProfile();
                    nameText.setText(profile.getName());
                    phoneText.setText(profile.getMobile());
                    emailText.setText(profile.getEmail());
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                profile.setName(nameText.getText().toString());
                profile.setEmail(emailText.getText().toString());
                api.editUserProfile(profile, new ApiCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        progressBar.setVisibility(View.GONE);
                        EditUserProfileResponse editUserProfileResponse = (EditUserProfileResponse) response;
                        if(editUserProfileResponse.isSuccess()){
                            Toast.makeText(getContext(), R.string.profile_updated_successfully, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.logout(new ApiCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        util.logout();
                        Intent intent = new Intent(activity, LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        Toast.makeText(getContext(),errorMsg,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

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
        if (activity.getSupportActionBar() != null){
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.profile_title);
        toolbarTitle.setVisibility(View.VISIBLE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.GONE);

        ImageView back = toolbar.findViewById(R.id.back);
        back.setVisibility(View.GONE);

        ConstraintLayout cart = activity.findViewById(R.id.cart);
        cart.setVisibility(View.GONE);

        ((BackPressListenerActivity) activity).setListener(new BackPressListener() {
            @Override
            public void backPressed() {
                activity.finish();
            }
        });
    }

}
