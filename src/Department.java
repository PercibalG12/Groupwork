import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

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

    public String toString(){
        return  String.format("| %-20s | %-20s | %-15.2f | %-15.2f |\n", departmentCode, departmentName, regularRate, overtimeRate) +
                "+----------------------+----------------------+-----------------+------------------+";
    }

    public static void printTableHeader() {
        System.out.printf(
                "+----------------------+----------------------+-----------------+------------------+%n" +
                        "| %-20s | %-20s | %-15s | %-16s |%n" +
                        "+----------------------+----------------------+-----------------+------------------+%n",
                "Department Code", "Department Name", "Regular Rate", "Overtime Rate"
        );
    }

    public static boolean isValidDepartmentCode(String departmentCode){

        //Define a regular expression pattern
        String regexPattern= "\\d+001";

        //Check if the code matches the pattern
        return Pattern.matches(regexPattern,departmentCode);
    }

    public String serializeToString(){
        return getDepartmentCode()+", "+getDepartmentName()+", "+getRegularRate()+", "+getOvertimeRate();
    }

    public static Department addDepartmentRecord(){
        Scanner scanner= new Scanner(System.in);
        Department department = new Department();

        boolean validCodeEntered = false;
        while (!validCodeEntered) {
            System.out.print("Enter the department code: ");
            String departmentCode = scanner.nextLine();

            if(isValidDepartmentCode(departmentCode)&&!FileManager.searchFile("Department Rates.txt",departmentCode)){
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

        String serializedData=department.serializeToString();

        FileManager.writeToFile("Department Rates.txt", serializedData);


        return department;
    }

    private static double getValidNumericInput(String prompt) {
        double value = 0.0;
        boolean validInput = false;

        try (Scanner scanner = new Scanner(System.in)) {
            while (!validInput) {
                try {
                    System.out.print(prompt);
                    value = Double.parseDouble(scanner.nextLine());
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid numeric value.");
                }
            }
        }

        return value;
    }

    public Department updateDepartmentRecord() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the department code to update: ");
        String departmentCodeToUpdate = scanner.nextLine();

        if (isValidDepartmentCode(departmentCodeToUpdate) && FileManager.searchFile("Department Rates.txt", departmentCodeToUpdate)) {
            // If the department code is valid and found, prompt the user for updated information
            System.out.print("Enter the new department name: ");
            String newDepartmentName = scanner.nextLine();
            setDepartmentName(newDepartmentName);

            double newRegularRate = getValidNumericInput("Enter the new Regular Rate: ");
            setRegularRate(newRegularRate);

            double newOvertimeRate = getValidNumericInput("Enter the new Overtime Rate: ");
            setOvertimeRate(newOvertimeRate);

            // Update the record in the file
            FileManager.updateDepartmentRecordInFile("Department Rates.txt", this);
            System.out.println("Record updated successfully.");

            scanner.close();
            return this; // Return the updated Department object
        } else {
            if (!isValidDepartmentCode(departmentCodeToUpdate)) {
                System.out.println("Invalid department code format. Please enter a valid code.");
            } else {
                System.out.println("Department code not found.");
            }

            scanner.close();
            return null; // Return null if the update was unsuccessful
        }
    }

    public static Department searchDepartmentByCode(String departmentCode) {
        if (!isValidDepartmentCode(departmentCode)) {
            System.out.println("Invalid department code format.");
            return null;
        }

        String filePath = "Department Rates.txt";
        Department department = null;

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.contains(departmentCode)) {
                    department = deserializeDepartment(line);
                    break; // Stop searching after finding the first match
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + filePath, e);
        }

        if (department != null) {
            System.out.println("Department found. Deserializing and displaying information:");
            displayDepartmentInfo(department);
        } else {
            System.out.println("Department not found for the given code.");
        }

        return department;
    }

    private static Department deserializeDepartment(String serializedData) {
        String[] parts = serializedData.split(", ");
        if (parts.length == 4) {
            return new Department(parts[0], parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
        } else {
            System.out.println("Error in deserialization. Invalid data format.");
            return null;
        }
    }

    private static void displayDepartmentInfo(Department department) {
        System.out.println("Department Information:");
        Department.printTableHeader();
        System.out.println(department.toString());
    }

    public void viewDepartmentRecord() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the department code to view: ");
        String departmentCode = scanner.nextLine();
        Department foundDepartment = searchDepartmentByCode(departmentCode);

        scanner.close();
    }

    public static void viewAllDepartmentRecords() {
        String filePath = "Department Rates.txt";

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            System.out.println("All Department Records:");
            printTableHeader();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Department department = deserializeDepartment(line);
                if (department != null) {
                    System.out.println(department.toString());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + filePath, e);
        }
    }
}