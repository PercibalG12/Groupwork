import java.util.Scanner;
import java.util.regex.Pattern;
public class Department{
    private String departmentCode;
    private String departmentName;
    private double regularRate;
    private double overtimeRate;

    // Default Constructor
    public Department() {
        this("", "", 0.0, 0.0);
    }

    // Primary Constructor
    public Department(String departmentCode, String departmentName, double regularRate, double overtimeRate) {
        this.setDepartmentCode(departmentCode);
        this.setDepartmentName(departmentName);
        this.setRegularRate(regularRate);
        this.setOvertimeRate(overtimeRate);
    }

    // Copy Constructor
    public Department(Department obj) {
        this(obj.getDepartmentCode(), obj.getDepartmentName(), obj.getRegularRate(), obj.getOvertimeRate());
    }

    // Getters
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

    // Setters
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

    // Override the toString method
    @Override
    public String toString() {
        return "+----------------------+----------------------+-----------------+------------------+\n" +
                "| Department Code      | Department Name      | Regular Rate    | Overtime Rate    |\n" +
                "+----------------------+----------------------+-----------------+------------------+\n" +
                String.format("| %-20s | %-20s | %-15.2f | %-15.2f |\n", departmentCode, departmentName, regularRate, overtimeRate) +
                "+----------------------+----------------------+-----------------+------------------+";
    }

    // Validate department code format
    public static boolean isValidDepartmentCode(String departmentCode) {
        // Define a regular expression pattern for the format "{int}001"
        String regexPattern = "\\d+001";
        // Check if the provided departmentCode matches the pattern
        return Pattern.matches(regexPattern, departmentCode);
    }

    // Check if a department code is unique in a specified file
    public static boolean isDepartmentCodeUnique(String departmentCode, String fileName) {
        return FileManager.isCodeUniqueInFile(departmentCode, fileName);
    }


    // Method to input data for a Department object
    public static Department addDepartmentRecord() {
        Scanner scanner = new Scanner(System.in);
        Department department = new Department();

        boolean validCodeEntered = false;

        while (!validCodeEntered) {
            System.out.println("Enter Department Code: ");
            String departmentCode = scanner.nextLine();

            if (isValidDepartmentCode(departmentCode) && isDepartmentCodeUnique(departmentCode, "Department Rates.txt")) {
                department.setDepartmentCode(departmentCode);
                validCodeEntered = true; // Exit the loop when a valid and unique code is entered
            } else if (!isValidDepartmentCode(departmentCode)) {
                System.out.println("Invalid department code format. Please enter a valid code.");
            } else {
                System.out.println("Department code already exists. Please enter a unique code.");
            }
        }

        System.out.println("Enter Department Name: ");
        department.setDepartmentName(scanner.nextLine());

        System.out.println("Enter Regular Rate: ");
        department.setRegularRate(scanner.nextDouble());

        System.out.println("Enter Overtime Rate: ");
        department.setOvertimeRate(scanner.nextDouble());

        // Close the Scanner to release resources
        scanner.close();

        // Serialize the Department object to a string
        String serializedData = department.serializeToString();

        // Write the serialized data to a file
        FileManager.writeToFile("Department Rates.txt", serializedData);

        return department;
    }

    // Method to display department data
    public void displayDepartmentRecord() {
        System.out.println(this.toString());
    }

    // Serialize the Department object to a string
    public String serializeToString() {
        // Use a delimiter, such as tab, to separate attributes
        return getDepartmentCode() + "\t" + getDepartmentName().replace("\n", "\t") + "\t" + getRegularRate() + "\t" + getOvertimeRate();
    }

    // Deserialize a string to create a Department object
    public static Department deserializeFromString(String data) {
        String[] parts = data.split("\t");
        if (parts.length != 4) {
            return null; // Invalid data
        }

        String departmentCode = parts[0];
        String departmentName = parts[1].replace("\t", "\n");
        double regularRate = Double.parseDouble(parts[2]);
        double overtimeRate = Double.parseDouble(parts[3]);

        return new Department(departmentCode, departmentName, regularRate, overtimeRate);
    }

    public void updateDepartmentRecord() {
        // Prompt the user to enter the departmentCode for the record they want to update
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Department Code of the record you want to update: ");
        String targetDepartmentCode = scanner.nextLine();

        // Search for the department record in the file
        String existingRecord = FileManager.findRecordByCode(targetDepartmentCode, "Department Rates.txt");

        if (existingRecord != null) {
            // Update the department record
            System.out.println("Enter the new Department Name: ");
            String newDepartmentName = scanner.nextLine();

            System.out.println("Enter the new Regular Rate: ");
            double newRegularRate = scanner.nextDouble();

            System.out.println("Enter the new Overtime Rate: ");
            double newOvertimeRate = scanner.nextDouble();

            setDepartmentName(newDepartmentName);
            setRegularRate(newRegularRate);
            setOvertimeRate(newOvertimeRate);

            // Serialize the updated department record
            String updatedData = serializeToString();

            // Use the updateRecord method from FileManager to update the record in the file
            if (FileManager.updateRecord("Department Rates.txt", targetDepartmentCode, updatedData)) {
                System.out.println("Record updated successfully.");
            } else {
                System.out.println("Error updating the record.");
            }
        } else {
            System.out.println("Department record not found with the specified code.");
        }

        // Close the Scanner to release resources
        scanner.close();
    }

    public void viewDepartmentRecord() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Department Code or Department Name: ");
        String input = scanner.nextLine();

        String data = FileManager.readFromFile("Department Rates.txt");

        if (data != null) {
            String[] records = data.split("\n");

            if (isValidDepartmentCode(input)) {
                // Input is a valid department code
                String record = FileManager.findRecordByCode(input, "Department Rates.txt");
                if (record != null) {
                    Department department = deserializeFromString(record);
                    if (department != null) {
                        department.displayDepartmentRecord();
                    } else {
                        System.out.println("Error deserializing the department record.");
                    }
                } else {
                    System.out.println("Department record not found with the specified code.");
                }
            } else {
                // Input is not a valid department code, so assume it's a department name

                boolean found = false;
                for (int i = 0; i < records.length; i += 4) {
                    if (records[i + 1].equalsIgnoreCase(input)) {
                        Department department = deserializeFromString(records[i] + "\t" + records[i + 1] + "\t" + records[i + 2] + "\t" + records[i + 3]);
                        if (department != null) {
                            department.displayDepartmentRecord();
                        } else {
                            System.out.println("Error deserializing the department record.");
                        }
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Department record not found with the specified code or name.");
                }
            }
        } else {
            System.out.println("Error reading data from the file.");
        }

        // Close the Scanner to release resources
        scanner.close();
    }

    // Add this method to the Department class
    public static void viewAllDepartmentRecords() {
        String data = FileManager.readFromFile("Department Rates.txt");

        if (data != null && !data.isEmpty()) {
            String[] records = data.split("\n");
            int recordCount = records.length / 4;

            // Header row
            System.out.println("+----------------------+----------------------+-----------------+------------------+");
            System.out.println("| Department Code      | Department Name      | Regular Rate    | Overtime Rate    |");
            System.out.println("+----------------------+----------------------+-----------------+------------------+");

            for (int i = 0; i < recordCount; i++) {
                int startIndex = i * 4;
                String departmentCode = records[startIndex];
                String departmentName = records[startIndex + 1].replace("\t", "\n");
                double regularRate = Double.parseDouble(records[startIndex + 2]);
                double overtimeRate = Double.parseDouble(records[startIndex + 3]);

                // Print each record
                System.out.printf("| %-20s | %-20s | %-15.2f | %-15.2f |\n", departmentCode, departmentName, regularRate, overtimeRate);
            }

            // Footer row
            System.out.println("+----------------------+----------------------+-----------------+------------------+");
        } else {
            System.out.println("No department records found.");
        }
    }
}