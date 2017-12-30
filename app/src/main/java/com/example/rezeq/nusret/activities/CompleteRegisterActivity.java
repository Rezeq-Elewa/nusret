package com.example.rezeq.nusret.activities;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.responses.EditUserProfileResponse;
import com.example.rezeq.nusret.api.responses.UserProfileResponse;
import com.example.rezeq.nusret.models.Profile;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomButton;
import com.example.rezeq.nusret.views.CustomEditText;
import com.example.rezeq.nusret.views.CustomTextView;

public class CompleteRegisterActivity extends AppCompatActivity {

    CustomEditText nameText, emailText;
    CustomButton completeRegistration;
    CustomTextView terms;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Util util = new Util(getApplicationContext());
        if( ! util.isLoggedIn()){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }
        final Api api = new Api(getApplicationContext());
        Profile profile = util.getUserProfile();
        final Profile userProfile = new Profile(profile);


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        setContentView(R.layout.activity_complete_register);
        nameText = findViewById(R.id.nameEditText);
        nameText.setText(userProfile.getName());
        emailText = findViewById(R.id.emailEditText);
        completeRegistration = findViewById(R.id.completeRegistration);
        terms = findViewById(R.id.terms);

        api.userProfile(new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                UserProfileResponse userResponse = (UserProfileResponse) response;
                if(userResponse.isSuccess()){

                    nameText.setText(userResponse.getResult().getProfile().getName());
                    emailText.setText(userResponse.getResult().getProfile().getEmail());
                }
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });

        completeRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String email = emailText.getText().toString();
                userProfile.setName(name);
                userProfile.setEmail(email);

                api.editUserProfile(userProfile, new ApiCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        EditUserProfileResponse editResponse = (EditUserProfileResponse) response;
                        if(editResponse.isSuccess()){
                            util.saveUserProfile(userProfile);
                            Intent intent = new Intent(getApplicationContext(), CongratulationActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        Toast.makeText(CompleteRegisterActivity.this, msg,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        initToolbar();
    }

    public void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.complete_profile);
        toolbarTitle.setVisibility(View.VISIBLE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.GONE);

        ConstraintLayout cart = toolbar.findViewById(R.id.cart);
        cart.setVisibility(View.GONE);
    }
}
