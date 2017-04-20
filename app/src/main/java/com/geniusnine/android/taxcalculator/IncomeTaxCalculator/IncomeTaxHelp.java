package com.geniusnine.android.taxcalculator.IncomeTaxCalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.geniusnine.android.taxcalculator.R;

public class IncomeTaxHelp extends AppCompatActivity {
    WebView Introwebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Introwebview = (WebView) findViewById(R.id.help);
        WebSettings IntroWebSettings = Introwebview.getSettings();
        IntroWebSettings.setBuiltInZoomControls(true);
        IntroWebSettings.setJavaScriptEnabled(true);
        Introwebview.setWebViewClient(new IncomeTaxHelp.WebViewClient());
        Introwebview.loadUrl("file:///android_res/raw/incometxhtml.html");
    }
        public class WebViewClient extends android.webkit.WebViewClient {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        }
}