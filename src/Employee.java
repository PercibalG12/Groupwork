
import java.io.FileWriter;
import java.io.IOException;

public class Employee {
    int empId;
    String name;
    String department;


    public Employee(int id, String name, String department) {
        this.empId = id;
        this.name = name;
        this.department = department;

    }

    public Employee()
    {

    }


    public void addEmployeeRecordToFile()
    {
        try
        {
            try (FileWriter writer = new FileWriter("EmployeeRecord.txt", true))
            {
                writer.write(this.empId + "," + this.name + "," + this.department +"\n");
            }

            System.out.println("Employee " + this.name + " added to file");
        }
        catch (IOException e)
        {
            System.err.println("Error writing to the file: " + e.getMessage());
        }

    }
}


