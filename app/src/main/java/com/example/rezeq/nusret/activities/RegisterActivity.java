package com.example.rezeq.nusret.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
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
import com.example.rezeq.nusret.api.responses.RegisterResponse;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomButton;
import com.example.rezeq.nusret.views.CustomEditText;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    Toolbar toolbar;
    CustomEditText nameText, phoneText;
    CustomButton loginButton;
    CustomTextView terms;
    Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        util = new Util(this);
        if (util.hasDeviceKeys()){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorRed));
            }
        }
        setContentView(R.layout.activity_register);
        nameText = findViewById(R.id.nameEditText);
        phoneText = findViewById(R.id.phoneEditText);
        loginButton = findViewById(R.id.loginButton);
        terms = findViewById(R.id.terms);

        final Api api = new Api(getApplicationContext());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                final String phone = phoneText.getText().toString();

                if(name.length() < 1 || phone.length() < 1){
                    Toast.makeText(RegisterActivity.this, R.string.enter_phone_and_name,Toast.LENGTH_LONG).show();
                    return;
                }
                api.register(name, phone, new ApiCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        RegisterResponse registerResponse = (RegisterResponse) response;
                        if (registerResponse.isSuccess()){
                            Intent intent = new Intent(getApplicationContext(), VerificationActivity.class);
                            intent.putExtra("phone" , phone);
                            intent.putExtra("new",true);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this,registerResponse.getError(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        Toast.makeText(RegisterActivity.this, msg,Toast.LENGTH_LONG).show();
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
        toolbarTitle.setText(R.string.new_user);
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
        Intent intent = new Intent(RegisterActivity.this,StartActivity.class);
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
