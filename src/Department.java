import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Department
{
    private int deptCode;
    private String deptName;
    private double regularRate;
    private double overtimeRate;

    // Constructor, getters, and setters


    public Department(int deptCode, String deptName, double regularRate, double overtimeRate)
    {
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.regularRate = regularRate;
        this.overtimeRate = overtimeRate;
    }

    public int getDeptCode()
    {
        return deptCode;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public double getRegularRate()
    {
        return regularRate;
    }

    public double getOvertimeRate()
    {
        return overtimeRate;
    }
}
