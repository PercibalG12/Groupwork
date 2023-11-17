import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Department {
    private String departmentCode;
    private String departmentName;
    private double regularRate;
    private double overtimeRate;


    //Constructor
    //Default Constructor
    Department(){
        this("","",0.0,0.0);
    }

    //Primary Constructor
    Department(String departmentCode, String departmentName, double regularRate, double overtimeRate){
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.regularRate = regularRate;
        this.overtimeRate = overtimeRate;
    }

    //Copy Constructor
    Department(Department obj){
        this(obj.departmentCode, obj.departmentName, obj.regularRate,obj.overtimeRate);
    }

    //Getters
    public String getDepartmentCode() {
        return departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public double getRegularRate() {
        return regularRate;
    }

    public double getOvertimeRate() {
        return overtimeRate;
    }

    //Setters
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setRegularRate(double regularRate) {
        this.regularRate = regularRate;
    }

    public void setOvertimeRate(double overtimeRate) {
        this.overtimeRate = overtimeRate;
    }

    //Header and Body
    public static void printTableHeader() {
        System.out.printf(
                "+----------------------+----------------------+-----------------+------------------+%n" +
                        "| %-20s | %-20s | %-15s | %-16s |%n" +
                        "+----------------------+----------------------+-----------------+------------------+%n",
                "Department Code", "Department Name", "Regular Rate", "Overtime Rate"
        );
    }

    @Override
    public String toString() {
        return  String.format("| %-20s | %-20s | %-15.2f | %-15.2f |\n", departmentCode, departmentName, regularRate, overtimeRate) +
                "+----------------------+----------------------+-----------------+------------------+";
    }

    public static void displayDepartmentInfo(Department department) {
        System.out.println("Department Information:");
        Department.printTableHeader();
        System.out.println(department.toString());
    }


    //Attribute Validations
    public static boolean isValidDepartmentCode(String departmentCode){

        //Define a regular expression pattern
        String regexPattern= "\\d+001";

        //Check if the code matches the pattern
        return Pattern.matches(regexPattern,departmentCode);
    }
    private static double getValidNumericInput(String rates) {
        double value = 0.0;
        boolean validInput = false;

        try (Scanner scanner = new Scanner(System.in)) {
            while (!validInput) {
                try {
                    System.out.print(rates);
                    value = Double.parseDouble(scanner.nextLine());
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid numeric value.");
                }
            }
        }

        return value;
    }

    //Serialization
    public String serializeToString(){
        return getDepartmentCode()+"\t"+getDepartmentName()+"\t"+getRegularRate()+"\t"+getOvertimeRate();
    }

    public static Department deserializeToString(String record){
        String[] parts = record.split("\t");
        if (parts.length != 4) {
            return null; // Invalid data
        }
        String departmentCode =  parts[0];
        String departmentName = parts[1];
        double regularRate = Double.parseDouble(parts[2]);
        double overtimeRate = Double.parseDouble(parts[3]);

        return new Department(departmentCode, departmentName,regularRate,overtimeRate);
    }

    public static Department addDepartmentRecord(){
        Scanner scanner= new Scanner(System.in);
        Department department = new Department();

        boolean validCodeEntered = false;
        while (!validCodeEntered) {
            System.out.print("Enter the department code: ");
            String departmentCode = scanner.nextLine();

            if(isValidDepartmentCode(departmentCode)&&!FileManager.doesRecordExist("Department Rates.txt",departmentCode)){
                department.setDepartmentCode(departmentCode);
                validCodeEntered = true;
            }else if (!isValidDepartmentCode(departmentCode)){
                System.out.println("Invalid department code format.Please enter a valid code.");
            }else{
                System.out.println("Department code already exists. Please enter a unique code.");
            }
        }

        System.out.print("Enter the department name: ");
        String departmentName = scanner.nextLine();
        department.setDepartmentName(departmentName);

        double regularRate = getValidNumericInput("Enter the Regular Rate: ");
        department.setRegularRate(regularRate);

        double overtimeRate = getValidNumericInput("Enter the Overtime Rate: ");
        department.setOvertimeRate(overtimeRate);

        scanner.close();

        String serializedData = department.serializeToString();
        FileManager.writeToFile("Department Rates.txt",serializedData);

        return department;
    }

    public static Department updateDepartmentRecord() {
        Scanner scanner = new Scanner(System.in);
        Department updatedDepartment = new Department();

        // Declare updateCode outside the loop
        String updateCode = "";

        boolean validCodeEntered = false;
        while (!validCodeEntered) {
            System.out.print("Enter the Department Code for the record to be updated: ");
            updateCode = scanner.nextLine();

            if (isValidDepartmentCode(updateCode) && FileManager.doesRecordExist("Department Rates.txt", updateCode)) {
                updatedDepartment.setDepartmentCode(updateCode);
                validCodeEntered = true;
            } else if (!isValidDepartmentCode(updateCode)) {
                System.out.println("Invalid department code format. Please enter a valid code.");
            } else {
                System.out.println("Department code doesn't exist. Please enter an existing code.");
            }
        }

        String existingRecord = FileManager.searchForRecord("Department Rates.txt", updateCode);

        if (existingRecord != null) {
            // Use deserializeToString to populate updatedDepartment
            updatedDepartment = deserializeToString(existingRecord);

            assert updatedDepartment != null;
            System.out.println("Updating the record for Department Code: " + updatedDepartment.getDepartmentCode());

            System.out.print("Enter the new Department Name: ");
            updatedDepartment.setDepartmentName(scanner.nextLine());

            double regularRate = getValidNumericInput("Enter the new Regular Rate: ");
            updatedDepartment.setRegularRate(regularRate);

            double overtimeRate = getValidNumericInput("Enter the new Overtime Rate: ");
            updatedDepartment.setOvertimeRate(overtimeRate);
            scanner.close();

            FileManager.updateRecord("Department Rates.txt", updateCode, updatedDepartment.serializeToString());
            System.out.println("Record updated successfully.");

            return updatedDepartment;
        } else {
            System.out.println("Record not found.");
            return null;
        }
    }

    public void viewDepartmentRecord() {
        Scanner scanner = new Scanner(System.in);
        String search = null;

        boolean validCodeEntered = false;
        while (!validCodeEntered) {
            System.out.print("Enter the Department Code for the record to be updated: ");
            String departmentCode = scanner.nextLine();

            if (isValidDepartmentCode(departmentCode) && FileManager.doesRecordExist("Department Rates.txt", departmentCode)) {
                search = FileManager.searchForRecord("Department Rates.txt",departmentCode);
                validCodeEntered = true;
            } else if (!isValidDepartmentCode(departmentCode)) {
                System.out.println("Invalid department code format. Please enter a valid code.");
            } else {
                System.out.println("Department code doesn't exist. Please enter an existing code.");
            }
        }

        assert search != null;
        Department singleRecord = deserializeToString(search);
        assert singleRecord != null;
        displayDepartmentInfo(singleRecord);

        scanner.close();
    }

    // Method to view all records in the Department class using FileManager's readAllLines method
    public static void viewAllDepartmentRecords() {
        printTableHeader();

        // Use FileManager to get all records
        ArrayList<String> records = FileManager.viewAllRecords("Department Rates.txt");

        // Check if records is not null
        if (!records.isEmpty()) {
            // Iterate over each record, deserialize, and print
            for (String record : records) {
                Department department = deserializeToString(record);
                assert department != null;
                System.out.println(department.toString());
            }
        } else {
            System.out.println("No records found.");
        }
    }




}