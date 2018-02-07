package com.example.rezeq.nusret.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.rezeq.nusret.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webView);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String url = bundle.getString("url",null);
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
        }

    }
}
