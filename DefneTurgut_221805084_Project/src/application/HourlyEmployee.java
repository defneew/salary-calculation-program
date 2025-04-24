package application;
import application.Employee;

public class HourlyEmployee extends Employee {
	private double wage;
	private double hours;

	public HourlyEmployee(String firstName, String lastName, String ssn, double wage, double hours) {
		super(firstName, lastName, ssn);
		if(wage >= 0) {
			this.wage = wage;
		}else {
			System.out.println("Invalid value...");
			System.exit(0);			
		}
		if(hours >= 0 && hours < 168) {
			this.hours = hours;
		}else {
			System.out.println("Invalid value...");
			System.exit(0);			
		}

	}
	
	public double getWage() {
		return wage;
	}
	public void setWage(double wage) {
		if(wage >= 0) {
			this.wage = wage;
		}else {
			System.out.println("Invalid value...");
			System.exit(0);			
		}
	}
	public double getHours() {
		return hours;
	}
	public void setHours(double hours) {
		if(hours >= 0 && hours < 168) {
			this.hours = hours;
		}else {
			System.out.println("Invalid value...");
			System.exit(0);			
		}
	}

	@Override
	public double getPaymentAmount() {
		double amount = 0;
		if(hours <= 40) {
			amount =  wage * hours;
		}
		else if(hours > 40) {
			amount = (40 * wage) + (hours - 40) * wage * 1.5;
		}
		return amount;
	}

	@Override
	public String toString() {
		return "hourly employee: " + super.toString() + "\nhourly wage: $" + wage + " hours worked: " + hours + "\npayment amount: $" + getPaymentAmount();
	}
	
	

}

