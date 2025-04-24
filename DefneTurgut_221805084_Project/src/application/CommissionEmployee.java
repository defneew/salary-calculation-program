package application;
import application.Employee;

public class CommissionEmployee extends Employee{
	private double grossSales;
	private double commissionRate;

	public CommissionEmployee(String firstName, String lastName, String ssn, double grossSales, double commissionRate) {
		super(firstName, lastName, ssn);
		if(grossSales >= 0) {
			this.grossSales = grossSales;
		}else {
			System.out.println("Invalid value...");
			System.exit(0);			
		}
		if(commissionRate > 0 && commissionRate < 1) {
			this.commissionRate = commissionRate;
		}else {
			System.out.println("Invalid value...");
			System.exit(0);			
		}
	}
	
	
	public double getGrossSales() {
		return grossSales;
	}
	public void setGrossSales(double grossSales) {
		if(grossSales >= 0) {
			this.grossSales = grossSales;
		}else {
			System.out.println("Invalid value...");
			System.exit(0);			
		}
	}
	public double getCommissionRate() {
		return commissionRate;
	}
	public void setCommissionRate(double commissionRate) {
		if(commissionRate > 0 && commissionRate < 1) {
			this.commissionRate = commissionRate;
		}else {
			System.out.println("Invalid value...");
			System.exit(0);			
		}
	}

	@Override
	public double getPaymentAmount() {
		return grossSales * commissionRate;
	}
	
	@Override
	public String toString() {
		return "commission employee: " + super.toString() + "\ngross sales: $" + grossSales + " commission rate: " + commissionRate + "\npayment amount: $" + getPaymentAmount();
	}

}

