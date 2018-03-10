package com.example.rezeq.nusret.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.activities.LoginActivity;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.responses.CreateOrderResponse;
import com.example.rezeq.nusret.api.responses.ListsResponse;
import com.example.rezeq.nusret.api.responses.OrderDetailsResponse;
import com.example.rezeq.nusret.api.responses.UserProfileResponse;
import com.example.rezeq.nusret.models.Address;
import com.example.rezeq.nusret.models.City;
import com.example.rezeq.nusret.models.CreateOrder;
import com.example.rezeq.nusret.models.Cutting;
import com.example.rezeq.nusret.models.Profile;
import com.example.rezeq.nusret.utility.BackPressListener;
import com.example.rezeq.nusret.utility.BackPressListenerActivity;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomButton;
import com.example.rezeq.nusret.views.CustomEditText;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.ArrayList;


public class CheckoutFragment extends Fragment {

    CustomEditText nameText, phoneText, emailText, townText, payText, coupon;
    AppCompatSpinner spCountry, spCity, spCutting;
    CustomButton checkoutButton;
    Toolbar toolbar;
    ProgressBar progressBar;
    AppCompatActivity activity;
    Util util;
    ArrayList<Address> addresses;
    ArrayList<City> cities;
    ArrayList<Cutting> cutting;

    public CheckoutFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        util = new Util(getContext());
        final Api api = new Api(getContext());
        addresses = new ArrayList<>();
        cities = new ArrayList<>();
        cutting = new ArrayList<>();

        if( ! util.isLoggedIn()){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        nameText = view.findViewById(R.id.name);
        phoneText = view.findViewById(R.id.phone);
        emailText = view.findViewById(R.id.email);
        spCountry = view.findViewById(R.id.country);
        spCity = view.findViewById(R.id.city);
        townText = view.findViewById(R.id.town);
        spCutting = view.findViewById(R.id.sp_cutting);
        payText = view.findViewById(R.id.pay);
        checkoutButton = view.findViewById(R.id.checkout);
        coupon = view.findViewById(R.id.coupon);
        progressBar = view.findViewById(R.id.progressBar);
        activity = ((AppCompatActivity) getActivity());

        final ArrayAdapter<Address> adapter = new ArrayAdapter<>(getContext(),R.layout.spinner_item,addresses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCountry.setAdapter(adapter);

        final ArrayAdapter<City> cityArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, cities);
        cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCity.setAdapter(cityArrayAdapter);

        final ArrayAdapter<Cutting> cuttingArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, cutting);
        cuttingArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCutting.setAdapter(cuttingArrayAdapter);

        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cities.clear();
                cities.addAll(addresses.get(i).getCities());
                cityArrayAdapter.notifyDataSetChanged();
                spCity.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                cities.clear();
                cityArrayAdapter.notifyDataSetChanged();
            }
        });

        api.getLists(new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                ListsResponse listsResponse = (ListsResponse) response;
                addresses.clear();
                addresses.addAll(listsResponse.getResult().getAddress());
                adapter.notifyDataSetChanged();

                cutting.clear();
                cutting.addAll(listsResponse.getResult().getCutting());
                cuttingArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });

        api.userProfile(new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                UserProfileResponse userProfileResponse = (UserProfileResponse) response;
                if(userProfileResponse.isSuccess()){
                    Profile profile = userProfileResponse.getResult().getProfile();
                    nameText.setText(profile.getName());
                    phoneText.setText(profile.getMobile());
                    emailText.setText(profile.getEmail());
//                    countryText.setText(profile.getCountry());
//                    cityText.setText(profile.getCity());
                    townText.setText(profile.getRegion());
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkoutButton.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                String name = nameText.getText().toString();
                String email = emailText.getText().toString();
                String country = addresses.get(spCountry.getSelectedItemPosition()).getName();
                String city = cities.get(spCity.getSelectedItemPosition()).getName();
                String town = townText.getText().toString();
                String payWay = payText.getText().toString();
                String receiveWay = cutting.get(spCutting.getSelectedItemPosition()).getName();
                String couponCode = coupon.getText().toString();
                final CreateOrder order = new CreateOrder(name,email,country,city,town,receiveWay,payWay);
                if ( ! order.validate()){
                    Toast.makeText(getContext(), R.string.fill_all_fields,Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    checkoutButton.setVisibility(View.VISIBLE);
                    return;
                }

                if (!couponCode.isEmpty() && !couponCode.equalsIgnoreCase(" ")){
                    api.applyCoupon(couponCode, new ApiCallback() {
                        @Override
                        public void onSuccess(Object response) {
                            api.createOrder(order , new ApiCallback() {
                                @Override
                                public void onSuccess(Object response) {
                                    CreateOrderResponse createOrderResponse = (CreateOrderResponse) response;
                                    if(createOrderResponse.isSuccess()){
                                        api.orderDetails(Integer.parseInt(createOrderResponse.getResult().getOrder_id()), new ApiCallback() {
                                            @Override
                                            public void onSuccess(Object response) {
                                                OrderDetailsResponse orderDetailsResponse = (OrderDetailsResponse) response;
                                                if(orderDetailsResponse.isSuccess()){
                                                    progressBar.setVisibility(View.GONE);
                                                    checkoutButton.setVisibility(View.VISIBLE);
                                                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                                                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                                                    Fragment newFragment = new OrderDetailsFragment();
                                                    Bundle bundle = new Bundle();
                                                    bundle.putParcelable("orderDetails" , orderDetailsResponse);
                                                    newFragment.setArguments(bundle);
                                                    transaction.replace(R.id.fragment, newFragment);
                                                    transaction.commit();
                                                }
                                            }

                                            @Override
                                            public void onFailure(String msg) {
                                                Toast.makeText(activity, msg,Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                                checkoutButton.setVisibility(View.VISIBLE);
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onFailure(String msg) {
                                    Toast.makeText(getContext(), msg,Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                    checkoutButton.setVisibility(View.VISIBLE);
                                }
                            });
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            Toast.makeText(activity, errorMsg,Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            checkoutButton.setVisibility(View.VISIBLE);
                        }
                    });
                } else {
                    api.createOrder(order , new ApiCallback() {
                        @Override
                        public void onSuccess(Object response) {
                            CreateOrderResponse createOrderResponse = (CreateOrderResponse) response;
                            if(createOrderResponse.isSuccess()){
                                api.orderDetails(Integer.parseInt(createOrderResponse.getResult().getOrder_id()), new ApiCallback() {
                                    @Override
                                    public void onSuccess(Object response) {
                                        OrderDetailsResponse orderDetailsResponse = (OrderDetailsResponse) response;
                                        if(orderDetailsResponse.isSuccess()){
                                            progressBar.setVisibility(View.GONE);
                                            checkoutButton.setVisibility(View.VISIBLE);
                                            FragmentManager fragmentManager = activity.getSupportFragmentManager();
                                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                                            Fragment newFragment = new OrderDetailsFragment();
                                            Bundle bundle = new Bundle();
                                            bundle.putParcelable("orderDetails" , orderDetailsResponse);
                                            newFragment.setArguments(bundle);
                                            transaction.replace(R.id.fragment, newFragment);
                                            transaction.commit();
                                        }
                                    }

                                    @Override
                                    public void onFailure(String msg) {
                                        Toast.makeText(activity, msg,Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        checkoutButton.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(String msg) {
                            Toast.makeText(getContext(), msg,Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            checkoutButton.setVisibility(View.VISIBLE);
                        }
                    });
                }

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
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.checkout);
        toolbarTitle.setVisibility(View.VISIBLE);

        ImageView toolbarLogo = toolbar.findViewById(R.id.toolbar_logo);
        toolbarLogo.setVisibility(View.GONE);

        ImageView back = toolbar.findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });

        ConstraintLayout cart = activity.findViewById(R.id.cart);
        cart.setVisibility(View.GONE);

        ((BackPressListenerActivity) activity).setListener(new BackPressListener() {
            @Override
            public void backPressed() {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment newFragment = new CartFragment();
                transaction.replace(R.id.fragment, newFragment);
                transaction.commit();
            }
        });
    }
}
