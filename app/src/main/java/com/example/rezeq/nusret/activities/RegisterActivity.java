package com.example.rezeq.nusret.activities;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.responses.RegisterResponse;
import com.example.rezeq.nusret.views.CustomButton;
import com.example.rezeq.nusret.views.CustomEditText;
import com.example.rezeq.nusret.views.CustomTextView;

public class RegisterActivity extends AppCompatActivity {

    Toolbar toolbar;
    CustomEditText nameText, phoneText;
    CustomButton loginButton;
    CustomTextView terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        setContentView(R.layout.activity_register);
        nameText = findViewById(R.id.nameEditText);
        phoneText = findViewById(R.id.phoneEditText);
        loginButton = findViewById(R.id.loginButton);
        terms = findViewById(R.id.terms);

        final Api api = Api.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                final String phone = phoneText.getText().toString();

                api.register(name, phone, new ApiCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        RegisterResponse registerResponse = (RegisterResponse) response;
                        if (registerResponse.isStatus()){
                            Intent intent = new Intent(getApplicationContext(), VerificationActivity.class);
                            intent.putExtra("phone" , phone);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            finish();
                        }else{

                        }
                    }

                    @Override
                    public void onFailure(Object response) {

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
        toolbarTitle.setText("مستخدم جديد");
        toolbarTitle.setVisibility(View.VISIBLE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.GONE);

        ConstraintLayout cart = toolbar.findViewById(R.id.cart);
        cart.setVisibility(View.GONE);

    }
}