package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application implements EventHandler<ActionEvent> {
	//Choice Box
	ChoiceBox<String> choibox = new ChoiceBox<>();
	//Text Fields
	TextField firstName = new TextField();
	TextField lastName = new TextField();
	TextField ssn = new TextField();
	TextField searchUpdate = new TextField();
	TextField grossSales = new TextField();
	TextField commissionRate = new TextField();
	TextField baseSalary = new TextField();
	TextField weeklySalary = new TextField();
	TextField wage = new TextField();
	TextField hours = new TextField();
			
	//Buttons
	Button add = new Button("Add");
	Button search = new Button("Search by SSN");
	Button update = new Button("Update by SSN");
	Button clean = new Button("Clean textFields");
			
	//Labels
	Label lbFirstName = new Label("First Name");
	Label lbLastName = new Label("Last Name");
	Label lbSsn = new Label("SSN");
	Label lbSearchUpdate = new Label("Search/Update SSN");
	Label lbGrossSales = new Label("Gross Sales");
	Label lbCommissionRate = new Label("Commission Rate");
	Label lbBaseSalary = new Label("Base Salary");
	Label lbWeeklySalary = new Label("Weekly Salary");
	Label lbWage = new Label("Wage");
	Label lbHours = new Label("Hours");
	Label lbSalary = new Label("");
	
	private Employee[] employee = new Employee[100];
	private int index = 0;
	//alert
	private void showAlert(String title, String headerText, String contentText) {
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle(title);
	    alert.setHeaderText(headerText);
	    alert.setContentText(contentText);
	    alert.showAndWait();
	}
	//clean text fields
	private void cleanTextFields() {
        firstName.clear();
        lastName.clear();
        ssn.clear();
        searchUpdate.clear();
        grossSales.clear();
        commissionRate.clear();
        baseSalary.clear();
        weeklySalary.clear();
        wage.clear();
        hours.clear();
		choibox.setValue("None");
		lbSalary.setText("");
    }
	//disable text fields
	private void disableAllTextFields() {
		firstName.setDisable(true);
		lastName.setDisable(true);
		ssn.setDisable(true);
		searchUpdate.setDisable(false);
		grossSales.setDisable(true);
		commissionRate.setDisable(true);
		baseSalary.setDisable(true);
		weeklySalary.setDisable(true);
		wage.setDisable(true);
		hours.setDisable(true);
	}
	@Override
	public void start(Stage stage) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    if (data[0].equals("Salaried Employee") && data.length >= 5) {
                    	Employee emp = new SalariedEmployee(data[1], data[2], data[3], Double.parseDouble(data[4]));
                        index++;
                    } else if (data[0].equals("Hourly Employee") && (data.length >= 6) ) {
                    	Employee emp = new HourlyEmployee(data[1], data[2], data[3], Double.parseDouble(data[4]), Double.parseDouble(data[5]));
                    	index++;
                    } else if (data[0].equals("Base Plus Commission Employee") && (data.length >= 7)) {
                    	Employee emp = new BasePlusCommissionEmployee(data[1], data[2], data[3], Double.parseDouble(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]));
                    	index++;
                    } else if (data[0].equals("Commission Employee") && (data.length >= 6)) {
                    	Employee emp = new CommissionEmployee(data[1], data[2], data[3], Double.parseDouble(data[4]), Double.parseDouble(data[5]));
                    	index++;
                    }else {
                    	
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		stage.setTitle("EMPLOYEE SALARY CALCULATOR");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(25);
		grid.setHgap(15);
		choibox.getItems().addAll("Salaried Employee", "Hourly Employee", "Commission Employee","Base Plus Commission Employee","None");
		Label choiLabel = new Label("Choose Employee Type");
        GridPane.setConstraints(choiLabel, 2, 0); 
        GridPane.setColumnSpan(choibox, 2); 
        GridPane.setConstraints(choibox, 3, 0); 
        grid.getChildren().addAll(choiLabel, choibox);
        
		GridPane grid2 = new GridPane();
		grid2.setVgap(15);
		grid2.setHgap(15);
		
		GridPane.setConstraints(lbFirstName, 0, 0);
		GridPane.setConstraints(firstName, 1, 0);
		GridPane.setConstraints(lbLastName, 0, 1);
		GridPane.setConstraints(lastName, 1, 1);
		GridPane.setConstraints(lbSsn, 0, 2);
		GridPane.setConstraints(ssn, 1, 2);
		GridPane.setConstraints(lbSearchUpdate, 0, 3);
		GridPane.setConstraints(searchUpdate, 1, 3);
		GridPane.setConstraints(lbSalary, 0, 4);
		GridPane.setConstraints(lbGrossSales, 2, 0);
		GridPane.setConstraints(grossSales, 3, 0);
		GridPane.setConstraints(lbCommissionRate, 2, 1);
		GridPane.setConstraints(commissionRate, 3, 1);
		GridPane.setConstraints(lbBaseSalary, 2, 2);
		GridPane.setConstraints(baseSalary, 3, 2);
		GridPane.setConstraints(lbWeeklySalary, 2, 3);
		GridPane.setConstraints(weeklySalary, 3, 3);
		GridPane.setConstraints(lbWage, 2, 4);
		GridPane.setConstraints(wage, 3, 4);
		GridPane.setConstraints(lbHours, 2, 5);
		GridPane.setConstraints(hours, 3, 5);
		
		grid2.getChildren().addAll(lbFirstName, firstName,lbLastName, lastName,lbSsn,ssn,lbSearchUpdate,searchUpdate,lbSalary,lbGrossSales,grossSales,lbCommissionRate,commissionRate,lbBaseSalary,baseSalary,lbWeeklySalary,weeklySalary,lbWage,wage,lbHours,hours);
		
		GridPane grid3 = new GridPane();
		grid3.setAlignment(Pos.CENTER);
		grid3.setVgap(0);
		grid3.setHgap(25);
		
        GridPane.setConstraints(add, 1, 7);
        GridPane.setConstraints(search, 2, 7);
        GridPane.setConstraints(update, 3, 7);
        GridPane.setConstraints(clean, 4, 7);

        grid3.getChildren().addAll(add, search, update, clean);
        
        GridPane mainGridPane = new GridPane();
        mainGridPane.setPadding(new Insets(50,50,50,50));
        mainGridPane.setHgap(30);
        mainGridPane.setVgap(30);
        
        mainGridPane.setAlignment(Pos.CENTER);
        
        mainGridPane.add(grid, 0, 0);
        mainGridPane.add(grid2, 0, 1);
        mainGridPane.add(grid3, 0, 2);
        
		
		Scene scene = new Scene(mainGridPane, 800,450);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
    	disableAllTextFields();		
        choibox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	disableAllTextFields();
   
				switch (newValue) {
                case "Salaried Employee":
                    firstName.setDisable(false);
                    lastName.setDisable(false);
                    weeklySalary.setDisable(false);
                    break;
                case "Hourly Employee":
                    firstName.setDisable(false);
                    lastName.setDisable(false);
                    wage.setDisable(false);
                    hours.setDisable(false);
                    break; 
                case "Commission Employee":
                    firstName.setDisable(false);
                    lastName.setDisable(false);
                    grossSales.setDisable(false);
                    commissionRate.setDisable(false);
                    break;
                case "Base Plus Commission Employee":
                    firstName.setDisable(false);
                    lastName.setDisable(false);
                    grossSales.setDisable(false);
                    commissionRate.setDisable(false);
                    baseSalary.setDisable(false);
                    break;                	
            }
            }
            });
	
    //add button
    add.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			String selectedEmployeeType = choibox.getValue();
            String inputFirstName = firstName.getText();
            String inputLastName = lastName.getText();
            String inputGrossSales = grossSales.getText();
            String inputCommissionRate = commissionRate.getText();
            String inputBaseSalary = baseSalary.getText();
            String inputWeeklySalary = weeklySalary.getText();
            String inputWage = wage.getText();
            String inputHours = hours.getText();
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt", true))) {
                Employee newEmployee = null;
                switch (selectedEmployeeType) {
                    case "Salaried Employee":
                    	if(Double.parseDouble(inputWeeklySalary) >= 0) {
                    	index++;
                        newEmployee = new SalariedEmployee(inputFirstName, inputLastName, String.valueOf(index), Double.parseDouble(inputWeeklySalary));                            
                        writer.write(selectedEmployeeType + "," + inputFirstName + "," + inputLastName + "," + index + "," + inputWeeklySalary + "," + newEmployee.getPaymentAmount() + "\n");
                        showAlert("Success", "Employee Added", "Employee information has been successfully added.");
                    	}else {
                    		showAlert("Error", "Invalid Value", "The value of the weekly salary is not recognized.");
                    	}break;
                    case "Hourly Employee":
                    	if(Double.parseDouble(inputWage) >= 0 && Double.parseDouble(inputHours) >= 0 && Double.parseDouble(inputHours) < 168 ) {
                    	index++;
                        newEmployee = new HourlyEmployee(inputFirstName, inputLastName, String.valueOf(index), Double.parseDouble(inputWage), Double.parseDouble(inputHours));
                        writer.write(selectedEmployeeType + "," + inputFirstName + "," + inputLastName + "," + index + "," + inputWage + "," + inputHours + "," + newEmployee.getPaymentAmount()+ "\n");
                        showAlert("Success", "Employee Added", "Employee information has been successfully added.");
                    	}else {
                    		showAlert("Error", "Invalid Value", "The value is not recognized.");
                    	}break;
                    case "Commission Employee":
                    	if(Double.parseDouble(inputGrossSales) >= 0 && Double.parseDouble(inputCommissionRate) > 0 && Double.parseDouble(inputCommissionRate) < 1) {
                    	index++;
                        newEmployee = new CommissionEmployee(inputFirstName, inputLastName, String.valueOf(index), Double.parseDouble(inputGrossSales), Double.parseDouble(inputCommissionRate));
                        writer.write(selectedEmployeeType + "," + inputFirstName + "," + inputLastName + "," + index + "," + inputGrossSales + "," + inputCommissionRate + "," + newEmployee.getPaymentAmount() + "\n");
                        showAlert("Success", "Employee Added", "Employee information has been successfully added.");
                    	}else {
                    		showAlert("Error", "Invalid Value", "The value is not recognized.");
                    	}break;
                    case "Base Plus Commission Employee":
                    	if(Double.parseDouble(inputGrossSales) >= 0 && Double.parseDouble(inputCommissionRate) > 0 && Double.parseDouble(inputCommissionRate) < 1 && Double.parseDouble(inputBaseSalary) >= 0) {
                    	index++;
                        newEmployee = new BasePlusCommissionEmployee(inputFirstName, inputLastName, String.valueOf(index), Double.parseDouble(inputGrossSales), Double.parseDouble(inputCommissionRate), Double.parseDouble(inputBaseSalary));
                        writer.write(selectedEmployeeType + "," + inputFirstName + "," + inputLastName + "," + index + "," + inputGrossSales + "," + inputCommissionRate + "," + inputBaseSalary + "," + newEmployee.getPaymentAmount() + "\n");
                        showAlert("Success", "Employee Added", "Employee information has been successfully added.");
                    	}else {
                    		showAlert("Error", "Invalid Value", "The value is not recognized.");
                    	}break;
                    default:
                        return;
                }
                cleanTextFields();
            }
            catch (IOException e) {     
                showAlert("Error", "File Write Error", "An error occurred while writing to the file.");
                e.printStackTrace();
            }
    }
    });
    //search button
	search.setOnAction(new EventHandler<ActionEvent>() {			
		@Override
		public void handle(ActionEvent event) {
			String inputSearchUpdate = searchUpdate.getText();
			boolean IsFound = false;
			try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");
					if(data[3].equals(inputSearchUpdate)) {
						IsFound = true;
						if(data.length >= 5 && data[0].equals("Salaried Employee")) {
							SalariedEmployee salariedEmployee = new SalariedEmployee(data[1], data[2], data[3], Double.parseDouble(data[4]));
							choibox.setValue(data[0]);
							firstName.setDisable(false);
							firstName.setText(data[1]);
							lastName.setDisable(false);
							lastName.setText(data[2]);
							weeklySalary.setDisable(false);
							weeklySalary.setText(data[4]);
							lbSalary.setText("SALARY: $" + salariedEmployee.getPaymentAmount());
						}
						else if(data[0].equals("Hourly Employee")) {
							HourlyEmployee hourlyEmployee = new HourlyEmployee(data[1], data[2], data[3], Double.parseDouble(data[4]), Double.parseDouble(data[5]));
							choibox.setValue(data[0]);
							firstName.setDisable(false);
							firstName.setText(data[1]);
							lastName.setDisable(false);
							lastName.setText(data[2]);
							wage.setDisable(false);
							wage.setText(data[4]);
							hours.setDisable(false);
							hours.setText(data[5]);
							lbSalary.setText("SALARY: $" + hourlyEmployee.getPaymentAmount());
						}
						else if(data[0].equals("Commission Employee")) {
							CommissionEmployee commissionEmployee = new CommissionEmployee(data[1], data[2], data[3], Double.parseDouble(data[4]), Double.parseDouble(data[5]));
							choibox.setValue(data[0]);
							firstName.setDisable(false);
							firstName.setText(data[1]);
							lastName.setDisable(false);
							lastName.setText(data[2]);
							grossSales.setDisable(false);
							grossSales.setText(data[4]);
							commissionRate.setDisable(false);
							commissionRate.setText(data[5]);
							lbSalary.setText("SALARY: $" + commissionEmployee.getPaymentAmount());
						}
						else if(data[0].equals("Base Plus Commission Employee")) {
							BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee(data[1], data[2], data[3], Double.parseDouble(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]));
							choibox.setValue(data[0]);
							firstName.setDisable(false);
							firstName.setText(data[1]);
							lastName.setDisable(false);
							lastName.setText(data[2]);
							grossSales.setDisable(false);
							grossSales.setText(data[4]);
							commissionRate.setDisable(false);
							commissionRate.setText(data[5]);
							baseSalary.setDisable(false);
							baseSalary.setText(data[6]);
							lbSalary.setText("SALARY: $" + basePlusCommissionEmployee.getPaymentAmount());
						}
					}
				}
				if (!IsFound) { 
	                showAlert("ERROR!", "The user could not be found.", "The user registered with SSN could not be found.");
	            }
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	});
    //clean button
    clean.setOnAction(new EventHandler<ActionEvent>() {
    	@Override
    	public void handle(ActionEvent event) {
    		cleanTextFields();
    		showAlert("Success", "Cleaned", "Texts cleaned.");
    	}
    });
    //update button
    update.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
	        String inputSearchUpdate = searchUpdate.getText();
	        String selectedEmployeeType = choibox.getValue();
	        String inputFirstName = firstName.getText();
	        String inputLastName = lastName.getText();
	        String inputGrossSales = grossSales.getText();
	        String inputCommissionRate = commissionRate.getText();
	        String inputBaseSalary = baseSalary.getText();
	        String inputWeeklySalary = weeklySalary.getText();
	        String inputWage = wage.getText();
	        String inputHours = hours.getText();
	        boolean IsFound = false;

	        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
	             BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                if (data.length > 3 && data[3].equals(inputSearchUpdate)) {
	                	IsFound = true;
	                    String updatedLine = "";
	                    switch (selectedEmployeeType) {
	                        case "Salaried Employee": 
                            	SalariedEmployee salariedEmployee = new SalariedEmployee(data[1], data[2], data[3], Double.parseDouble(data[4]));
                                salariedEmployee.setFirstName(firstName.getText().isEmpty() ? data[1] : firstName.getText());
                                salariedEmployee.setLastName(lastName.getText().isEmpty() ? data[2] : lastName.getText());
                                salariedEmployee.setWeeklySalary(weeklySalary.getText().isEmpty() ? Double.parseDouble(data[4]) : Double.parseDouble(weeklySalary.getText()));
                                System.out.println(salariedEmployee.toString());
                                updatedLine += selectedEmployeeType + "," + (inputFirstName.isEmpty() ? data[1] : inputFirstName) + "," + (inputLastName.isEmpty() ? data[2] : inputLastName) + "," + data[3] + "," + (inputWeeklySalary.isEmpty() ? data[4] : inputWeeklySalary + "," + salariedEmployee.getPaymentAmount());
                                lbSalary.setText("SALARY: $" + salariedEmployee.getPaymentAmount());
	                            break;
	                        case "Hourly Employee":
                            	HourlyEmployee hourlyEmployee = new HourlyEmployee(data[1], data[2], data[3], Double.parseDouble(data[4]), Double.parseDouble(data[5]));
                                hourlyEmployee.setFirstName(firstName.getText().isEmpty() ? data[1] : firstName.getText());
                                hourlyEmployee.setLastName(lastName.getText().isEmpty() ? data[2] : lastName.getText());
                                hourlyEmployee.setWage(wage.getText().isEmpty() ? Double.parseDouble(data[4]) : Double.parseDouble(wage.getText()));
                                hourlyEmployee.setHours(hours.getText().isEmpty() ? Double.parseDouble(data[5]) : Double.parseDouble(hours.getText()));
                                System.out.println(hourlyEmployee.toString());
                                updatedLine += selectedEmployeeType + "," + (inputFirstName.isEmpty() ? data[1] : inputFirstName) + "," + (inputLastName.isEmpty() ? data[2] : inputLastName) + "," + data[3] + "," + (inputWage.isEmpty() ? data[4] : inputWage) + "," + (inputHours.isEmpty() ? data[5] : inputHours + "," + hourlyEmployee.getPaymentAmount());                            
                                lbSalary.setText("SALARY: $" + hourlyEmployee.getPaymentAmount());
                                break;
	                        case "Commission Employee":
                            	CommissionEmployee commissionEmployee = new CommissionEmployee(data[1], data[2], data[3],
                            	        Double.parseDouble(data[4]), Double.parseDouble(data[5]));
                            	commissionEmployee.setFirstName(firstName.getText().isEmpty() ? data[1] : firstName.getText());
                            	commissionEmployee.setLastName(lastName.getText().isEmpty() ? data[2] : lastName.getText());
                            	commissionEmployee.setGrossSales(grossSales.getText().isEmpty() ? Double.parseDouble(data[4]) : Double.parseDouble(grossSales.getText()));
                            	commissionEmployee.setCommissionRate(commissionRate.getText().isEmpty() ? Double.parseDouble(data[5]) : Double.parseDouble(commissionRate.getText()));
                            	System.out.println(commissionEmployee.toString());
                            	updatedLine += selectedEmployeeType + "," + (inputFirstName.isEmpty() ? data[1] : inputFirstName) + "," + (inputLastName.isEmpty() ? data[2] : inputLastName) + "," + data[3] + "," + (inputGrossSales.isEmpty() ? data[4] : inputGrossSales) + "," + (inputCommissionRate.isEmpty() ? data[5] : inputCommissionRate + "," + commissionEmployee.getPaymentAmount());
                            	lbSalary.setText("SALARY: $" + commissionEmployee.getPaymentAmount());
                            	break;
	                        case "Base Plus Commission Employee":
                            	BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee(data[1], data[2], data[3],
                            	        Double.parseDouble(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]));
                            	basePlusCommissionEmployee.setFirstName(firstName.getText().isEmpty() ? data[1] : firstName.getText());
                            	basePlusCommissionEmployee.setLastName(lastName.getText().isEmpty() ? data[2] : lastName.getText());
                            	basePlusCommissionEmployee.setGrossSales(grossSales.getText().isEmpty() ? Double.parseDouble(data[4]) : Double.parseDouble(grossSales.getText()));
                            	basePlusCommissionEmployee.setCommissionRate(commissionRate.getText().isEmpty() ? Double.parseDouble(data[5]) : Double.parseDouble(commissionRate.getText()));
                            	basePlusCommissionEmployee.setBaseSalary(baseSalary.getText().isEmpty() ? Double.parseDouble(data[6]) : Double.parseDouble(baseSalary.getText()));
                            	System.out.println(basePlusCommissionEmployee.toString());
                            	updatedLine += selectedEmployeeType + "," + (inputFirstName.isEmpty() ? data[1] : inputFirstName) + "," + (inputLastName.isEmpty() ? data[2] : inputLastName) + "," + data[3] + "," + (inputGrossSales.isEmpty() ? data[4] : inputGrossSales) + "," + (inputCommissionRate.isEmpty() ? data[5] : inputCommissionRate) + "," + (inputBaseSalary.isEmpty() ? data[6] : inputBaseSalary + "," + basePlusCommissionEmployee.getPaymentAmount());
                            	lbSalary.setText("SALARY: $" + basePlusCommissionEmployee.getPaymentAmount());
                            	break;
	                    }
	                    writer.write(updatedLine);
	                    writer.newLine();
	                } else {
	                    writer.write(line);
	                    writer.newLine();
	                }
	            }
	        }catch (IOException e) {
	            e.printStackTrace();
	            showAlert("Error", "Update Error", "An error occurred while updating the employee information.");
	            return;
	        }
            if (!IsFound) {
                showAlert("ERROR!", "The user could not be found.", "The user registered with SSN could not be found.");
            }else {
	        try {
	            Files.move(Paths.get("temp.txt"), Paths.get("input.txt"), StandardCopyOption.REPLACE_EXISTING);
	            showAlert("Success", "Employee Updated", "Employee information has been successfully updated.");
	        } catch (IOException e) {
	            e.printStackTrace();
	            showAlert("Error", "File Rename Error", "An error occurred while renaming the file.");
	        }
            }
		}
    });
	}
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void handle(ActionEvent arg0) {
		
	}
}
