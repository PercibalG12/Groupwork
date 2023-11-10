import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;
import java.util.regex.Pattern;

public class Employee extends Department{
    private static String departmentCode;
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

    // Default Constructor
    public Employee() {
        this("", "", "", "", "", "", "", new Date(), new Date(), 0.0);
    }

    public Employee(String employeeID , String firstName, String lastName, String employeeDepartmentCode, String position, String taxRegistrationNumber, String nationalInsuranceScheme, Date dateOfBirth, Date dateOfHire, double hrsWorked) {
        this.setEmployeeID(employeeID);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmployeeDepartmentCode(employeeDepartmentCode);
        this.setPosition(position);
        this.setTaxRegistrationNumber(taxRegistrationNumber);
        this.setNationalInsuranceScheme(nationalInsuranceScheme);
        this.setDateOfBirth(dateOfBirth);
        this.setDateOfHire(dateOfHire);
        this.setHrsWorked(hrsWorked);
    }

    //Copy Constructors
    public Employee(Employee obj){
        this(obj.getEmployeeID(), obj.getFirstName(),obj.getLastName(),obj.getEmployeeDepartmentCode(),obj.getPosition(),obj.getTaxRegistrationNumber(),obj.getNationalInsuranceScheme(),obj.getDateOfBirth(),obj.getDateOfHire(),obj.getHrsWorked());
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

    public void setNationalInsuranceScheme(String NationalInsuranceScheme) {
        this.nationalInsuranceScheme = NationalInsuranceScheme;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfHire(Date dateOfHire) {
        this.dateOfHire = dateOfHire;
    }

    public void setHrsWorked(double hrsWorked) {
        this.hrsWorked = hrsWorked;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "+----------------------+----------------------+----------------------+-----------------+-----------------+-----------------+----------------------+----------------------+-----------------+\n" +
                "| Employee ID          | First Name           | Last Name            | Department Code      | Position            | TRN                 | NIS                  | Date of Birth        | Date of Hire        | Hours Worked       |\n" +
                "+----------------------+----------------------+----------------------+-----------------+-----------------+-----------------+----------------------+----------------------+-----------------+\n" +
                String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-15.2f |\n",
                        employeeID, firstName, lastName, employeeDepartmentCode, position, taxRegistrationNumber, nationalInsuranceScheme,
                        dateFormat.format(dateOfBirth), dateFormat.format(dateOfHire), hrsWorked) +
                "+----------------------+----------------------+----------------------+-----------------+-----------------+-----------------+----------------------+----------------------+-----------------+";
    }

    public String serializeToString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDateOfBirth = dateFormat.format(dateOfBirth);
        String formattedDateOfHire = dateFormat.format(dateOfHire);

        // Use a delimiter, such as tab, to separate attributes
        return employeeID + "\t" + firstName + "\t" + lastName + "\t" + employeeDepartmentCode + "\t" + position
                + "\t" + taxRegistrationNumber + "\t" + nationalInsuranceScheme + "\t" + formattedDateOfBirth
                + "\t" + formattedDateOfHire + "\t" + hrsWorked;
    }

    public static Employee deserializeFromString(String data) {
        String[] parts = data.split("\t");
        if (parts.length != 10) {
            return null; // Invalid data
        }

        String employeeID = parts[0];
        String firstName = parts[1];
        String lastName = parts[2];
        String employeeDepartmentCode = parts[3];
        String position = parts[4];
        String taxRegistrationNumber = parts[5];
        String nationalInsuranceScheme = parts[6];
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOfBirth;
        Date dateOfHire;
        try {
            dateOfBirth = dateFormat.parse(parts[7]);
            dateOfHire = dateFormat.parse(parts[8]);
        } catch (ParseException e) {
            return null; // Invalid date format
        }
        double hrsWorked = Double.parseDouble(parts[9]);

        return new Employee(employeeID, firstName, lastName, employeeDepartmentCode, position, taxRegistrationNumber, nationalInsuranceScheme, dateOfBirth, dateOfHire, hrsWorked);
    }


    public void addEmployeeRecord() {
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee();

        boolean validEmployeeID = false;
        boolean validEmployeeDepartmentCode = false;
        boolean validTRN = false;
        boolean validNIS = false;
        boolean validDateOfBirth = false;
        boolean validDateOfHire = false;

        while (!validEmployeeID) {
            System.out.print("Enter the employee's ID (format: 97 followed by 3 digits): ");
            String employeeID = scanner.nextLine();

            if (isValidEmployeeID(employeeID)) {
                employee.setEmployeeID(employeeID);
                validEmployeeID = true;
            } else {
                System.out.println("Invalid employeeID format. Please enter a valid employeeID.");
            }
        }

        System.out.print("Enter the employee's first name: ");
        employee.setFirstName(scanner.nextLine());

        System.out.print("Enter the employee's last name: ");
        employee.setLastName(scanner.nextLine());

        while (!validEmployeeDepartmentCode) {
            System.out.print("Enter the employee's department code: ");
            String departmentCode = scanner.nextLine();

            if (FileManager.isCodeUniqueInFile(departmentCode, "Department Rates.txt") && isValidDepartmentCode(departmentCode))
            {
                employee.setEmployeeDepartmentCode(departmentCode);
                validEmployeeDepartmentCode = true;
            } else {
                System.out.println("Invalid department code or code already exists. Please enter a valid and unique department code.");
            }
            System.out.println("Valid Employee Department Code: " + validEmployeeDepartmentCode);
        }


        System.out.print("Enter the employee's position: ");
        employee.setPosition(scanner.nextLine());

        while (!validTRN) {
            System.out.print("Enter the employee's TRN (format: 9 digits or 3 digits - 3 digits - 3 digits): ");
            String trn = scanner.nextLine();

            if (isValidTRN(trn)) {
                employee.setTaxRegistrationNumber(trn);
                validTRN = true;
            } else {
                System.out.println("Invalid TRN format. Please enter a valid TRN.");
            }
        }

        while (!validNIS) {
            System.out.print("Enter the employee's NIS (format: letter + 6 digits or letter + space + 2 digits + space + 2 digits + space + 2 digits): ");
            String nis = scanner.nextLine();

            if (isValidNIS(nis)) {
                employee.setNationalInsuranceScheme(nis);
                validNIS = true;
            } else {
                System.out.println("Invalid NIS format. Please enter a valid NIS.");
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        while (!validDateOfBirth) {
            System.out.print("Enter the employee's date of birth (dd/mm/yyyy): ");
            String dateOfBirthString = scanner.nextLine();

            try {
                Date dateOfBirth = dateFormat.parse(dateOfBirthString);
                employee.setDateOfBirth(dateOfBirth);
                validDateOfBirth = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use the format dd/mm/yyyy.");
            }
        }

        while (!validDateOfHire) {
            System.out.print("Enter the date the employee was hired (dd/mm/yyyy): ");
            String dateOfHireString = scanner.nextLine();

            try {
                Date dateOfHire = dateFormat.parse(dateOfHireString);
                employee.setDateOfHire(dateOfHire);
                validDateOfHire = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use the format dd/mm/yyyy.");
            }
        }

        // Serialize the employee record to a string
        String serializedData = employee.serializeToString();

        // Use FileManager to add the serialized data to the "Employee Payroll.txt" file
        FileManager.writeToFile("Employee Payroll.txt", serializedData);

        // Display a success message
        System.out.println("Employee record added successfully.");

        // Close the Scanner to release resources
        scanner.close();
    }

    public void updateEmployeeRecord(){

        Scanner scanner = new Scanner(System.in);
        boolean validEmployeeDepartmentCode = false;
        boolean validTRN = false;
        boolean validNIS = false;
        boolean validDateOfBirth = false;
        boolean validDateOfHire = false;

        System.out.print("Enter the employee's ID (format: 97 followed by 3 digits) to update: ");
        String employeeIDToUpdate = scanner.nextLine();

        // Search for the employee record by ID
        String targetRecord = FileManager.findRecordByCode(employeeIDToUpdate, "Employee Payroll.txt");

        if (targetRecord != null) {
            Employee employee = Employee.deserializeFromString(targetRecord);

            // Allow the user to update the employee record
            System.out.print("Enter the new First Name: ");
            employee.setFirstName(scanner.nextLine());

            System.out.print("Enter the new Last Name: ");
            employee.setLastName(scanner.nextLine());

            while (!validEmployeeDepartmentCode) {
                System.out.print("Enter the employee's department code: ");
                String departmentCode = scanner.nextLine();

                if (FileManager.isCodeUniqueInFile(departmentCode, "Department Rates.txt") && isValidDepartmentCode(departmentCode))
                {
                    employee.setEmployeeDepartmentCode(departmentCode);
                    validEmployeeDepartmentCode = true;
                } else {
                    System.out.println("Invalid department code or code already exists. Please enter a valid and unique department code.");
                }
                System.out.println("Valid Employee Department Code: " + validEmployeeDepartmentCode);
            }

            System.out.print("Enter the new Position: ");
            employee.setPosition(scanner.nextLine());

            while (!validTRN) {
                System.out.print("Enter the employee's TRN (format: 9 digits or 3 digits - 3 digits - 3 digits): ");
                String trn = scanner.nextLine();

                if (isValidTRN(trn)) {
                    employee.setTaxRegistrationNumber(trn);
                    validTRN = true;
                } else {
                    System.out.println("Invalid TRN format. Please enter a valid TRN.");
                }
            }

            while (!validNIS) {
                System.out.print("Enter the employee's NIS (format: letter + 6 digits or letter + space + 2 digits + space + 2 digits + space + 2 digits): ");
                String nis = scanner.nextLine();

                if (isValidNIS(nis)) {
                    employee.setNationalInsuranceScheme(nis);
                    validNIS = true;
                } else {
                    System.out.println("Invalid NIS format. Please enter a valid NIS.");
                }
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            while (!validDateOfBirth) {
                System.out.print("Enter the employee's date of birth (dd/mm/yyyy): ");
                String dateOfBirthString = scanner.nextLine();

                try {
                    Date dateOfBirth = dateFormat.parse(dateOfBirthString);
                    employee.setDateOfBirth(dateOfBirth);
                    validDateOfBirth = true;
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please use the format dd/mm/yyyy.");
                }
            }

            while (!validDateOfHire) {
                System.out.print("Enter the date the employee was hired (dd/mm/yyyy): ");
                String dateOfHireString = scanner.nextLine();

                try {
                    Date dateOfHire = dateFormat.parse(dateOfHireString);
                    employee.setDateOfHire(dateOfHire);
                    validDateOfHire = true;
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please use the format dd/mm/yyyy.");
                }
            }

            // Serialize the updated employee record
            String updatedData = employee.serializeToString();

            // Use the updateRecord method from FileManager to update the record
            if (FileManager.updateRecord("Employee Payroll.txt", employeeIDToUpdate, updatedData)) {
                System.out.println("Employee record updated successfully.");
            } else {
                System.out.println("Error updating the employee record.");
            }
        } else {
            System.out.println("Employee with ID " + employeeIDToUpdate + " not found.");
        }

        // Close the Scanner to release resources
        scanner.close();
    }

    // Create a method to display an Employee record in the Department format
    public void displayEmployeeRecord() {
        System.out.println(this.toString());
    }

    public boolean isValidEmployeeID(String employeeID) {
        // Define a regular expression pattern for the format "97(3 digits)"
        String regexPattern = "^97\\d{3}$";
        // Check if the provided employeeID matches the pattern
        return Pattern.matches(regexPattern, employeeID);
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

    public void viewEmployeeRecord() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        String employeeID = scanner.nextLine();

        String data = FileManager.readFromFile("Employee Payroll.txt");

        if (data != null) {
            String[] records = data.split("\n");

            for (int i = 0; i < records.length; i += 10) {
                if (records[i].equals(employeeID)) {
                    // Found the employee record
                    String employeeRecordData = String.join("\n", Arrays.copyOfRange(records, i, i + 10));
                    Employee employee = Employee.deserializeFromString(employeeRecordData);

                    if (employee != null) {
                        employee.displayEmployeeRecord();
                    } else {
                        System.out.println("Error deserializing the employee record.");
                    }
                    return; // Exit the loop
                }
            }

            System.out.println("Employee record not found with the specified Employee ID.");
        } else {
            System.out.println("Error reading data from the file.");
        }

        scanner.close();
    }

    public static void viewAllEmployeesInDepartment() {
        // Read employee records from the file using FileManager
        List<String> employeeRecords = Collections.singletonList(FileManager.readFromFile("Employee Payroll.txt"));

        for (String employeeRecord : employeeRecords) {
            Employee employee = deserializeFromString(employeeRecord);
            if (employee.getEmployeeDepartmentCode().equals(departmentCode)) {
                // This record belongs to the specified department, so display it
                employee.displayEmployeeRecord(); // Create a display method in the Employee class
            }
        }
    }

    public static void deleteEmployeeRecord(String employeeID) {
        // Read employee records from the file using FileManager
        List<String> employeeRecords = Collections.singletonList(FileManager.readFromFile("Employee Payroll.txt"));

        // Create a new list to store updated records
        List<String> updatedRecords = new ArrayList<>();

        for (String employeeRecord : employeeRecords) {
            Employee employee = deserializeFromString(employeeRecord);

            if (!employee.getEmployeeID().equals(employeeID)) {
                // Keep the record if it doesn't match the specified employee ID
                updatedRecords.add(employeeRecord);
            }
        }

        // Write the updated list of records back to the file using FileManager
        FileManager.writeToFile("Employee Payroll.txt", updatedRecords.toString());
    }
    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Error parsing date. Please use the format 'dd/mm/yyyy'.");
            return null;
        }
    }
}