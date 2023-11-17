import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        mainMenu();

    }

    public static void mainMenu(){

        char option;


        System.out.println("Select from the menu below");
        System.out.println("1. Department Record Management");
        System.out.println("2. Employee Record Management");
        System.out.println("3. Payroll Processing Management");
        System.out.println("4. Exit");

        option = scanner.next().charAt(0);
        scanner.nextLine();


        switch (option) {
            case '1':
                departmentMenu();
                break;
            case '2':
                employeeMenu();
                break;
            case '3':
                payrollMenu();
                break;
            case '4':
                System.out.println("Exiting the program.");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input entered. Please enter a valid option.");
                scanner.nextLine(); // Consume invalid input and move to the next line
                break;
        }
    }

    public static void departmentMenu(){
        char departmentOption;
        FileManager.fileStatus("Department Rates.txt");

        System.out.println("Select an option:");
        System.out.println("1. Add");
        System.out.println("2. Update");
        System.out.println("3. View");
        System.out.println("4. View All");
        System.out.println("5. Return to Main Menu");


        departmentOption = scanner.next().charAt(0);
        scanner.nextLine();

        switch (departmentOption) {
            case '1':
                Department newDepartment = Department.addDepartmentRecord();
                System.out.println("New Department Record:");
                Department.displayDepartmentInfo(newDepartment);
                break;
            case '2':
                Department updatedDepartment = Department.updateDepartmentRecord();
                if (updatedDepartment != null) {
                    System.out.println("Updated Department Record:");
                    Department.displayDepartmentInfo(updatedDepartment);
                }else {
                    System.out.println("Update unsuccessful. Department record not found or invalid format.");
                }
                break;
            case '3':
                Department singleRecord = new Department();
                singleRecord.viewDepartmentRecord();
                break;
            case '4':
                Department.viewAllDepartmentRecords();
                break;
            case '5':
                mainMenu();
                break;
            default:
                System.out.println("Invalid input");
                scanner.nextLine();
                break;
        }
    }

    public static void employeeMenu(){
        char employeeOption;
        FileManager.fileStatus("Employee Payroll.txt");


        System.out.println("Select an option:");
        System.out.println("1. Add");
        System.out.println("2. Update");
        System.out.println("3. View");
        System.out.println("4. View All in Department");
        System.out.println("5. Delete");
        System.out.println("6. Return to Main Menu");

        employeeOption = scanner.next().charAt(0);
        scanner.nextLine();


        switch (employeeOption) {
            case '1':
                Employee newEmployee = Employee.addEmployeeRecord();
                System.out.println("New Employee Record:");
                Employee.displayEmployeeInfo(newEmployee);
                break;
            case '2':
                Employee updatedEmployee = Employee.updateEmployeeRecord();

                if (updatedEmployee != null) {
                    System.out.println("Updated Employee Record:");
                    Employee.displayEmployeeInfo(updatedEmployee);
                } else {
                    System.out.println("Update unsuccessful. Employee record not found or invalid format.");
                }
                break;
            case '3':
                Employee.viewEmployeeRecord();
                break;
            case '4':
                Employee.viewAllEmployeeRecords();
                break;
            case '5':
                Employee.deleteEmployeeRecord();
                break;
            case '6':
                mainMenu();
                break;
            default:
                System.out.println("Invalid input");
                scanner.nextLine();
                break;
        }
    }

    public static void payrollMenu() {
        char payrollOption;
        FileManager.fileStatus("Processed Payroll.txt");

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Process Payroll");
            System.out.println("2. View Payroll");
            System.out.println("3. View Department Payroll");
            System.out.println("4. Back to Main Menu");
            System.out.println("5.Do you want to continue ");


            payrollOption = scanner.next().charAt(0);
            scanner.nextLine();

            switch (payrollOption) {
                case '1':
                    // Process Payroll
                    Payroll.processPayroll();
                    break;
                case '2':
                    // View Single Employee Payroll
                    System.out.println("Enter Employee ID:");
                    String employeeId = scanner.nextLine();
                    Payroll.viewPayroll(employeeId);
                    break;
                case '3':
                    // View Department Payroll
                    System.out.println("Enter Department Code:");
                    String departmentCode = scanner.nextLine();
                    Payroll.viewDepartmentPayroll(departmentCode);
                    break;
                case '4':
                    mainMenu();
                    break;
                case '5':
                    System.out.println("Exiting The Program");
                    System.exit(0);
                default:
                    System.out.println("Invalid input entered");
                    scanner.nextLine();
                    break;
            }

        }


    }
}