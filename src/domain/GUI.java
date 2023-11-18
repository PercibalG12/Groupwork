package domain;
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private JPanel mainMenuPanel;
    private JPanel departmentMenuPanel;
    private JPanel employeeMenuPanel;
    private JPanel payrollMenuPanel;

    private Department department;

    private Main main;

    public GUI(Main main) {
        this.main = main;

    }

    public GUI() {
        setTitle("SSN Payroll Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLayout(new BorderLayout());

        mainMenuPanel = createMainMenuPanel();
        departmentMenuPanel = createDepartmentMenuPanel();
        employeeMenuPanel = createEmployeeMenuPanel();
        payrollMenuPanel = createPayrollMenuPanel();

        add(mainMenuPanel, BorderLayout.CENTER);


    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1));

        JButton departmentButton = createMenuButton("Department Record Management");
        JButton employeeButton = createMenuButton("Employee Record Management");
        JButton payrollButton = createMenuButton("Payroll Processing Management");
        JButton exitButton = createMenuButton("Exit");

        departmentButton.addActionListener(e -> switchToPanel(departmentMenuPanel));
        employeeButton.addActionListener(e -> switchToPanel(employeeMenuPanel));
        payrollButton.addActionListener(e -> switchToPanel(payrollMenuPanel));
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(departmentButton);
        panel.add(employeeButton);
        panel.add(payrollButton);
        panel.add(exitButton);



        return panel;
    }

    private JButton createMenuButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        return button;
    }

    private JPanel createDepartmentMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));

        JButton addDepartmentButton = createMenuButton("Add Department Record");
        JButton updateDepartmentButton = createMenuButton("Update Department Record");
        JButton viewDepartmentButton = createMenuButton("View Department Record");
        JButton viewAllDepartmentsButton = createMenuButton("View All Department Records");
        JButton returnToMainMenuButton = createMenuButton("Return to Main Menu");

        addDepartmentButton.addActionListener(e -> {

        });
        updateDepartmentButton.addActionListener(e -> {

        });
        viewDepartmentButton.addActionListener(e -> {

        });
        viewAllDepartmentsButton.addActionListener(e -> {

        });
        returnToMainMenuButton.addActionListener(e -> switchToPanel(mainMenuPanel));

        panel.add(addDepartmentButton);
        panel.add(updateDepartmentButton);
        panel.add(viewDepartmentButton);
        panel.add(viewAllDepartmentsButton);
        panel.add(returnToMainMenuButton);

        return panel;
    }

    private JPanel createEmployeeMenuPanel() {
        JPanel panel = new JPanel();

        return panel;
    }

    private JPanel createPayrollMenuPanel() {

        JPanel panel = new JPanel();

        return panel;
    }

    private void switchToPanel(JPanel panel) {
        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
