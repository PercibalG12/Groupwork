/*
//
Date: 18/11/2023
Author: Joshuawn Johnson 2205652
//
This file pertains to Question 5 . The user shall process the Employee Payroll via a menu with the options:
i. Process Payroll: Calculates payroll and generates Processed Payroll File.
ii. View Payroll: Allows the user to view a single employee payroll record.
iii. View Department Payroll:  Allows the user to view all employee payroll
records for a specific department.

//

*/


package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
// Payroll class extends Employee and handles payroll processing
public class Payroll extends Employee {
    private double regularPay;
    private double overtimePay;
    private double grossPay;
    private LocalDate dateOfProcessing;
    private String chequeNumber;

    // Constructors

    // Default Constructor
    public Payroll() {
        super();
        this.regularPay = 0.0;
        this.overtimePay = 0.0;
        this.grossPay = 0.0;
        this.dateOfProcessing = LocalDate.now();
        this.chequeNumber = "";
    }

    // Primary Constructor
    public Payroll(Employee employee, double regularPay, double overtimePay,
                   double grossPay, LocalDate dateOfProcessing, String chequeNumber) {
        super(employee);
        this.regularPay = regularPay;
        this.overtimePay = overtimePay;
        this.grossPay = grossPay;
        this.dateOfProcessing = dateOfProcessing;
        this.chequeNumber = chequeNumber;
    }

    // Copy Constructor
    public Payroll(Payroll obj) {
        super(obj);
        this.regularPay = obj.regularPay;
        this.overtimePay = obj.overtimePay;
        this.grossPay = obj.grossPay;
        this.dateOfProcessing = obj.dateOfProcessing;
        this.chequeNumber = obj.chequeNumber;
    }

    // Getters

    public double getRegularPay() {
        return regularPay;
    }

    public double getOvertimePay() {
        return overtimePay;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public LocalDate getDateOfProcessing() {
        return dateOfProcessing;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    // Setters

    public void setRegularPay(double regularPay) {
        this.regularPay = regularPay;
    }

    public void setOvertimePay(double overtimePay) {
        this.overtimePay = overtimePay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public void setDateOfProcessing(LocalDate dateOfProcessing) {
        this.dateOfProcessing = dateOfProcessing;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }
    // Helper method to print table header
    public static void printTableHeader() {
        String format = "| %-20s ";
        String separator = "+----------------------+";
        String[] headers = {
                "Employee ID", "First Name", "Last Name","Dept. Code", "Position",
                "Tax Registration", "NIS Scheme", "Date of Birth", "Date of Hire",
                "Hours Worked", "Regular Pay", "Overtime Pay", "Gross Pay",
                "Date of Processing", "Cheque Number"
        };

        StringBuilder sb = new StringBuilder();
        sb.append(separator.repeat(headers.length)).append("+\n");

        for (String header : headers) {
            sb.append(String.format(format, header));
        }

        sb.append("|\n").append(separator.repeat(headers.length)).append("+\n");

        System.out.println(sb.toString());
    }
    // Method to convert Payroll object to string
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDateOfBirth = dateFormatter.format(getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        String formattedDateOfHire = dateFormatter.format(getDateOfHire().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        String formattedDateOfProcessing = (dateOfProcessing != null) ? dateFormatter.format(dateOfProcessing) : "";

        return String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20.2f | %-20.2f | %-20.2f | %-20.2f | %-20s | %-20s |\n",
                getEmployeeID(), getFirstName(), getLastName(), getEmployeeDepartmentCode(), getPosition(), getTaxRegistrationNumber(),
                getNationalInsuranceScheme(), formattedDateOfBirth, formattedDateOfHire, getHrsWorked(), getRegularPay(), getOvertimePay(),
                getGrossPay(),formattedDateOfProcessing,getChequeNumber()) +
                "+----------------------+----------------------+----------------------+-----------------+-----------------+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+";
    }
    // Method to serialize Payroll object to a formatted string
    public String serializeToString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convert java.util.Date to java.time.LocalDate
        LocalDate dateOfBirth = getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateOfHire = getDateOfHire().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Serialize Payroll object to a formatted string
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                getEmployeeID(), getFirstName(), getLastName(), getEmployeeDepartmentCode(), getPosition(),
                getTaxRegistrationNumber(), getNationalInsuranceScheme(),
                dateFormatter.format(dateOfBirth), dateFormatter.format(dateOfHire),
                getHrsWorked(), getRegularPay(), getOvertimePay(), getGrossPay(),
                dateFormatter.format(getDateOfProcessing()), getChequeNumber());
    }

    // Method to deserialize a string to a Payroll object
    public static Payroll deserializeToString(String data) {
        // Deserialize string to a Payroll object
        String[] parts = data.split(","); // Change this line
         // Check if the string contains at least 15 fields
        if (parts.length >= 15) { // Assuming at least 14 fields in the record
            Payroll payroll = new Payroll();

            payroll.setEmployeeID(parts[0]);
            payroll.setFirstName(parts[1]);
            payroll.setLastName(parts[2]);
            payroll.setPosition(parts[4]);
            payroll.setEmployeeDepartmentCode(parts[3]);
            payroll.setTaxRegistrationNumber(parts[5]);
            payroll.setNationalInsuranceScheme(parts[6]);
            // Use of exception handling to Parse date strings
            try {
                payroll.setDateOfBirth(parseDate(parts[7]));
                payroll.setDateOfHire(parseDate(parts[8]));
            } catch (ParseException e) {
                System.out.println("Error parsing date: " + e.getMessage());
                return null;
            }
            payroll.setHrsWorked(Double.parseDouble(parts[9]));
            payroll.setRegularPay(Double.parseDouble(parts[10]));
            payroll.setOvertimePay(Double.parseDouble(parts[11]));
            payroll.setGrossPay(Double.parseDouble(parts[12]));
            payroll.setDateOfProcessing(LocalDate.parse(parts[13], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            payroll.setChequeNumber(parts[14]);
            return payroll;
        } else {
            System.out.println("Invalid payroll record format: " + data);
            return null;
        }
    }

    // Helper method to parse date strings
    private static Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.parse(dateString);
    }


   // Method to process payroll
    public static void processPayroll() {
        // Retrieve all employee records
        ArrayList<String> employeeRecords = FileManager.viewAllRecords("Employee Payroll.txt");
       // Check if there are any employee records
        if (!employeeRecords.isEmpty()) {
            for (String record : employeeRecords) {
                // Deserialize employee record
                Employee employee = Employee.deserializeToString(record);

                if (employee != null) {
                    // Retrieve department record
                    Department department = Department.deserializeToString(Objects.requireNonNull(FileManager.searchForRecord("Department Rates.txt", employee.getEmployeeDepartmentCode())));

                    if (department != null) {
                        // Calculate regular pay, overtime pay, and gross pay
                        double regularPay = calculateRegularPay(employee, department);
                        double overtimePay = calculateOvertimePay(employee, department);
                        double grossPay = regularPay + overtimePay;

                        // Create a Payroll object with a unique 6-digit random cheque number
                        Payroll payroll = createPayrollRecord(employee, regularPay, overtimePay, grossPay);

                        // Serialize and write to the Processed Payroll File
                        FileManager.writeToFile("Processed Payroll.txt", payroll.serializeToString());
                    } else {
                        System.out.println("Department record not found for employee with ID: " + employee.getEmployeeID());
                    }
                } else {
                    System.out.println("Error deserializing employee record.");
                }
            }

            System.out.println("Payroll processed successfully.");
        } else {
            System.out.println("No employee records found.");
        }
    }

    // Helper method to calculate regular pay
    private static double calculateRegularPay(Employee employee, Department department) {
        double regularHourlyRate = department.getRegularRate();
        double hoursWorked = employee.getHrsWorked();

        // Check if the employee worked more than 40 hours (overtime)
        if (hoursWorked > 40) {
            // For regular hours (up to 40 hours)
            return 40 * regularHourlyRate;
        } else {
            // If the employee worked 40 hours or less, calculate regular pay only
            return hoursWorked * regularHourlyRate;
        }
    }


    // Helper method to calculate overtime pay
    private static double calculateOvertimePay(Employee employee, Department department) {
        double overtimeHourlyRate = department.getOvertimeRate();
        double hoursWorked = employee.getHrsWorked();

        // Check if the employee worked more than 40 hours (overtime)
        if (hoursWorked > 40) {
            // Calculate overtime hours
            double overtimeHours = hoursWorked - 40;

            // Calculate overtime pay
            return overtimeHours * overtimeHourlyRate;
        } else {
            // If the employee worked 40 hours or less, there is no overtime pay
            return 0.0;
        }
    }


    // Helper method to create a Payroll record with a unique cheque number
    private static Payroll createPayrollRecord(Employee employee, double regularPay, double overtimePay, double grossPay) {
        // Your logic to create a Payroll record with a unique cheque number
        // Generate a 6-digit random cheque number
        Random random = new Random();
        int randomChequeNumber = 100000 + random.nextInt(900000); // Generates a random number between 100,000 and 999,999

        // Convert the random number to a string
        String chequeNumber = String.valueOf(randomChequeNumber);

        // Assuming date of processing is the current date
        Payroll payroll = new Payroll(employee, regularPay, overtimePay, grossPay,
                LocalDate.now(), chequeNumber);

        return payroll;
    }

    // Method to view a single employee payroll record
    public static void viewPayroll(String employeeId) {
        // Retrieve the payroll record for the specified employee ID
        String payrollRecord = FileManager.searchForRecord("Processed Payroll.txt", employeeId);

        if (payrollRecord != null) {
            // Deserialize the payroll record
            Payroll payroll = Payroll.deserializeToString(payrollRecord);

            // Display the payroll information
            System.out.println("Employee Payroll Record:\n");
            printTableHeader();
            System.out.println(payroll);
        } else {
            System.out.println("Payroll record not found for employee with ID: " + employeeId);
        }
    }

    // Method to view all employee payroll records for a specific department
    public static void viewDepartmentPayroll(String departmentCode) {
        // Retrieve all payroll records
        ArrayList<String> payrollRecords = FileManager.viewAllRecords("Processed Payroll.txt");

        // Check if there are any payroll records
        if (!payrollRecords.isEmpty()) {
            // Display the payroll information
            System.out.println("Department Payroll Records:\n");
            printTableHeader();
            for (String record : payrollRecords) {
                // Deserialize payroll record
                Payroll payroll = Payroll.deserializeToString(record);

                if (payroll != null) {
                    // Check if the payroll record belongs to the specified department
                    if (departmentCode.equals(payroll.getEmployeeDepartmentCode())) {
                        System.out.println(payroll);
                    }
                } else {
                    System.out.println("Error deserializing payroll record.");
                }
            }

        } else {
            System.out.println("No payroll records found.");
        }
    }

}
