package application;
import application.CommissionEmployee;

public class BasePlusCommissionEmployee extends CommissionEmployee {
	private double baseSalary;

	public BasePlusCommissionEmployee(String firstName, String lastName, String ssn, double grossSales,
			double commissionRate,double baseSalary) {
		super(firstName, lastName, ssn, grossSales, commissionRate);
		if(baseSalary >= 0) {
			this.baseSalary = baseSalary;
		}else {
			System.out.println("Invalid value...");
			System.exit(0);			
		}
	}

	public double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		if(baseSalary >= 0) {
			this.baseSalary = baseSalary;
		}else {
			System.out.println("Invalid value...");
			System.exit(0);			
		}
	}

	@Override
	public double getPaymentAmount() {
		return super.getPaymentAmount() + baseSalary;
	}

	@Override
	public String toString() {
		String superToString = super.toString();
	    int paymentAmountIndex = superToString.indexOf("\npayment amount");
	    if (paymentAmountIndex != -1) {
	        superToString = superToString.substring(0, paymentAmountIndex);
	    }

	    superToString += " base salary: $" + baseSalary;
	    return "base-salaried" + superToString;
	}
	
	
	

}

