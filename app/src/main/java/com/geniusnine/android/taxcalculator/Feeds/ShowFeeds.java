package com.geniusnine.android.taxcalculator.Feeds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.geniusnine.android.taxcalculator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


/**
 * Created by Dev on 14-03-2017.
 */

public class ShowFeeds extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_feeds);

        MobileAds.initialize(ShowFeeds.this, getString(R.string.ads_app_id));
        AdView mAdView = (AdView) findViewById(R.id.adViewNews);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        WebView webViewNewsContent = (WebView)findViewById(R.id.webview);
        WebSettings webSettings = webViewNewsContent.getSettings();
        webSettings.setJavaScriptEnabled(false);

        webViewNewsContent.loadUrl(url);
    }
}