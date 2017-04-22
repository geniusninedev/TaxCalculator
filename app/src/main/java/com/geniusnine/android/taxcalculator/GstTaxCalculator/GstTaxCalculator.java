package com.geniusnine.android.taxcalculator.GstTaxCalculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.geniusnine.android.taxcalculator.IncomeTaxCalculator.IncomeTaxCalculator;
import com.geniusnine.android.taxcalculator.MainActivity;
import com.geniusnine.android.taxcalculator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GstTaxCalculator extends AppCompatActivity {

    EditText amount,rate;
    Spinner select;
    Button cal,calclear;
    TextView gst,total;
    GstCalculator gstCalculator;
    DecimalFormat f = new DecimalFormat("##.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst_tax_calculator);
        amount=(EditText)findViewById(R.id.amountid);
        rate=(EditText)findViewById(R.id.rateid);
        Button buttonhelp = (Button) findViewById(R.id.gstbuttonhelp);
        gst=(TextView)findViewById(R.id.gstid);
        total=(TextView)findViewById(R.id.netid);
        cal=(Button)findViewById(R.id.buttoncalculate);
        calclear=(Button)findViewById(R.id.buttonclear);
        select=(Spinner)findViewById(R.id.getselectid);
        ArrayList select1 = new ArrayList();

        select1.add("Add GST");
        select1.add("Remove GST");

        MobileAds.initialize(GstTaxCalculator.this, getString(R.string.ads_app_id));
        AdView mAdView = (AdView) findViewById(R.id.adViewGstCalculator);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(GstTaxCalculator.this, android.R.layout.simple_spinner_item, select1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select.setAdapter(dataAdapter1);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().equals("")) {
                    amount.setError("Enter the Amount");
                } else if (rate.getText().toString().equals("")) {
                    rate.setError("Enter the Rate(%)");
                } else {
                    String selectunit = select.getSelectedItem().toString().trim();
                    double amountValue = Double.parseDouble(amount.getText().toString().trim());
                    double rateValue = Double.parseDouble(rate.getText().toString().trim());
                    gstCalculator = new GstCalculator(amountValue, rateValue, selectunit);
                    gst.setText(String.valueOf(f.format(gstCalculator.calGst1())));
                    total.setText(String.valueOf(f.format(gstCalculator.netprice())));
                }
            }
        });
        calclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                rate.setText("");
                gst.setText("");
                total.setText("");


            }
        });
        buttonhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent helpref = new Intent(GstTaxCalculator.this,Gsttaxhelp.class);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {

            finish();

        }
        return super.onOptionsItemSelected(item);
    }

}
