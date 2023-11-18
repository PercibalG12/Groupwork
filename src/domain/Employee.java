/*
//
Date: 18/11/2023
Author: Dongie King
//
This file pertains to Question 4 . The user should maintain the Employee data via a menu with the options:
i. Add: which allows the user to add a new employee record to the system.
ii. Update: which allows the user to update an existing employee record.
iii. View: which allows the user to view a single employee record.
iv. View All in Department: which allows the user to view all employee records
for a specified department.
v. Delete: which allows the user to delete an employee record.


This class also answers questions 8
The analysis of the current system also uncovered details related to the various
records held in several files; all employees must provide their Taxpayer Registration
Number, National Insurance Scheme number and date of birth. The company also
records the date the employee was hired. Each processed payroll record should also
show the current payroll date and a unique system generated cheque number.

//

*/

package domain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Employee {
    public static Scanner scanner = new Scanner(System.in);
    private String employeeID;
    private String firstName;
    private String lastName;
    private String employeeDepartmentCode;
    private String position;
    private String taxRegistrationNumber;
    private String nationalInsuranceScheme;
    private Date dateOfBirth;
    private Date dateOfHire;
    private double hrsWorked;

    //Constructors
    //Default Constructors
    public Employee(){
        this("","","","","","","",new Date(),new Date(),0.0);
    }

    //Primary Constructors
    public Employee(String employeeID, String firstName, String lastName, String employeeDepartmentCode, String position, String taxRegistrationNumber, String nationalInsuranceScheme, Date dateOfBirth, Date dateOfHire, double hrsWorked) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeDepartmentCode = employeeDepartmentCode;
        this.position = position;
        this.taxRegistrationNumber = taxRegistrationNumber;
        this.nationalInsuranceScheme = nationalInsuranceScheme;
        this.dateOfBirth = dateOfBirth;
        this.dateOfHire = dateOfHire;
        this.hrsWorked = hrsWorked;
    }

    //Copy Constructors
    public Employee(Employee obj){
        this(obj.getEmployeeID(),obj.getFirstName(),obj.getLastName(), obj.getEmployeeDepartmentCode(), obj.getPosition(), obj.getTaxRegistrationNumber(), obj.getNationalInsuranceScheme(), obj.getDateOfBirth(),obj.getDateOfHire(), obj.getHrsWorked());
    }

    //Getters
    public String getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmployeeDepartmentCode() {
        return employeeDepartmentCode;
    }

    public String getPosition() {
        return position;
    }

    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    public String getNationalInsuranceScheme() {
        return nationalInsuranceScheme;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public Date getDateOfHire() {

        return dateOfHire;
    }

    public double getHrsWorked() {
        return hrsWorked;
    }

    //Setters
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmployeeDepartmentCode(String employeeDepartmentCode) {
        this.employeeDepartmentCode = employeeDepartmentCode;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
    }

    public void setNationalInsuranceScheme(String nationalInsuranceScheme) {
        this.nationalInsuranceScheme = nationalInsuranceScheme;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    static Date setDateOfBirth(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.parse(dateString);
    }

    public void setDateOfHire(Date dateOfHire) {
        this.dateOfHire = dateOfHire;
    }

    static Date setDateOfHire(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.parse(dateString);
    }

    public void setHrsWorked(double hrsWorked) {
        this.hrsWorked = hrsWorked;
    }

    //Header and Body
    public static void printTableHeader() {
        System.out.printf(
                "+----------------------+----------------------+----------------------+-----------------+-----------------+-----------------+----------------------+----------------------+-----------------+\n" +
                        "| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-15s |\n" +
                        "+----------------------+----------------------+----------------------+-----------------+-----------------+-----------------+----------------------+----------------------+-----------------+\n",
                "Employee ID", "First Name", "Last Name", "Department Code", "Position", "Tax Registration", "NI Scheme",
                "Date of Birth", "Date of Hire", "Hours Worked"
        );
    }

    @Override
    public String toString() {
        // Create a SimpleDateFormat object to format date fields
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Format date fields to strings
        String formattedDateOfBirth = dateFormat.format(dateOfBirth);
        String formattedDateOfHire = dateFormat.format(dateOfHire);
        // Return a formatted string representing the employee details
        return "+----------------------+----------------------+----------------------+-----------------+-----------------+-----------------+----------------------+----------------------+-----------------+--------------------------------------------\n"+
                "| Employee ID          | First Name           | Last Name            | Department Code      | Position            | TRN                 | NIS                  | Date of Birth        | Date of Hire        | Hours Worked (hrs)      |\n" +
                "+----------------------+----------------------+----------------------+-----------------+-----------------+-----------------+----------------------+----------------------+-----------------+-------------------------------------------\n" +
                String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-15.2f |\n",
                        employeeID, firstName, lastName, employeeDepartmentCode, position, taxRegistrationNumber, nationalInsuranceScheme,
                        dateFormat.format(dateOfBirth), dateFormat.format(dateOfHire), hrsWorked) +
                "+----------------------+----------------------+----------------------+-----------------+-----------------+-----------------+----------------------+----------------------+-----------------+";
    }

    //Serialization
    public String serializeToString() {
        // Create a SimpleDateFormat object to format date fields
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Serialize employee details into a tab-delimited string
        return getEmployeeID() + "\t" + getFirstName() + "\t" + getLastName() + "\t" +
                getEmployeeDepartmentCode() + "\t" + getPosition() + "\t" +
                getTaxRegistrationNumber() + "\t" + getNationalInsuranceScheme() + "\t" +
                dateFormat.format(getDateOfBirth()) + "\t" + dateFormat.format(getDateOfHire()) + "\t" +
                getHrsWorked();
    }

    public static Employee deserializeToString(String record) {
        String[] parts = record.split("\t");

        if (parts.length != 10) {
            // Consider logging an error or providing more information about the issue.
            return null; // Invalid data
        }

        try {
            String employeeID = parts[0];
            String firstName = parts[1];
            String lastName = parts[2];
            String employeeDepartmentCode = parts[3];
            String position = parts[4];
            String taxRegistrationNumber = parts[5];
            String nationalInsuranceScheme = parts[6];
            Date dateOfBirth = setDateOfBirth(parts[7]);
            Date dateOfHire = setDateOfHire(parts[8]);
            double hrsWorked = Double.parseDouble(parts[9]);

            return new Employee(employeeID, firstName, lastName, employeeDepartmentCode, position,
                    taxRegistrationNumber, nationalInsuranceScheme, dateOfBirth, dateOfHire, hrsWorked);
        } catch (NumberFormatException | ParseException e) {
            // Handle the exception, log it, or return null, depending on your requirements.
            e.printStackTrace(); // For illustration purposes; consider using a logging framework.
            return null;
        }
    }

    public static void displayEmployeeInfo(Employee department) {
        // Print a header for the department information table
        System.out.println("Department Information:");
        Department.printTableHeader(); //calls the print table header method
        // Print the string representation of the employee
        System.out.println(department.toString());
    }

    //Attribute Validation

    public static boolean isValidEmployeeID(String employeeID) {
        // Define a regular expression pattern for the format "97(3 digits)"
        String regexPattern = "^97\\d{3}$";
        // Check if the provided employeeID matches the pattern
        return Pattern.matches(regexPattern, employeeID);
    }

    public static boolean isValidDepartmentCode(String employeeDepartmentCode){

        //Define a regular expression pattern
        String regexPattern= "\\d+001";

        //Check if the code matches the pattern
        return Pattern.matches(regexPattern,employeeDepartmentCode);
    }

    // Validate TRN format
    public static boolean isValidTRN(String trn) {
        // TRN format: 9 digits or 3 digits - 3 digits - 3 digits
        return trn.matches("\\d{9}|\\d{3}-\\d{3}-\\d{3}");
    }

    // Validate NIS format
    public static boolean isValidNIS(String nis) {
        // NIS format: letter + 6 digits or letter + space + 2 digits + space + 2 digits + space + 2 digits
        return nis.matches("[A-Za-z]\\d{6}|[A-Za-z]\\d{2} \\d{2} \\d{2}");
    }

    private Date parseDate(String dateString) {
        try {
            // Create a SimpleDateFormat object with the specified date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            // Attempt to parse the dateString into a Date object
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            // Handle a ParseException (error during date parsing)
            System.err.println("Error parsing date. Please use the format 'dd/mm/yyyy'.");
            // Return null in case of an error
            return null;
        }
    }


    // Helper method to get a valid date input
    private static Date getDateInput(String prompt) {
        Date date = null;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                String dateStr = scanner.nextLine();

                // Validate the date format
                if (dateStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
                    validInput = true;
                } else {
                    System.out.println("Invalid date format. Please enter a valid date (dd/MM/yyyy).");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a valid date (dd/MM/yyyy).");
            }
        }

        return date;
    }


    private static double getValidNumericInput(String rates) {
        // Initialize the value to 0.0
        double value = 0.0;
        // Flag to control the input validation loop
        boolean validInput = false;
        // Continue looping until valid input is obtained
        while (!validInput) {
            try {
                // Prompt the user with the provided rates string
                System.out.print(rates);
                // Attempt to parse the user input into a double value
                value = Double.parseDouble(scanner.nextLine());
                // If parsing is successful, set validInput to true to exit the loop
                validInput = true;
            } catch (NumberFormatException e) {
                // Handle NumberFormatException (occurs if user enters a non-numeric value)
                System.out.println("Invalid input. Please enter a valid numeric value.");
            }
        }
        // Return the validated numeric value
        return value;
    }

    public static Employee addEmployeeRecord(){
        // Create a new Employee object
        Employee employee = new Employee();
        // Input and validation for employee ID
        boolean validEmployeeIDEntered = false;
        while (!validEmployeeIDEntered) {
            System.out.print("Enter the employee's ID (format: 97 followed by 3 digits): ");
            String employeeID = scanner.nextLine();

            if (isValidEmployeeID(employeeID)) {
                employee.setEmployeeID(employeeID);
                validEmployeeIDEntered = true;
            } else {
                System.out.println("Invalid employeeID format. Please enter a valid employeeID.");
            }
        }
        // Input for first name
        System.out.print("Enter the first name: ");
        String firstName = scanner.nextLine();
        employee.setFirstName(firstName);
        // Input for last name
        System.out.print("Enter the last name: ");
        String lastName = scanner.nextLine();
        employee.setLastName(lastName);
        // Input and validation for employee department code
        boolean validEmployeeDepartmentCode = false;
        while (!validEmployeeDepartmentCode) {
            System.out.print("Enter the employee's department code(digit + 001): ");
            String departmentCode = scanner.nextLine();

            if (FileManager.doesRecordExist("Department Rates.txt", departmentCode))
            {
                employee.setEmployeeDepartmentCode(departmentCode);
                validEmployeeDepartmentCode = true;
            } else {
                System.out.println("Invalid department code or code already exists. Please enter a valid and unique department code.");
            }
            System.out.println("Valid Employee Department Code: " + validEmployeeDepartmentCode);
        }
        // Input for position
        System.out.print("Enter the position: ");
        String position = scanner.nextLine();
        employee.setPosition(position);
        // Input and validation for tax registration number (TRN)
        System.out.print("Enter the tax registration number (TRN (9 digits)): ");
        String trn = scanner.nextLine();
        while (!isValidTRN(trn)) {
            System.out.println("Invalid TRN format. Please enter a valid TRN.");
            System.out.print("Enter the tax registration number (TRN (9 digits)): ");
            trn = scanner.nextLine();
        }
        employee.setTaxRegistrationNumber(trn);
        // Input and validation for national insurance scheme (NIS)
        System.out.print("Enter the national insurance scheme (NIS(Capital letter +6 numbers)): ");
        String nis = scanner.nextLine();
        while (!isValidNIS(nis)) {
            System.out.println("Invalid NIS format. Please enter a valid NIS.");
            System.out.print("Enter the national insurance scheme (NIS(Capital letter +6 numbers): ");
            nis = scanner.nextLine();
        }
        employee.setNationalInsuranceScheme(nis);
        // Input for date of birth
        Date dateOfBirth = getDateInput("Enter the date of birth (dd/MM/yyyy): ");
        employee.setDateOfBirth(dateOfBirth);
        // Input for date of hire
        Date dateOfHire = getDateInput("Enter the date of hire (dd/MM/yyyy): ");
        employee.setDateOfHire(dateOfHire);
        // Input and validation for hours worked
        double hrsWorked = getValidNumericInput("Enter the hours worked: ");
        employee.setHrsWorked(hrsWorked);

        // Serialize the employee data to a string
        String serializedData = employee.serializeToString();
        // Write the serialized data to a file
        FileManager.writeToFile("Employee Payroll.txt",serializedData);
        // Return the created Employee object
        return employee;
    }

    public static Employee updateEmployeeRecord() {
        // Create an Employee object to store the updated information
        Employee updatedEmployee = new Employee();
        // Variable to store the employee ID to be updated
        String updateID = null;
        // Input and validation for employee ID
        boolean validEmployeeIDEntered = false;
        while (!validEmployeeIDEntered) {
            System.out.print("Enter the employee ID: ");
            updateID = scanner.nextLine();

            if (isValidEmployeeID(updateID) && FileManager.doesRecordExist("Employee Payroll.txt", updateID)) {
                // Set the employee ID in the updatedEmployee object
                updatedEmployee.setEmployeeID(updateID);
                validEmployeeIDEntered = true;
            } else if (!isValidEmployeeID(updateID)) {
                System.out.println("Invalid employee ID format. Please enter a valid ID.");
            } else {
                System.out.println("Employee ID doesn't exist. Please enter an existing ID.");
            }
        }
        // Search for the existing record in the file
        String existingRecord = FileManager.searchForRecord("Employee Payroll.txt", updateID);

        if (existingRecord != null) {
            // Deserialize the existing record to populate the updatedEmployee
            updatedEmployee = deserializeToString(existingRecord);
            // Update employee information
            System.out.print("Enter the first name: ");
            String firstName = scanner.nextLine();
            assert updatedEmployee != null;
            updatedEmployee.setFirstName(firstName);

            System.out.print("Enter the last name: ");
            String lastName = scanner.nextLine();
            updatedEmployee.setLastName(lastName);
            // Input and validation for employee department code
            boolean validEmployeeDepartmentCode = false;
            while (!validEmployeeDepartmentCode) {
                System.out.print("Enter the employee's department code: ");
                String departmentCode = scanner.nextLine();

                if (FileManager.doesRecordExist("Department Rates.txt", departmentCode) && isValidDepartmentCode(departmentCode)) {
                    updatedEmployee.setEmployeeDepartmentCode(departmentCode);
                    validEmployeeDepartmentCode = true;
                } else {
                    System.out.println("Invalid department code or code already exists. Please enter a valid and unique department code.");
                }
                System.out.println("Valid Employee Department Code: " + validEmployeeDepartmentCode);
            }
            // Input for position
            System.out.print("Enter the position: ");
            String position = scanner.nextLine();
            updatedEmployee.setPosition(position);
            // Input and validation for tax registration number (TRN)
            System.out.print("Enter the tax registration number (TRN (9 digits)): ");
            String trn = scanner.nextLine();
            while (!isValidTRN(trn)) {
                System.out.println("Invalid TRN format. Please enter a valid TRN.");
                System.out.print("Enter the tax registration number (TRN (9 digits)): ");
                trn = scanner.nextLine();
            }
            updatedEmployee.setTaxRegistrationNumber(trn);
            // Input and validation for national insurance scheme (NIS)
            System.out.print("Enter the national insurance scheme (NIS(Capital letter +6 numbers): ");
            String nis = scanner.nextLine();
            while (!isValidNIS(nis)) {
                System.out.println("Invalid NIS format. Please enter a valid NIS.");
                System.out.print("Enter the national insurance scheme (NIS(Capital letter +6 numbers): ");
                nis = scanner.nextLine();
            }
            updatedEmployee.setNationalInsuranceScheme(nis);
            // Input for date of birth
            Date dateOfBirth = getDateInput("Enter the date of birth (dd/MM/yyyy): ");
            updatedEmployee.setDateOfBirth(dateOfBirth);
            // Input for date of hire
            Date dateOfHire = getDateInput("Enter the date of hire (dd/MM/yyyy): ");
            updatedEmployee.setDateOfHire(dateOfHire);
            // Input and validation for hours worked
            double hrsWorked = getValidNumericInput("Enter the hours worked: ");
            updatedEmployee.setHrsWorked(hrsWorked);

            // Update the record in the file
            FileManager.updateRecord("Employee Payroll.txt", updateID, updatedEmployee.serializeToString());
            System.out.println("Record updated successfully.");
            // Return the updated Employee object
            return updatedEmployee;
        } else {
            System.out.println("Record not found.");
            return null;
        }
    }

    public static void viewEmployeeRecord() {
        // Variable to store the serialized data of the found employee
        String search = null;

        // Input and validation for employee ID
        boolean validEmployeeIDEntered = false;
        while (!validEmployeeIDEntered) {
            System.out.print("Enter the employee's ID (format: 97 followed by 3 digits): ");
            String employeeID = scanner.nextLine();

            if (isValidEmployeeID(employeeID)) {
                // Search for the employee record in the file and get the serialized data
                search = FileManager.searchForRecord("Employee Payroll.txt",employeeID);
                validEmployeeIDEntered = true;
            } else {
                System.out.println("Invalid employeeID format. Please enter a valid employeeID.");
            }
        }
        // Deserialize the serialized data to an Employee object
        assert search != null;
        Employee singleRecord = deserializeToString(search);
        // Display the information of the found employee
        assert singleRecord != null;
        displayEmployeeInfo(singleRecord);

    }

    public static void viewAllEmployeeRecords() {
        printTableHeader();

        // Use FileManager to get all records
        ArrayList<String> records = FileManager.viewAllRecords("Employee Payroll.txt");

        // Check if records is not null
        if (!records.isEmpty()) {
            // Iterate over each record, deserialize, and print
            for (String record : records) {
                Employee employee = deserializeToString(record);
                assert employee != null;
                System.out.println(employee.toString());
            }
        } else {
            System.out.println("No records found.");
        }
    }

    public static void deleteEmployeeRecord(){
        // Create an Employee object to store the employee ID
        Employee deleteEmployeeRecord = new Employee();
        // Input and validation for employee ID
        boolean validEmployeeIDEntered = false;
        while (!validEmployeeIDEntered) {
            System.out.print("Enter the employee's ID (format: 97 followed by 3 digits): ");
            String employeeID = scanner.nextLine();

            if (isValidEmployeeID(employeeID)) {
                // Set the employee ID in the deleteEmployeeRecord object
                deleteEmployeeRecord.setEmployeeID(employeeID);
                validEmployeeIDEntered = true;
            } else {
                System.out.println("Invalid employeeID format. Please enter a valid employeeID.");
            }
        }
        // Use FileManager to delete the employee record
        FileManager.deleteRecord("Employee Payroll.txt",deleteEmployeeRecord.employeeID);

    }
}