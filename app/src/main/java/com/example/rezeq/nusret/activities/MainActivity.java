package com.example.rezeq.nusret.activities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.fragments.ContactFragment;
import com.example.rezeq.nusret.fragments.MainFragment;
import com.example.rezeq.nusret.fragments.OrdersFragment;
import com.example.rezeq.nusret.fragments.ProfileFragment;
import com.example.rezeq.nusret.models.Ad;
import com.example.rezeq.nusret.models.Category;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomTextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigation;
    Toolbar toolbar;
    ArrayList<Ad> ads;
    ArrayList<Category> categories;
    Util util;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction;
            Fragment newFragment;

            switch (item.getItemId()) {
                case R.id.contact :
                    newFragment = new ContactFragment();
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragment, newFragment);
                    transaction.commit();
                    break;
                case R.id.home :
                    newFragment = new MainFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("ads" , ads);
                    bundle.putParcelableArrayList("categories" , categories);
                    newFragment.setArguments(bundle);
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragment, newFragment);
                    transaction.commit();
                    break;
                case R.id.cart :
                    newFragment = new OrdersFragment();
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragment, newFragment);
                    transaction.commit();
                    break;
                case R.id.profile :
                    newFragment = new ProfileFragment();
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragment, newFragment);
                    transaction.commit();
                    break;
            }
            return true;
        }
    };

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
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        disableShiftMode(navigation);
        ads = new ArrayList<>();
        categories = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ads = extras.getParcelableArrayList("ads");
            categories = extras.getParcelableArrayList("categories");
        }
        initToolbar();
        findViewById(R.id.home).performClick();
    }

    @SuppressLint("RestrictedApi")
    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    public void initToolbar() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setVisibility(View.GONE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.VISIBLE);

        ConstraintLayout cart = toolbar.findViewById(R.id.cart);
        cart.setVisibility(View.GONE);

        if (util.hasDeviceKeys()){
            toolbar.setPadding(0,util.getStatusBarHeight(),0,0);
        }
    }
}


