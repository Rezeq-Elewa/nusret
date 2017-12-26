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
import com.example.rezeq.nusret.api.responses.LoginResponse;
import com.example.rezeq.nusret.views.CustomButton;
import com.example.rezeq.nusret.views.CustomEditText;
import com.example.rezeq.nusret.views.CustomTextView;

public class VerificationActivity extends AppCompatActivity {

    Toolbar toolbar;
    CustomEditText verificationText;
    CustomButton verifyButton;
    CustomTextView resendVerification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String phone = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            phone = extras.getString("phone");
        }
        if(phone == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        setContentView(R.layout.activity_verification);
        verificationText = findViewById(R.id.verificationCode);
        verifyButton = findViewById(R.id.verifyButton);
        resendVerification = findViewById(R.id.resendVerification);

        final Api api = Api.getInstance();
        final String finalPhone = phone;
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = verificationText.getText().toString();
                api.login(finalPhone, code, new ApiCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        LoginResponse loginResponse = (LoginResponse) response;
                        if(loginResponse.isStatus()){
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            finish();
                        }else{
                            //TODO show error message based on error number
                        }
                    }

                    @Override
                    public void onFailure(Object response) {
                        Toast.makeText(VerificationActivity.this, R.string.error_try_again,Toast.LENGTH_LONG).show();
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
        toolbarTitle.setText(R.string.code_verification);
        toolbarTitle.setVisibility(View.VISIBLE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.GONE);

        ConstraintLayout cart = toolbar.findViewById(R.id.cart);
        cart.setVisibility(View.GONE);

    }
}
