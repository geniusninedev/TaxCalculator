package com.geniusnine.android.taxcalculator.AdvanceIncomeTaxCalculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
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
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdvanceIncomeTaxCalculator extends AppCompatActivity {

    private TextView first,second,third,fourth,fivth,firstI,secondI,thirdI,fourthI,fivthI;
    private Spinner genderspinner;
    private AdvancedTax income;
    private EditText incometaxsalary,housingloan,selfoccupied,letablevalue,municipaltaxes,unrealizedrent,netincome,standarddeduction,letout, interestonhousing1,
            shortterm1,shortterm2,lonterm1,longterm2,totalcapitalgain,interest,commision,lotery, totalothersources,reliefedittext,surchargeedittext,
            educationedittext, higherandseceducationcessedittext, totalreliefedittext,totalnet,totalhousedittextid;
    double incomeTaxRelief,surcharge,educationCess,higherAndSecondaryEduCess;
    PieChart mChart;
    private Typeface mTfRegular;
    private Typeface mTfLight;
    protected double[] mParties;
    double incomerelief ;
    double surchargeValue;
    double educationalcess ;
    double highereducationalcess ;
    DecimalFormat f = new DecimalFormat("##.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_income_tax_calculator);
        MobileAds.initialize(AdvanceIncomeTaxCalculator.this, getString(R.string.ads_app_id));
        AdView mAdView = (AdView) findViewById(R.id.adViewAdvanceTax);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdView mAdView1 = (AdView) findViewById(R.id.adViewAdvTax);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest1);

        AdView mAdView2 = (AdView) findViewById(R.id.adViewAdvanceMain);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);

        DecimalFormat f = new DecimalFormat("##.00");
        Configuration config = getResources().getConfiguration();
        genderspinner = (Spinner) findViewById(R.id.genderspinnerid);
        incometaxsalary = (EditText) findViewById(R.id.incometaxedittextid);
        housingloan = (EditText) findViewById(R.id.intersetonhousingloanedittextid);
        housingloan.setText("0");
        selfoccupied = (EditText) findViewById(R.id.interestonselfoccupiededittext);
        selfoccupied.setEnabled(false);
        Button buttonCalculte = (Button) findViewById(R.id.buttoncalculate);
        letablevalue = (EditText) findViewById(R.id.annualliablevalueedittextid);
        letablevalue.setText("0");
        municipaltaxes = (EditText) findViewById(R.id.muncipaltaxesedittextid);
        municipaltaxes.setText("0");
        unrealizedrent = (EditText) findViewById(R.id.unrealizedrentedittextid);
        unrealizedrent.setText("0");
        netincome = (EditText) findViewById(R.id.netincomevalueedittextid);
        netincome.setEnabled(false);
        standarddeduction = (EditText) findViewById(R.id.standarddeductionedittextid);
        standarddeduction.setEnabled(false);
        interestonhousing1 = (EditText) findViewById(R.id.interestonhousingedittextid);
        interestonhousing1.setText("0");
        totalhousedittextid = (EditText) findViewById(R.id.totalhousetextid);
        totalhousedittextid.setEnabled(false);
        shortterm1 = (EditText) findViewById(R.id.shortterm1edittextid);
        shortterm1.setText("0");
        shortterm2 = (EditText) findViewById(R.id.shortterm2edittextid);
        shortterm2.setText("0");
        lonterm1 = (EditText) findViewById(R.id.longterm1edittextid123);
        lonterm1.setText("0");
        longterm2 = (EditText) findViewById(R.id.longterm2edittextid);
        longterm2.setText("0");
        totalcapitalgain = (EditText) findViewById(R.id.totalcapitalgainedittextid);
        totalcapitalgain.setEnabled(false);
        interest = (EditText) findViewById(R.id.interestedittextid);
        interest.setText("0");
        commision = (EditText) findViewById(R.id.Commissionedittextid);
        commision.setText("0");
        lotery = (EditText) findViewById(R.id.Lotteryedittextid);
        lotery.setText("0");
        totalothersources = (EditText) findViewById(R.id.totalotheredittextid);
        totalothersources.setEnabled(false);
        reliefedittext = (EditText) findViewById(R.id.incometaxreliefedittextid);
        reliefedittext.setEnabled(false);
        surchargeedittext = (EditText) findViewById(R.id.surchargeedittextid123);
        surchargeedittext.setEnabled(false);
        educationedittext = (EditText) findViewById(R.id.educationcessedittextid);
        educationedittext.setEnabled(false);
        higherandseceducationcessedittext = (EditText) findViewById(R.id.higherandseceducessedittextid);
        higherandseceducationcessedittext.setEnabled(false);
        totalreliefedittext = (EditText) findViewById(R.id.totalreliefedittextid);
        totalreliefedittext.setEnabled(false);
        totalnet = (EditText) findViewById(R.id.totalnettaxedittextid);
        totalnet.setEnabled(false);
        letout = (EditText) findViewById(R.id.interestfromletoutedittextid);
        letout.setText("0");
        first = (TextView) findViewById(R.id.firstedittextid);
        second = (TextView) findViewById(R.id.secondedittextid);
        third = (TextView) findViewById(R.id.thirdedittextid);
        fourth = (TextView) findViewById(R.id.fourthedittextid);
        fivth = (TextView) findViewById(R.id.fivthedittext);
        firstI = (TextView) findViewById(R.id.firstIedittext);
        secondI = (TextView) findViewById(R.id.secondIedittext);
        thirdI = (TextView) findViewById(R.id.thirdIedittext);
        fourthI = (TextView) findViewById(R.id.fourthiedittextid);
        fivthI = (TextView) findViewById(R.id.fivthiedittext);
        Button buttonhelp = (Button) findViewById(R.id.advancetaxhelp);
        Button btnClear = (Button) findViewById(R.id.advancetaxclear);
        ArrayList gender = new ArrayList();
        gender.add("Citizen");
        gender.add("seniorCitizen");
        gender.add("SeniorSuperCitizen");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AdvanceIncomeTaxCalculator.this, android.R.layout.simple_spinner_item, gender);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderspinner.setAdapter(dataAdapter);


        buttonCalculte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (incometaxsalary.getText().toString().equals("")) {
                    incometaxsalary.setError("Enter the Salary");
                } else if (letablevalue.getText().toString().equals("")) {
                } else if (municipaltaxes.getText().toString().equals("")) {

                } else if (unrealizedrent.getText().toString().equals("")) {

                } else if (interestonhousing1.getText().toString().equals("")) {

                } else if (shortterm1.getText().toString().equals("")) {

                } else if (shortterm2.getText().toString().equals("")) {

                } else if (lonterm1.getText().toString().equals("")) {

                } else if (longterm2.getText().toString().equals("")) {

                } else if (interest.getText().toString().equals("")) {

                } else if (commision.getText().toString().equals("")) {

                } else if (lotery.getText().toString().equals("")) {

                } else if (housingloan.getText().toString().equals("")) {

                } else {
                    DecimalFormat f = new DecimalFormat("##.00");
                    Integer salaryincome = Integer.parseInt(incometaxsalary.getText().toString());
                    Integer house = Integer.parseInt(housingloan.getText().toString());
                    Integer letable = Integer.parseInt(letablevalue.getText().toString());
                    Integer municipal = Integer.parseInt(municipaltaxes.getText().toString());
                    Integer unrealized = Integer.parseInt(unrealizedrent.getText().toString());
                    Integer intersthousing = Integer.parseInt(interestonhousing1.getText().toString());
                    Integer short1 = Integer.parseInt(shortterm1.getText().toString());
                    Integer short2 = Integer.parseInt(shortterm2.getText().toString());
                    Integer long1 = Integer.parseInt(lonterm1.getText().toString());
                    Integer long2 = Integer.parseInt(longterm2.getText().toString());
                    Integer interestt = Integer.parseInt(interest.getText().toString());
                    Integer commisionn = Integer.parseInt(commision.getText().toString());
                    Integer loteryy = Integer.parseInt(lotery.getText().toString());
                    int capitalgainvaluetotal = (short1 + short2 + long1 + long2);
                    totalcapitalgain.setText(String.valueOf(capitalgainvaluetotal));
                    selfoccupied.setText(String.valueOf(-(house)));
                    int NAV = (letable - (municipal + unrealized));
                    netincome.setText(String.valueOf(NAV));
                    standarddeduction.setText(String.valueOf(NAV * 30 / 100));
                    int valuetotalhouse = ((-house) + (NAV - ((NAV * 30 / 100) + intersthousing)));
                    int incomeother = interestt + commisionn + loteryy;
                    totalhousedittextid.setText(String.valueOf(valuetotalhouse));
                    totalothersources.setText(String.valueOf(interestt + commisionn + loteryy));
                    totalnet.setText(String.valueOf(salaryincome + valuetotalhouse + capitalgainvaluetotal + incomeother));
                    double nettax = (salaryincome + valuetotalhouse + capitalgainvaluetotal + incomeother);
                    String spinnerGender = genderspinner.getSelectedItem().toString().trim();
                    income = new AdvancedTax(nettax, spinnerGender);
                    double incomerelief = income.calculateIncomeTaxAfterRelief();
                    double surchargeValue = income.calculateSurcharge();
                    double educationalcess = income.calculateIncomeTaxAfterRelief() * 0.02;
                    double highereducationalcess = income.calculateIncomeTaxAfterRelief() * 0.01;
                    reliefedittext.setText(String.valueOf((f.format(incomerelief))));
                    educationedittext.setText(String.valueOf((f.format(educationalcess))));
                    surchargeedittext.setText(String.valueOf((f.format(surchargeValue))));
                    higherandseceducationcessedittext.setText(String.valueOf((f.format(highereducationalcess))));
                    totalreliefedittext.setText(String.valueOf((f.format(incomerelief + educationalcess + highereducationalcess + surchargeValue))));
                    double i = income.uptoJune();
                    first.setText(String.valueOf(f.format(i)));
                    second.setText(String.valueOf(f.format(income.uptoSep())));
                    third.setText(String.valueOf(f.format(income.uptoDec())));
                    fourth.setText(String.valueOf(f.format(income.uptoMarch())));
                    fivth.setText(String.valueOf(f.format(income.uptoMarch())));

                    firstI.setText(String.valueOf(f.format(i)));
                    secondI.setText(String.valueOf(f.format(income.uptoSep() - (income.uptoJune()))));
                    thirdI.setText(String.valueOf(f.format(income.uptoDec() - (income.uptoSep()))));
                    fourthI.setText(String.valueOf(f.format(income.uptoMarch() - (income.uptoDec()))));
                    fivthI.setText(String.valueOf(f.format(income.uptoMarch() - (income.uptoMarch()))));
                    dispalyPieChart(incomerelief, surchargeValue, educationalcess, highereducationalcess);
                    setData(2, 20);
                }
            }
        });
        buttonhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent helpref = new Intent(AdvanceIncomeTaxCalculator.this, AdvancedTaxHelp.class);
                startActivity(helpref);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incometaxsalary.setText("");
                housingloan.setText("");
                selfoccupied.setText("");
                letablevalue.setText("");
                municipaltaxes.setText("");
                unrealizedrent.setText("");
                netincome.setText("");
                standarddeduction.setText("");
                letout.setText("");
                interestonhousing1.setText("");
                shortterm1.setText("");
                shortterm2.setText("");
                lonterm1.setText("");
                longterm2.setText("");
                totalcapitalgain.setText("");
                interest.setText("");
                commision.setText("");
                lotery.setText("");
                totalothersources.setText("");
                reliefedittext.setText("");
                surchargeedittext.setText("");
                educationedittext.setText("");
                higherandseceducationcessedittext.setText("");
                totalreliefedittext.setText("");
                totalnet.setText("");
                totalhousedittextid.setText("");
            }
        });
    }


    private void dispalyPieChart(double incomeTaxRelief, double surcharge,double educationCess,double higherAndSecondaryEduCess) {
        this.incomeTaxRelief=incomeTaxRelief;
        this.surcharge=surcharge;
        this.educationCess=educationCess;
        this.higherAndSecondaryEduCess=higherAndSecondaryEduCess;
        mParties = new double[]{incomerelief, surchargeValue,educationalcess,highereducationalcess};
        mChart = (PieChart)findViewById(R.id.advancepiechart);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setCenterTextTypeface(mTfLight);
        mChart.setCenterText(generateCenterSpannableText());
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(55f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);
        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setTextSize(8f);
        mChart.setEntryLabelColor(Color.BLACK);
        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(30f);
        mChart.setDrawSliceText(false);

    }


    private void setData(int count, double range) {
        double incomerelief = income.calculateIncomeTaxAfterRelief();
        double surchargeValue = income.calculateSurcharge();
        double educationalcess = income.calculateIncomeTaxAfterRelief() * 0.02;
        double highereducationalcess = income.calculateIncomeTaxAfterRelief() * 0.01;

        double mult = range;
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry((float) incomeTaxRelief, "IncomeTaxRelief-" +f.format( incomerelief)));
        entries.add(new PieEntry((float) surcharge, "SurchargeValue-" + (f.format(surchargeValue))));
        entries.add(new PieEntry((float) educationCess, "EducationalCess-" + (f.format(educationalcess))));
        entries.add(new PieEntry((float) higherAndSecondaryEduCess, "Higher&Secondary-" + (f.format(highereducationalcess))));
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);
    }
    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Total Amount\n Rs." +f.format((incomeTaxRelief+surcharge+educationCess+higherAndSecondaryEduCess)));
        return s;
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

