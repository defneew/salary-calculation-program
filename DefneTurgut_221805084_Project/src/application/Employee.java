package application;
import application.Payable;

public abstract class Employee implements Payable {
	private String firstName;
	private String lastName;
	private String ssn;
	
	public Employee(String firstName, String lastName, String ssn) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	//public abstract void updateEmployee(String firstName, String lastName, double value);
	public String toString() {
		return firstName + " " + lastName + "\nsocial security number: " + ssn;	
	}
	

}


