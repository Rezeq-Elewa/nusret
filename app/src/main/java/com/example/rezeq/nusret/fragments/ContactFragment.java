package com.example.rezeq.nusret.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
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
import android.widget.Toast;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.activities.LoginActivity;
import com.example.rezeq.nusret.api.Api;
import com.example.rezeq.nusret.api.ApiCallback;
import com.example.rezeq.nusret.api.responses.AboutAppResponse;
import com.example.rezeq.nusret.api.responses.ContactDetailsResponse;
import com.example.rezeq.nusret.api.responses.HowItWorkResponse;
import com.example.rezeq.nusret.api.responses.TermsResponse;
import com.example.rezeq.nusret.utility.BackPressListener;
import com.example.rezeq.nusret.utility.BackPressListenerActivity;
import com.example.rezeq.nusret.utility.Util;
import com.example.rezeq.nusret.views.CustomTextView;

import java.util.Locale;

public class ContactFragment extends Fragment {

    ConstraintLayout contact, talk, follow, rate, instruction, about, terms;
    Toolbar toolbar;
    AppCompatActivity activity;
    Api api;

    public ContactFragment() {
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
        if( ! util.isLoggedIn()){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        api = new Api(getActivity());

        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        contact = view.findViewById(R.id.contact_link);
        talk = view.findViewById(R.id.talk_link);
        follow = view.findViewById(R.id.follow_link);
        rate = view.findViewById(R.id.rate_link);
        instruction = view.findViewById(R.id.instruction_link);
        about = view.findViewById(R.id.about_link);
        terms = view.findViewById(R.id.terms_link);
        activity = ((AppCompatActivity) getActivity());

        api.getContactDetails(new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                final ContactDetailsResponse contactDetailsResponse = (ContactDetailsResponse) response;
                ((CustomTextView)contact.findViewById(R.id.tv_email)).setText(contactDetailsResponse.getResult().getEmail());
                contact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto",contactDetailsResponse.getResult().getEmail(), null));
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    }
                });

                ((CustomTextView)talk.findViewById(R.id.tv_phone)).setText(contactDetailsResponse.getResult().getMobile());
                talk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contactDetailsResponse.getResult().getMobile(), null));
                        startActivity(intent);
                    }
                });

                follow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        Fragment newFragment = new MediaFragment();
                        Bundle args = new Bundle();
                        args.putString("facebook",contactDetailsResponse.getResult().getFacebook());
                        args.putString("twitter",contactDetailsResponse.getResult().getTwitter());
                        args.putString("instagram",contactDetailsResponse.getResult().getInstagram());
                        args.putString("snapchat",contactDetailsResponse.getResult().getSnapchat());
                        args.putString("title",getString(R.string.follow_us));
                        newFragment.setArguments(args);
                        transaction.replace(R.id.fragment, newFragment);
                        transaction.commit();
                    }
                });
            }

            @Override
            public void onFailure(String errorMsg) {
                Toast.makeText(getContext(),errorMsg,Toast.LENGTH_LONG).show();
            }
        });

        api.getHowItWork(new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                final HowItWorkResponse howItWorkResponse = (HowItWorkResponse) response;
                instruction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        Fragment newFragment = new WebFragment();
                        Bundle args = new Bundle();
                        args.putString("content",howItWorkResponse.getResult().getHow_it_work());
                        args.putString("title",getString(R.string.app_instruction));
                        newFragment.setArguments(args);
                        transaction.replace(R.id.fragment, newFragment);
                        transaction.commit();
                    }
                });
            }

            @Override
            public void onFailure(String errorMsg) {
                Toast.makeText(getContext(),errorMsg,Toast.LENGTH_LONG).show();
            }
        });

        api.getAboutApp(new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                final AboutAppResponse aboutAppResponse = (AboutAppResponse) response;
                about.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        Fragment newFragment = new WebFragment();
                        Bundle args = new Bundle();
                        args.putString("content",aboutAppResponse.getResult().getAbout_app());
                        args.putString("title",getString(R.string.about_app));
                        newFragment.setArguments(args);
                        transaction.replace(R.id.fragment, newFragment);
                        transaction.commit();
                    }
                });
            }

            @Override
            public void onFailure(String errorMsg) {
                Toast.makeText(getContext(),errorMsg,Toast.LENGTH_LONG).show();
            }
        });

        api.getTerms(new ApiCallback() {
            @Override
            public void onSuccess(Object response) {
                final TermsResponse termsResponse = (TermsResponse) response;
                terms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        Fragment newFragment = new WebFragment();
                        Bundle args = new Bundle();
                        args.putString("content",termsResponse.getResult().getTerms());
                        args.putString("title",getString(R.string.terms));
                        newFragment.setArguments(args);
                        transaction.replace(R.id.fragment, newFragment);
                        transaction.commit();
                    }
                });
            }

            @Override
            public void onFailure(String errorMsg) {
                Toast.makeText(getContext(),errorMsg,Toast.LENGTH_LONG).show();
            }


        });


        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent rateIntent = rateIntentForUrl("market://details");
                    startActivity(rateIntent);
                } catch (ActivityNotFoundException e) {
                    Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
                    startActivity(rateIntent);
                }
            }
        });

        editToolbar();
        setLanguage();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        editToolbar();
        setLanguage();
    }

    public void editToolbar() {

        toolbar = activity.findViewById(R.id.toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        CustomTextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.contact_us_title);
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


    private Intent rateIntentForUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getActivity().getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21) {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        } else {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }

    private void setLanguage(){
        String languageToLoad  = "ar"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        activity.getBaseContext().getResources().updateConfiguration(config, activity.getBaseContext().getResources().getDisplayMetrics());
    }
}
