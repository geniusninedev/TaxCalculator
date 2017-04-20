package com.geniusnine.android.taxcalculator.Calculators;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.geniusnine.android.taxcalculator.AdvanceIncomeTaxCalculator.AdvanceIncomeTaxCalculator;
import com.geniusnine.android.taxcalculator.EstateTaxCalculator.EstateTaxCalculator;
import com.geniusnine.android.taxcalculator.GstTaxCalculator.GstTaxCalculator;
import com.geniusnine.android.taxcalculator.IncomeTaxCalculator.IncomeTaxCalculator;
import com.geniusnine.android.taxcalculator.R;
import com.geniusnine.android.taxcalculator.SalesTaxCalculator.SalesTaxCalculator;
import com.geniusnine.android.taxcalculator.VatTaxCalculator.VatTaxCalculator;

import java.util.ArrayList;

/**
 * Created by Supriya on 19-04-2017.
 */

public class CalculatorFragment extends Fragment {
    private CustomAdapter mAdapter;
    private ArrayList<String> listCalculator;
    private ArrayList<Integer> listCount;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    private GridView gridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_calculator_fragment, null);
        // prepared arraylist and passed it to the Adapter class

        prepareList();
        mAdapter = new CustomAdapter(getActivity(),listCalculator, listCount);

        // Set custom adapter to gridview
        gridView = (GridView)v.findViewById(R.id.grid);
        gridView.setAdapter(mAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if(position == 0){
                    startActivity(new Intent(getActivity(), IncomeTaxCalculator.class));
                }
                if(position == 1){
                    startActivity(new Intent(getActivity(), AdvanceIncomeTaxCalculator.class));
                }
                if(position == 2){
                    startActivity(new Intent(getActivity(), SalesTaxCalculator.class));
                }

                if(position == 3){
                    startActivity(new Intent(getActivity(), VatTaxCalculator.class));
                }

                if(position == 4){
                    startActivity(new Intent(getActivity(), GstTaxCalculator.class));
                }
                if(position == 5){
                    startActivity(new Intent(getActivity(), EstateTaxCalculator.class));
                }

            }
        });
        return v;
    }


    public void prepareList()
    {
        listCalculator = new ArrayList<String>();

        listCalculator.add("Income Tax ");
        listCalculator.add("Advance Income Tax");
        listCalculator.add("Sales Tax");
        listCalculator.add("Vat Tax");
        listCalculator.add("GST Tax");
        listCalculator.add("Estate Income Tax ");


        listCount = new ArrayList<Integer>();
        listCount.add(R.drawable.income);
        listCount.add(R.drawable.advanceincometax);
        listCount.add(R.drawable.sales);
        listCount.add(R.drawable.vat2);
        listCount.add(R.drawable.gst1);
        listCount.add(R.drawable.estatetax);
    }

}
