package com.geniusnine.android.taxcalculator.VatTaxCalculator;

/**
 * Created by Supriya on 19-04-2017.
 */

public class VatCalculatorEngine {

    double vatexcluded;
    double vatpercent;
    Vatval vatvalue;


    VatCalculatorEngine(double vatexcluded, double vatpercent){
        this.vatexcluded=vatexcluded;
        this.vatpercent=vatpercent;
    }

    public Vatval getVatvalues() {

        vatvalue = new Vatval();

        vatvalue.setVat(vatval());

        vatvalue.setTotalval(totalval());

        return vatvalue;

    }

    public double totalval() {

        double vatAmount=((vatexcluded*vatpercent)/100);

        double totalval1=(vatexcluded+vatAmount);


        return totalval1;
    }

    public double vatval() {

        double vatAmount=((vatexcluded*vatpercent)/100);

        return vatAmount;
    }


    class Vatval{

        private double vat;
        private double totalval;


        public double getTotalval() {
            return totalval;
        }

        public void setTotalval(double totalval) {
            this.totalval = totalval;
        }

        public double getVat() {
            return vat;
        }

        public void setVat(double vat) {
            this.vat = vat;
        }

    }
}
