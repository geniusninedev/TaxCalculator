package com.geniusnine.android.taxcalculator.EstateTaxCalculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.geniusnine.android.taxcalculator.GstTaxCalculator.GstTaxCalculator;
import com.geniusnine.android.taxcalculator.IncomeTaxCalculator.IncomeTaxCalculator;
import com.geniusnine.android.taxcalculator.MainActivity;
import com.geniusnine.android.taxcalculator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;

public class EstateTaxCalculator extends AppCompatActivity {

    EditText residence, stock, savings, vehicles, retiremnt, life, other, debts, funeral, charitable, state;
    TextView total, federal;
    Button cal, calclear;
    EstateCalculator estateCalculator;
    DecimalFormat f = new DecimalFormat("##.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estate_tax_calculator);
        MobileAds.initialize(EstateTaxCalculator.this, getString(R.string.ads_app_id));
        AdView mAdView = (AdView) findViewById(R.id.adViewEstateTax);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        AdView mAdView1 = (AdView) findViewById(R.id.adViewEstateCalculator);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest1);

        residence = (EditText)findViewById(R.id.residenceid);
        residence.setText("0");
        stock = (EditText) findViewById(R.id.stockid);
        stock.setText("0");
        savings = (EditText) findViewById(R.id.savingsid);
        savings.setText("0");
        vehicles = (EditText) findViewById(R.id.vehiclesid);
        vehicles.setText("0");
        retiremnt = (EditText)findViewById(R.id.retirementid);
        retiremnt.setText("0");
        life = (EditText) findViewById(R.id.lifeid);
        life.setText("0");
        other = (EditText) findViewById(R.id.othersid);
        other.setText("0");
        debts = (EditText) findViewById(R.id.debtsid);
        debts.setText("0");
        funeral = (EditText) findViewById(R.id.funeralid);
        funeral.setText("0");
        charitable = (EditText)findViewById(R.id.charitableid);
        charitable.setText("0");
        state = (EditText) findViewById(R.id.stateid);
        state.setText("0");
        cal = (Button) findViewById(R.id.buttoncalculate);
        calclear = (Button) findViewById(R.id.buttonclear);
        total = (TextView) findViewById(R.id.totalid);
        federal = (TextView) findViewById(R.id.federaltvid);
        Button buttonhelp = (Button) findViewById(R.id.estatebuttonhelp);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double residenceValue = Double.parseDouble(residence.getText().toString().trim());
                double stockValue = Double.parseDouble(stock.getText().toString().trim());
                double savingsValue = Double.parseDouble(savings.getText().toString().trim());
                double vehiclesValue = Double.parseDouble(vehicles.getText().toString().trim());
                double retiremntValue = Double.parseDouble(retiremnt.getText().toString().trim());
                double lifeValue = Double.parseDouble(life.getText().toString().trim());
                double otherValue = Double.parseDouble(other.getText().toString().trim());
                double debtsValue = Double.parseDouble(debts.getText().toString().trim());
                double funeralValue = Double.parseDouble(funeral.getText().toString().trim());
                double charitableValue = Double.parseDouble(charitable.getText().toString().trim());
                double stateValue = Double.parseDouble(state.getText().toString().trim());
                estateCalculator = new EstateCalculator(residenceValue, stockValue, savingsValue, vehiclesValue, retiremntValue, lifeValue, otherValue, debtsValue, funeralValue, charitableValue, stateValue);
                total.setText(String.valueOf(f.format(estateCalculator.totaltax())));
                federal.setText(String.valueOf(f.format(estateCalculator.federabletax())));

            }
        });
        calclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                residence.setText("0");
                stock.setText("0");
                savings.setText("0");
                vehicles.setText("0");
                retiremnt.setText("0");
                life.setText("0");
                other.setText("0");
                debts.setText("0");
                funeral.setText("0");
                charitable.setText("0");
                state.setText("0");
                total.setText("0");
                federal.setText("0");
            }
        });
        buttonhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent helpref = new Intent(EstateTaxCalculator.this,EstateTxHelp.class);
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
            Intent intent=new Intent(EstateTaxCalculator.this,MainActivity.class);
            finish();
            startActivity(intent);


        }


        return super.onOptionsItemSelected(item);
    }

}
