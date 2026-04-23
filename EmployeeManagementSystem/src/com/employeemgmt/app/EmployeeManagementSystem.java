package com.employeemgmt.app;

import com.employeemgmt.model.Employee;
import com.employeemgmt.service.EmployeeService;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Employee Management System Main Class
 * Provides menu-driven console interface for employee management
 * 
 * @author Employee Management System
 * @version 1.0
 */
public class EmployeeManagementSystem {
    private static EmployeeService service = new EmployeeService();
    private static Scanner scanner = new Scanner(System.in);
    private static final String SEPARATOR = "═══════════════════════════════════════════════════════════════";

    /**
     * Main method - Entry point of the application
     */
    public static void main(String[] args) {
        System.out.println("\n");
        displayWelcome();
        mainMenu();
        scanner.close();
    }

    /**
     * Display welcome banner
     */
    private static void displayWelcome() {
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                               ║");
        System.out.println("║          WELCOME TO EMPLOYEE MANAGEMENT SYSTEM                ║");
        System.out.println("║                   Version 1.0                                 ║");
        System.out.println("║                                                               ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    /**
     * Main menu loop
     */
    private static void mainMenu() {
        boolean running = true;

        while (running) {
            try {
                displayMainMenu();
                System.out.print("\n➤ Enter your choice: ");
                
                if (!scanner.hasNextInt()) {
                    System.out.println("❌ Invalid input! Please enter a number.");
                    scanner.nextLine();
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addNewEmployee();
                        break;
                    case 2:
                        viewAllEmployees();
                        break;
                    case 3:
                        searchEmployee();
                        break;
                    case 4:
                        updateEmployeeDetails();
                        break;
                    case 5:
                        deleteEmployee();
                        break;
                    case 6:
                        displayAdvancedMenu();
                        break;
                    case 7:
                        displayStatistics();
                        break;
                    case 8:
                        running = false;
                        displayExitMessage();
                        break;
                    default:
                        System.out.println("❌ Invalid choice! Please select a valid option.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine();
            }

            if (running) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
                System.out.println("\n");
            }
        }
    }

    /**
     * Display main menu options
     */
    private static void displayMainMenu() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("                       MAIN MENU");
        System.out.println(SEPARATOR);
        System.out.println("1. ➕ Add New Employee");
        System.out.println("2. 👥 View All Employees");
        System.out.println("3. 🔍 Search Employee");
        System.out.println("4. ✏️  Update Employee Details");
        System.out.println("5. 🗑️  Delete Employee");
        System.out.println("6. 🔧 Advanced Options");
        System.out.println("7. 📊 View Statistics");
        System.out.println("8. 🚪 Exit");
        System.out.println(SEPARATOR);
    }

    // ==================== CRUD OPERATIONS ====================

    /**
     * Add a new employee
     */
    private static void addNewEmployee() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("                    ADD NEW EMPLOYEE");
        System.out.println(SEPARATOR);

        try {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Department: ");
            String department = scanner.nextLine().trim();

            System.out.print("Enter Designation: ");
            String designation = scanner.nextLine().trim();

            System.out.print("Enter Salary: ");
            double salary = getValidDouble();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter Phone Number (10 digits): ");
            String phoneNumber = scanner.nextLine().trim();

            if (service.addEmployee(name, department, designation, salary, email, phoneNumber)) {
                System.out.println("\n✅ Employee added successfully!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    /**
     * View all employees
     */
    private static void viewAllEmployees() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("                   ALL EMPLOYEES");
        System.out.println(SEPARATOR);

        try {
            ArrayList<Employee> employees = service.getAllEmployees();

            if (employees.isEmpty()) {
                System.out.println("❌ No employees found in the system!");
                return;
            }

            System.out.println(String.format("\nTotal Employees: %d\n", employees.size()));
            
            for (Employee emp : employees) {
                System.out.println(emp.toShortString());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Search employee by ID
     */
    private static void searchEmployee() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("                   SEARCH EMPLOYEE");
        System.out.println(SEPARATOR);

        System.out.print("\n1. Search by Employee ID\n2. Search by Name\nEnter choice: ");
        int choice = getValidInt();

        try {
            if (choice == 1) {
                System.out.print("Enter Employee ID: ");
                int empId = getValidInt();
                Employee emp = service.searchEmployeeById(empId);

                if (emp != null) {
                    System.out.println("\n✅ Employee Found:\n");
                    System.out.println(emp);
                } else {
                    System.out.println("\n❌ Employee with ID " + empId + " not found!");
                }
            } else if (choice == 2) {
                System.out.print("Enter Employee Name: ");
                String name = scanner.nextLine().trim();
                ArrayList<Employee> results = service.searchEmployeeByName(name);

                if (results.isEmpty()) {
                    System.out.println("\n❌ No employees found with name: " + name);
                } else {
                    System.out.println("\n✅ Found " + results.size() + " employee(s):\n");
                    for (Employee emp : results) {
                        System.out.println(emp.toShortString());
                    }
                }
            } else {
                System.out.println("❌ Invalid choice!");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Update employee details
     */
    private static void updateEmployeeDetails() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("                   UPDATE EMPLOYEE");
        System.out.println(SEPARATOR);

        try {
            System.out.print("\nEnter Employee ID to update: ");
            int empId = getValidInt();

            Employee emp = service.searchEmployeeById(empId);
            if (emp == null) {
                System.out.println("❌ Employee with ID " + empId + " not found!");
                return;
            }

            System.out.println("\n✅ Employee Found: " + emp.getName());
            System.out.println("\nEnter new details (leave blank to keep existing):\n");

            System.out.print("Enter Name [" + emp.getName() + "]: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Department [" + emp.getDepartment() + "]: ");
            String department = scanner.nextLine().trim();

            System.out.print("Enter Designation [" + emp.getDesignation() + "]: ");
            String designation = scanner.nextLine().trim();

            System.out.print("Enter Salary [" + emp.getSalary() + "]: ");
            String salaryInput = scanner.nextLine().trim();
            double salary = salaryInput.isEmpty() ? -1 : Double.parseDouble(salaryInput);

            System.out.print("Enter Email [" + emp.getEmail() + "]: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter Phone Number [" + emp.getPhoneNumber() + "]: ");
            String phoneNumber = scanner.nextLine().trim();

            if (service.updateEmployee(empId, name, department, designation, salary, email, phoneNumber)) {
                System.out.println("\n✅ Employee details updated successfully!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n❌ Error: Invalid input format or other error!");
        }
    }

    /**
     * Delete employee
     */
    private static void deleteEmployee() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("                   DELETE EMPLOYEE");
        System.out.println(SEPARATOR);

        try {
            System.out.print("\nEnter Employee ID to delete: ");
            int empId = getValidInt();

            Employee emp = service.searchEmployeeById(empId);
            if (emp == null) {
                System.out.println("❌ Employee with ID " + empId + " not found!");
                return;
            }

            System.out.println("\n⚠️  Employee to be deleted:");
            System.out.println(emp.toShortString());
            System.out.print("\nAre you sure? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("yes")) {
                if (service.deleteEmployee(empId)) {
                    System.out.println("✅ Employee deleted successfully!");
                }
            } else {
                System.out.println("❌ Deletion cancelled!");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // ==================== ADVANCED OPERATIONS ====================

    /**
     * Display advanced menu options
     */
    private static void displayAdvancedMenu() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("                   ADVANCED OPTIONS");
        System.out.println(SEPARATOR);
        System.out.println("1. Sort Employees by Salary");
        System.out.println("2. Sort Employees by Name");
        System.out.println("3. Sort Employees by Department");
        System.out.println("4. Filter Employees by Department");
        System.out.println("5. Filter Employees by Salary Range");
        System.out.println("6. Back to Main Menu");
        System.out.println(SEPARATOR);

        System.out.print("➤ Enter your choice: ");
        int choice = getValidInt();

        switch (choice) {
            case 1:
                sortBySalary();
                break;
            case 2:
                sortByName();
                break;
            case 3:
                sortByDepartment();
                break;
            case 4:
                filterByDepartment();
                break;
            case 5:
                filterBySalaryRange();
                break;
            case 6:
                break;
            default:
                System.out.println("❌ Invalid choice!");
        }
    }

    /**
     * Sort employees by salary
     */
    private static void sortBySalary() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("            EMPLOYEES SORTED BY SALARY (HIGHEST FIRST)");
        System.out.println(SEPARATOR);

        ArrayList<Employee> sorted = service.sortBySalary();
        if (sorted.isEmpty()) {
            System.out.println("❌ No employees found!");
            return;
        }

        System.out.println();
        for (Employee emp : sorted) {
            System.out.println(emp.toShortString());
        }
    }

    /**
     * Sort employees by name
     */
    private static void sortByName() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("            EMPLOYEES SORTED BY NAME (A-Z)");
        System.out.println(SEPARATOR);

        ArrayList<Employee> sorted = service.sortByName();
        if (sorted.isEmpty()) {
            System.out.println("❌ No employees found!");
            return;
        }

        System.out.println();
        for (Employee emp : sorted) {
            System.out.println(emp.toShortString());
        }
    }

    /**
     * Sort employees by department
     */
    private static void sortByDepartment() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("            EMPLOYEES SORTED BY DEPARTMENT");
        System.out.println(SEPARATOR);

        ArrayList<Employee> sorted = service.sortByDepartment();
        if (sorted.isEmpty()) {
            System.out.println("❌ No employees found!");
            return;
        }

        System.out.println();
        String currentDept = "";
        for (Employee emp : sorted) {
            if (!emp.getDepartment().equals(currentDept)) {
                currentDept = emp.getDepartment();
                System.out.println("\n📁 " + currentDept);
                System.out.println("─────────────────────────────────────────────────────────────");
            }
            System.out.println(emp.toShortString());
        }
    }

    /**
     * Filter employees by department
     */
    private static void filterByDepartment() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("              FILTER EMPLOYEES BY DEPARTMENT");
        System.out.println(SEPARATOR);

        ArrayList<String> departments = service.getAllDepartments();
        if (departments.isEmpty()) {
            System.out.println("\n❌ No departments found!");
            return;
        }

        System.out.println("\nAvailable Departments:");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i));
        }

        System.out.print("\nSelect department (by number): ");
        int choice = getValidInt();

        if (choice < 1 || choice > departments.size()) {
            System.out.println("❌ Invalid choice!");
            return;
        }

        String selectedDept = departments.get(choice - 1);
        ArrayList<Employee> filtered = service.filterByDepartment(selectedDept);

        System.out.println("\n✅ Employees in " + selectedDept + ":\n");
        for (Employee emp : filtered) {
            System.out.println(emp.toShortString());
        }

        double[] stats = service.getDepartmentSalaryStats(selectedDept);
        System.out.println("\n📊 Department Statistics:");
        System.out.println("Total Employees: " + (int)stats[0]);
        System.out.println("Total Salary: ₹" + String.format("%.2f", stats[1]));
        System.out.println("Average Salary: ₹" + String.format("%.2f", stats[2]));
        System.out.println("Highest Salary: ₹" + String.format("%.2f", stats[3]));
        System.out.println("Lowest Salary: ₹" + String.format("%.2f", stats[4]));
    }

    /**
     * Filter employees by salary range
     */
    private static void filterBySalaryRange() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("              FILTER EMPLOYEES BY SALARY RANGE");
        System.out.println(SEPARATOR);

        try {
            System.out.print("\nEnter Minimum Salary: ");
            double minSalary = getValidDouble();

            System.out.print("Enter Maximum Salary: ");
            double maxSalary = getValidDouble();

            if (minSalary > maxSalary) {
                System.out.println("❌ Minimum salary cannot be greater than maximum salary!");
                return;
            }

            ArrayList<Employee> filtered = service.filterBySalaryRange(minSalary, maxSalary);
            if (filtered.isEmpty()) {
                System.out.println("\n❌ No employees found in the salary range ₹" + minSalary + " - ₹" + maxSalary);
                return;
            }

            System.out.println("\n✅ Employees in salary range ₹" + minSalary + " - ₹" + maxSalary + ":\n");
            for (Employee emp : filtered) {
                System.out.println(emp.toShortString());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // ==================== STATISTICS ====================

    /**
     * Display statistics and reports
     */
    private static void displayStatistics() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("                      STATISTICS & REPORTS");
        System.out.println(SEPARATOR);

        if (service.isEmpty()) {
            System.out.println("❌ No employees found in the system!");
            return;
        }

        try {
            double totalSalary = service.calculateTotalSalaryExpenditure();
            double avgSalary = service.calculateAverageSalary();
            Employee highestPaid = service.findHighestPaidEmployee();
            Employee lowestPaid = service.findLowestPaidEmployee();

            System.out.println("\n📊 SALARY STATISTICS:");
            System.out.println(SEPARATOR);
            System.out.println("Total Employees: " + service.getTotalEmployeeCount());
            System.out.println("Total Salary Expenditure: ₹" + String.format("%.2f", totalSalary));
            System.out.println("Average Salary: ₹" + String.format("%.2f", avgSalary));
            System.out.println();
            System.out.println("Highest Paid Employee: " + highestPaid.getName() + " (₹" + 
                              String.format("%.2f", highestPaid.getSalary()) + ")");
            System.out.println("Lowest Paid Employee: " + lowestPaid.getName() + " (₹" + 
                              String.format("%.2f", lowestPaid.getSalary()) + ")");

            System.out.println("\n📁 DEPARTMENT-WISE BREAKDOWN:");
            System.out.println(SEPARATOR);
            ArrayList<String> departments = service.getAllDepartments();
            for (String dept : departments) {
                double[] stats = service.getDepartmentSalaryStats(dept);
                System.out.println("\n" + dept + ":");
                System.out.println("  Employees: " + (int)stats[0]);
                System.out.println("  Total Salary: ₹" + String.format("%.2f", stats[1]));
                System.out.println("  Average Salary: ₹" + String.format("%.2f", stats[2]));
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // ==================== UTILITY METHODS ====================

    /**
     * Get valid integer input from user
     * 
     * @return Valid integer
     */
    private static int getValidInt() {
        try {
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                throw new IllegalArgumentException("Please enter a valid integer!");
            }
            int value = scanner.nextInt();
            scanner.nextLine();
            return value;
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
            return -1;
        }
    }

    /**
     * Get valid double input from user
     * 
     * @return Valid double
     */
    private static double getValidDouble() {
        try {
            if (!scanner.hasNextDouble()) {
                scanner.nextLine();
                throw new IllegalArgumentException("Please enter a valid number!");
            }
            double value = scanner.nextDouble();
            scanner.nextLine();
            return value;
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
            return -1;
        }
    }

    /**
     * Display exit message
     */
    private static void displayExitMessage() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                               ║");
        System.out.println("║  Thank you for using Employee Management System!              ║");
        System.out.println("║                   Goodbye! 👋                                  ║");
        System.out.println("║                                                               ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        System.out.println();
    }
}
