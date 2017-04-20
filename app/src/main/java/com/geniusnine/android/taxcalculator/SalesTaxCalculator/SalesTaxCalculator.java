package com.geniusnine.android.taxcalculator.SalesTaxCalculator;

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

public class SalesTaxCalculator extends AppCompatActivity {

    EditText amount,tax;
    Button btnCalculate,btnClear;
    TextView taxtextview,amounttextview;
    SalesTaxCalculatorEngine salestax;
    DecimalFormat f = new DecimalFormat("##.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_tax_calculator);

        MobileAds.initialize(SalesTaxCalculator.this, getString(R.string.ads_app_id));
        AdView mAdView = (AdView) findViewById(R.id.adViewSalesTax);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        amount=(EditText)findViewById(R.id.amountedittext);
        tax=(EditText)findViewById(R.id.taxpaidedittextid);
        btnCalculate=(Button)findViewById(R.id.buttoncalculate);
        taxtextview=(TextView)findViewById(R.id.taxttext);
        amounttextview=(TextView)findViewById(R.id.amounttext);
        btnClear=(Button)findViewById(R.id.buttonclear);
        Button buttonhelp = (Button)findViewById(R.id.saleshelp);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (amount.getText().toString().equals("")) {
                    amount.setError("Enter the Amount");
                } else if (tax.getText().toString().equals("")) {
                    tax.setError("Enter the Tax(%)");
                } else {
                    double amountval = Double.parseDouble(amount.getText().toString());
                    double taxval = Double.parseDouble(tax.getText().toString());
                    salestax = new SalesTaxCalculatorEngine(amountval, taxval);
                    double taxamount = salestax.TaxAmount();
                    taxtextview.setText(String.valueOf(f.format(taxamount)));
                    amounttextview.setText(String.valueOf(f.format(salestax.TotalAmount())));
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                tax.setText("");
                taxtextview.setText("");
                amounttextview.setText("");
            }
        });
        buttonhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent helpref = new Intent(SalesTaxCalculator.this,SalesTaxHelp.class);
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
            Intent intent=new Intent(SalesTaxCalculator.this,MainActivity.class);
            finish();
            startActivity(intent);


        }


        return super.onOptionsItemSelected(item);
    }

}
