package com.geniusnine.android.taxcalculator.IncomeTaxCalculator;

import com.github.mikephil.charting.charts.PieChart;

public class IncomeTaxCalculatorEngine {
	double a,b,c;
	double income;
	private PieChart mChart;
	String citizenStatus;
	final double BASE_VALUE = 250000;
	final double BASE_VALUE_SENIOR = 300000;
	final double BASE_VALUE_SUPER_SENIOR = 500000;
	private IncomeTaxResults incomeTaxResults;
	
	public IncomeTaxCalculatorEngine(double income, String citizenStatus) {
        this.income = income;
        this.citizenStatus = citizenStatus;
    }
	public IncomeTaxResults getIncomeTaxResults(){
		incomeTaxResults = new IncomeTaxResults();
		incomeTaxResults.setIncomeTaxAfterRelief(calculateIncomeTaxAfterRelief());
		incomeTaxResults.setEducationCess((calculateIncomeTaxAfterRelief())*.02);
		incomeTaxResults.setSecondaryAndHigherEducationCess((calculateIncomeTaxAfterRelief())*.01);
		incomeTaxResults.setSurcharge(calculateSurcharge());
		incomeTaxResults.setTotalTax(calculateIncomeTaxAfterRelief()+((calculateIncomeTaxAfterRelief())*.02)+ ((calculateIncomeTaxAfterRelief())*.01)+calculateSurcharge());
		return incomeTaxResults;
	}

public double calculateSurcharge(){
	if(income<=5000000){
		return 0;
	}
		
	else if(income>5000000&&income<=10000000) {
			
			return getSurcharge();
			
		} else if(income>10000000){
			return getsur();
		}else
			
		return calculateIncomeTaxAfterRelief();
	}
	
private double getsur() {
	

	if(income>10000000 && ((income-10000000)*0.7)<(calculateIncomeTaxAfterRelief()*0.15)){
		
		  return (income-10000000)*0.7;
	
	}else {
		
		return (calculateIncomeTaxAfterRelief()*0.15);
	
	}
		
	}

	private double getSurcharge() {
		
		 if(income>5000000 && (income-5000000)*0.7<calculateIncomeTaxAfterRelief()*0.1){
		    	
		 return (income-5000000)*0.7;
		
		}else {
			
			return (calculateIncomeTaxAfterRelief()*0.1);
		
		}  
		
		}
			
	public double  calculateIncomeTaxAfterRelief(){
		
		if(citizenStatus == "Citizen"){
			
			double incomeTaxAfterRelief = calculateIncomeTaxCitizen();
			
			return incomeTaxAfterRelief;
			
		}
		
		else if(citizenStatus == "seniorCitizen"){
			
			double incomeTaxAfterRelief = calculateIncomeTaxSeniorCitizen();
			
			return incomeTaxAfterRelief;

		} 
		
		else{
		
			double incomeTaxAfterRelief = calculateIncomeTaxSuperSeniorCitizen();
		
			return incomeTaxAfterRelief;
		}
		
	}

	public  double calculateIncomeTaxCitizen(){
		
		if (income <= 300000) {
            return 0;

        } else if (income > 300000 && income <= 350000) {
            return ((income - BASE_VALUE) * 0.05) - 2500;

        } else if (income > 350000 && income <= 500000) {
            return ((income - BASE_VALUE) * 0.05);

        } else if (income > 500000 && income <=1000000) {
            return ((income - 500000) * 0.2) + 12500;

        } else
            return ((income - 1000000) * 0.3) + 112500;
				
	}
	public  double calculateIncomeTaxSeniorCitizen(){
		
		if (income <= 350000) {
            return 0;

        } else if (income > 350000 && income <= 500000) {
            return ((income - BASE_VALUE_SENIOR) * 0.05);

        }  else if (income > 500000 && income <=1000000) {
            return ((income - 500000) * 0.2) + 10000;

        } else
            return ((income - 1000000) * 0.3) + 110000;
		
	}
	public  double calculateIncomeTaxSuperSeniorCitizen(){
	
	if (income <= BASE_VALUE_SUPER_SENIOR) {
        return 0;

     } else if (income > BASE_VALUE_SUPER_SENIOR && income <=1000000) {
        return ((income - BASE_VALUE_SUPER_SENIOR) * 0.2) ;

    } else
        return ((income - 1000000) * 0.3) + 100000;
	
	
}
	public class IncomeTaxResults{
		
		private double incomeTaxAfterRelief;
		private double surcharge;
		private double educationCess;
		private double secondaryAndHigherEducationCess;
		private double totalTax;
		
		public double getIncomeTaxAfterRelief() {
	        return incomeTaxAfterRelief;
	    }

	    public void setIncomeTaxAfterRelief(double incomeTaxAfterRelief) {
	        this.incomeTaxAfterRelief = incomeTaxAfterRelief;
	    }

	    public double getSurcharge() {
	        return surcharge;
	    }

	    public void setSurcharge(double surcharge) {
	        this.surcharge = surcharge;
	    }

	    public double getEducationCess() {
	        return educationCess;
	    }

	    public void setEducationCess(double educationCess) {
	        this.educationCess = educationCess;
	    }

	    public double getSecondaryAndHigherEducationCess() {
	        return secondaryAndHigherEducationCess;
	    }

	    public void setSecondaryAndHigherEducationCess(double secondaryAndHigherEducationCess) {
	        this.secondaryAndHigherEducationCess = secondaryAndHigherEducationCess;
	    }

	    public double getTotalTax() {
	        return totalTax;
	    }

	    public void setTotalTax(double totalTax) {
	        this.totalTax = totalTax;
	    }
	}
}

