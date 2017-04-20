package com.geniusnine.android.taxcalculator.VatTaxCalculator;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.geniusnine.android.taxcalculator.IncomeTaxCalculator.IncomeTaxCalculator;
import com.geniusnine.android.taxcalculator.MainActivity;
import com.geniusnine.android.taxcalculator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;

public class VatTaxCalculator extends AppCompatActivity {

    EditText amount,vat;
    Button Cal,clear;
    TextView vatamount,totalamount;
    VatCalculatorEngine vatCalculator;
    DecimalFormat f = new DecimalFormat("##.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vat_tax_calculator);

        MobileAds.initialize(VatTaxCalculator.this, getString(R.string.ads_app_id));
        AdView mAdView = (AdView) findViewById(R.id.adViewVatCalculator);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        amount=(EditText)findViewById(R.id.amountid);
        vat=(EditText)findViewById(R.id.vatid);
        Cal=(Button)findViewById(R.id.buttoncalculate);
        clear=(Button) findViewById(R.id.buttonclear);
        vatamount=(TextView) findViewById(R.id.vatpayableid);
        totalamount=(TextView)findViewById(R.id.totalmoneyaftervatid);
        Button buttonhelp = (Button) findViewById(R.id.vatbuttonhelp);
        Cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (amount.getText().toString().equals("")) {
                    amount.setError("Enter the Amount");
                } else if (vat.getText().toString().equals("")) {
                    vat.setError("Enter the Vat(%)");
                } else {
                    double amountvalue = Double.parseDouble(amount.getText().toString());
                    double vatvalue = Double.parseDouble(vat.getText().toString());
                    vatCalculator = new VatCalculatorEngine(amountvalue, vatvalue);
                    vatamount.setText(String.valueOf(f.format(vatCalculator.vatval())));
                    totalamount.setText(String.valueOf(f.format(vatCalculator.totalval())));
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                vat.setText("");
                vatamount.setText("");
                totalamount.setText("");
            }
        });
        buttonhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent helpref = new Intent(VatTaxCalculator.this,VatTaxHelp.class);
                startActivity(helpref);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            Intent intent=new Intent(VatTaxCalculator.this,MainActivity.class);
            finish();
            startActivity(intent);


        }


        return super.onOptionsItemSelected(item);
    }

}
