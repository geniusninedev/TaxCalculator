package com.geniusnine.android.taxcalculator.SalesTaxCalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.geniusnine.android.taxcalculator.R;

/**
 * Created by Dev on 24-03-2017.
 */

public class SalesTaxHelp extends AppCompatActivity {
    WebView Introwebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Introwebview = (WebView) findViewById(R.id.help);
        WebSettings IntroWebSettings = Introwebview.getSettings();
        IntroWebSettings.setBuiltInZoomControls(true);
        IntroWebSettings.setJavaScriptEnabled(true);
        Introwebview.setWebViewClient(new SalesTaxHelp.WebViewClient());
        Introwebview.loadUrl("file:///android_res/raw/salestaxhtml.html");
    }


    public class WebViewClient extends android.webkit.WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
