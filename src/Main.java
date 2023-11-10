import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Manage Department Rates");
            System.out.println("2. Manage Employee Data");
            System.out.println("3. Process Employee Payroll");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1: // Manage Department Rates
                    System.out.println("Select an option:");
                    System.out.println("1. Add");
                    System.out.println("2. Update");
                    System.out.println("3. View");
                    System.out.println("4. View All");

                    char departmentOption = scanner.nextLine().charAt(0);

                    // Implement the Department Rates actions based on the selected option.
                    switch (departmentOption) {
                        case '1':
                            Department newDepartment = Department.addDepartmentRecord();
                            System.out.println("New Department Record:");
                            newDepartment.displayDepartmentRecord();
                            break;
                        case '2':
                            Department updateDepartment = new Department();
                            updateDepartment.updateDepartmentRecord();
                            System.out.println("Updated Department Record:");
                            updateDepartment.displayDepartmentRecord();
                            break;
                        case '3':
                            Department singleRecord = new Department();
                            singleRecord.viewDepartmentRecord();
                            break;
                        case '4':
                            Department.viewAllDepartmentRecords();
                            break;
                        default:
                            System.out.println("Invalid option for Department Rates.");
                    }
                    break;

                case 2: // Manage Employee Data
                    System.out.println("Select an option:");
                    System.out.println("1. Add");
                    System.out.println("2. Update");
                    System.out.println("3. View");
                    System.out.println("4. View All in Department");
                    System.out.println("5. Delete");

                    char employeeOption = scanner.nextLine().charAt(0);

                    // Implement the Employee Data actions based on the selected option.
                    switch (employeeOption) {
                        case '1':
                            Employee newEmployee = new Employee();
                            newEmployee.addEmployeeRecord();
                            System.out.println("New Employee Record:");
                            newEmployee.displayEmployeeRecord();
                            break;
                        case '2':
                            Employee updatedEmployee = new Employee();
                            updatedEmployee.updateEmployeeRecord();
                            System.out.println("New Employee Record:");
                            updatedEmployee.displayEmployeeRecord();
                            break;
                        case '3':
                            Employee singleEmployeeRecord = new Employee();
                            singleEmployeeRecord.viewEmployeeRecord();
                            break;
                        case '4':
                            Employee.viewAllEmployeesInDepartment();
                            break;
                        case '5':
                            // Delete an employee record
                            // Implement logic to delete an employee record.
                            break;
                        default:
                            System.out.println("Invalid option for Employee Data.");
                    }
                    break;

                case 3: // Process Employee Payroll
                    System.out.println("Select an option:");
                    System.out.println("1. Process Payroll");
                    System.out.println("2. View Payroll");
                    System.out.println("3. View Department Payroll");

                    char payrollOption = scanner.nextLine().charAt(0);

                    // Implement the Employee Payroll actions based on the selected option.
                    switch (payrollOption) {
                        case '1':
                            // Calculate payroll and generate Processed Payroll File.
                            // Implement payroll processing logic.
                            break;
                        case '2':
                            // View a single employee payroll record
                            // Implement logic to display a single employee payroll record.
                            break;
                        case '3':
                            // View all employee payroll records for a specific department
                            // Implement logic to display all employee payroll records for a department.
                            break;
                        default:
                            System.out.println("Invalid option for Employee Payroll.");
                    }
                    break;

                case 4: // Exit
                    System.out.println("Exiting the program.");
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}