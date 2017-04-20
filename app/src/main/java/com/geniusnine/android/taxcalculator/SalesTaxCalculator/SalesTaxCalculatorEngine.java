package com.geniusnine.android.taxcalculator.SalesTaxCalculator;

/**
 * Created by Supriya on 19-04-2017.
 */


    public class SalesTaxCalculatorEngine {
        double price;
        double percent;
        Salestax salestaxcal;

        SalesTaxCalculatorEngine(double price, double percent){
            this.price=price;
            this.percent=percent;
        }

        public Salestax getsalesvalues(){
            salestaxcal = new Salestax();
            salestaxcal.settaxamt(TaxAmount());
            salestaxcal.settotalamnt(TotalAmount());
            return salestaxcal;

        }


        public double TaxAmount() {

            double taxAmount=((price*percent)/100);

            return taxAmount;
        }

        public double TotalAmount() {

            double taxAmount=((price*percent)/100);

            double pricetotal=(price+taxAmount);

            return pricetotal;
        }

        class Salestax{

            private double taxamt;
            private double totalamnt;


            public double gettaxamt() {
                return taxamt;
            }

            public void settaxamt(double taxamt) {
                this.taxamt = taxamt;

            }
            public double gettotalamnt() {
                return totalamnt;
            }

            public void settotalamnt(double totalamnt) {
                this.totalamnt = totalamnt;

            }

        }

    }

