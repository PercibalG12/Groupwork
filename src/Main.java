import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

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
                Department.printTableHeader();
                System.out.println(newDepartment.toString());
                break;
            case '2':
                Department updatedDepartment = updateDepartment();
                if (updatedDepartment != null) {
                    System.out.println("Updated Department Record:");
                    Department.printTableHeader();
                    System.out.println(updatedDepartment.toString());
                }else {
                    System.out.println("Update unsuccessful. Department record not found or invalid format.");
                }
                break;
            case '3':
                Department singleRecord = new Department();
                singleRecord.viewDepartmentRecord();
                break;
            case '4':
                Department allRecords = new Department();
                allRecords.viewAllDepartmentRecords();
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
                //Add Employee Record
                break;
            case '2':
                //Update Employee Record
                break;
            case '3':
                //View a single record
                break;
            case '4':
                //View all records
                break;
            case '5':
                //Delete a record
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
                    //Process Payroll
                    break;
                case '2':
                    //View Single Employee Payroll
                    break;
                case '3':
                    //View Department Payroll
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

    public static Department updateDepartment() {
        Department department = new Department();
        return department.updateDepartmentRecord();
    }
}