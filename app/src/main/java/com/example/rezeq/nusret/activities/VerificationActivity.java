package com.example.rezeq.nusret.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.responses.LoginResponse;
import com.example.rezeq.nusret.api.responses.RequestLoginCodeResponse;
import com.example.rezeq.nusret.models.Profile;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomButton;
import com.example.rezeq.nusret.views.CustomEditText;
import com.example.rezeq.nusret.views.CustomTextView;
import com.onesignal.OneSignal;

import java.util.Locale;

public class VerificationActivity extends AppCompatActivity {

    Toolbar toolbar;
    CustomEditText verificationText;
    CustomButton verifyButton;
    CustomTextView resendVerification;
    ProgressBar progressBar;
    Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        util = new Util(this);
        String phone = null;
        boolean newUser = false;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            phone = extras.getString("phone", null);
            newUser = extras.getBoolean("new", false);
        }
        if(phone == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }
        if (util.hasDeviceKeys()){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorRed));
            }
        }

        SharedPreferences preferences = this.getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        String userId = preferences.getString("userId","");
        if(userId.equalsIgnoreCase("")){
            userId = OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getUserId();
        }

        setContentView(R.layout.activity_verification);
        verificationText = findViewById(R.id.verificationCode);
        verifyButton = findViewById(R.id.verifyButton);
        resendVerification = findViewById(R.id.resendVerification);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        final Api api = new Api(getApplicationContext());
        final String finalPhone = phone;
        final boolean finalNewUser = newUser;
        final String finalUserId = userId;
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String code = verificationText.getText().toString();
                api.login(finalPhone, code, finalUserId, new ApiCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        progressBar.setVisibility(View.GONE);
                        LoginResponse loginResponse = (LoginResponse) response;
                        if(loginResponse.isSuccess()){
                            Util util = new Util(getApplicationContext());
                            Profile profile = loginResponse.getResult().getUser();
                            profile.setAccessToken(loginResponse.getResult().getToken());
                            util.saveUserProfile(profile);
                            if(finalNewUser){
                                Intent intent = new Intent(getApplicationContext(), CompleteRegisterActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                finish();
                            } else {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("ads",loginResponse.getResult().getAds());
                                intent.putExtra("categories",loginResponse.getResult().getCategoies());
                                startActivity(intent);
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                finish();
                            }
                        }else{
                            Toast.makeText(VerificationActivity.this, loginResponse.getError(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(VerificationActivity.this, msg,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        final String finalPhone1 = phone;
        resendVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.requestLoginCode(finalPhone1, new ApiCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        RequestLoginCodeResponse codeResponse = (RequestLoginCodeResponse) response;
                        if(codeResponse.isSuccess()){
                            Toast.makeText(VerificationActivity.this, R.string.verification_sent_again,Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(VerificationActivity.this, msg,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        setLanguage();
        initToolbar();
    }

    @Override
    public void onResume() {
        super.onResume();
        initToolbar();
    }

    public void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.code_verification);
        toolbarTitle.setVisibility(View.VISIBLE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.GONE);

        ImageView back = toolbar.findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ConstraintLayout cart = toolbar.findViewById(R.id.cart);
        cart.setVisibility(View.GONE);

        if (util.hasDeviceKeys()){
            toolbar.setPadding(0,util.getStatusBarHeight(),0,0);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(VerificationActivity.this,LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    private void setLanguage(){
        String languageToLoad  = "ar"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}
