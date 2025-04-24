package application;
import application.Employee;

public class SalariedEmployee extends Employee{
	private double weeklySalary;
	
	public SalariedEmployee(String firstName, String lastName, String ssn, double weeklySalary) {
		super(firstName, lastName, ssn);
		if(weeklySalary >= 0) {
			this.weeklySalary = weeklySalary;
		}else {
			System.out.println("Invalid value...");
			System.exit(0);			
		}
		
	}
	
	public double getWeeklySalary() {
		return weeklySalary;
	}
	public void setWeeklySalary(double weeklySalary) {
		if(weeklySalary >= 0) {
			this.weeklySalary = weeklySalary;
		}else {
			System.out.println("Invalid value...");
			System.exit(0);			
		}
	}

	@Override
	public double getPaymentAmount() {
		return weeklySalary;
	}

	@Override
	public String toString() {
		return "salaried employee: " + super.toString() + "\nweekly salary: $" + weeklySalary + "\npayment amount: $" + getPaymentAmount(); 
	}
	


}

