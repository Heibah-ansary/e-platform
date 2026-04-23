package com.employeemgmt.service;

import com.employeemgmt.model.Employee;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Employee Service Class
 * Contains all business logic for employee management operations
 * Implements CRUD operations, sorting, filtering, and calculations
 * 
 * @author Employee Management System
 * @version 1.0
 */
public class EmployeeService {
    private ArrayList<Employee> employees;
    private static int employeeCounter = 1000;

    /**
     * Constructor to initialize the employee list
     */
    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    // ==================== CREATE OPERATION ====================

    /**
     * Add a new employee to the system
     * Validates input before adding
     * 
     * @param name Employee name
     * @param department Employee department
     * @param designation Employee designation
     * @param salary Employee salary
     * @param email Employee email
     * @param phoneNumber Employee phone number
     * @return true if employee added successfully
     * @throws IllegalArgumentException if any field is invalid
     */
    public boolean addEmployee(String name, String department, String designation, 
                              double salary, String email, String phoneNumber) {
        // Input Validation
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ Error: Name cannot be empty!");
        }
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ Error: Department cannot be empty!");
        }
        if (designation == null || designation.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ Error: Designation cannot be empty!");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("❌ Error: Salary cannot be negative!");
        }
        if (email == null || !isValidEmail(email)) {
            throw new IllegalArgumentException("❌ Error: Invalid email format!");
        }
        if (phoneNumber == null || !isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("❌ Error: Invalid phone number format! (Should be 10 digits)");
        }

        int employeeId = ++employeeCounter;
        Employee employee = new Employee(employeeId, name, department, designation, salary, email, phoneNumber);
        employees.add(employee);
        return true;
    }

    // ==================== READ OPERATIONS ====================

    /**
     * Get all employees
     * 
     * @return ArrayList of all employees
     */
    public ArrayList<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    /**
     * Search employee by ID
     * 
     * @param employeeId ID to search
     * @return Employee object if found, null otherwise
     */
    public Employee searchEmployeeById(int employeeId) {
        for (Employee emp : employees) {
            if (emp.getEmployeeId() == employeeId) {
                return emp;
            }
        }
        return null;
    }

    /**
     * Search employees by name (case-insensitive)
     * 
     * @param name Name to search
     * @return List of matching employees
     */
    public ArrayList<Employee> searchEmployeeByName(String name) {
        return employees.stream()
                .filter(emp -> emp.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Get total count of employees
     * 
     * @return Count of employees
     */
    public int getTotalEmployeeCount() {
        return employees.size();
    }

    // ==================== UPDATE OPERATION ====================

    /**
     * Update employee details
     * 
     * @param employeeId ID of employee to update
     * @param name New name
     * @param department New department
     * @param designation New designation
     * @param salary New salary
     * @param email New email
     * @param phoneNumber New phone number
     * @return true if update successful, false if employee not found
     * @throws IllegalArgumentException if any field is invalid
     */
    public boolean updateEmployee(int employeeId, String name, String department, 
                                 String designation, double salary, String email, String phoneNumber) {
        Employee employee = searchEmployeeById(employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("❌ Error: Employee with ID " + employeeId + " not found!");
        }

        // Validate inputs
        if (name != null && !name.trim().isEmpty()) {
            employee.setName(name);
        }
        if (department != null && !department.trim().isEmpty()) {
            employee.setDepartment(department);
        }
        if (designation != null && !designation.trim().isEmpty()) {
            employee.setDesignation(designation);
        }
        if (salary >= 0) {
            employee.setSalary(salary);
        }
        if (email != null && isValidEmail(email)) {
            employee.setEmail(email);
        }
        if (phoneNumber != null && isValidPhoneNumber(phoneNumber)) {
            employee.setPhoneNumber(phoneNumber);
        }
        return true;
    }

    // ==================== DELETE OPERATION ====================

    /**
     * Delete employee by ID
     * 
     * @param employeeId ID of employee to delete
     * @return true if deletion successful, false if employee not found
     */
    public boolean deleteEmployee(int employeeId) {
        Employee employee = searchEmployeeById(employeeId);
        if (employee != null) {
            employees.remove(employee);
            return true;
        }
        return false;
    }

    // ==================== SORTING OPERATIONS ====================

    /**
     * Sort employees by salary in descending order
     * 
     * @return Sorted list of employees by salary
     */
    public ArrayList<Employee> sortBySalary() {
        ArrayList<Employee> sorted = new ArrayList<>(employees);
        sorted.sort((emp1, emp2) -> Double.compare(emp2.getSalary(), emp1.getSalary()));
        return sorted;
    }

    /**
     * Sort employees by name in ascending order
     * 
     * @return Sorted list of employees by name
     */
    public ArrayList<Employee> sortByName() {
        ArrayList<Employee> sorted = new ArrayList<>(employees);
        sorted.sort((emp1, emp2) -> emp1.getName().compareToIgnoreCase(emp2.getName()));
        return sorted;
    }

    /**
     * Sort employees by department and then by name
     * 
     * @return Sorted list of employees
     */
    public ArrayList<Employee> sortByDepartment() {
        ArrayList<Employee> sorted = new ArrayList<>(employees);
        sorted.sort((emp1, emp2) -> {
            int deptComparison = emp1.getDepartment().compareToIgnoreCase(emp2.getDepartment());
            if (deptComparison != 0) {
                return deptComparison;
            }
            return emp1.getName().compareToIgnoreCase(emp2.getName());
        });
        return sorted;
    }

    // ==================== FILTERING OPERATIONS ====================

    /**
     * Filter employees by department
     * 
     * @param department Department to filter by
     * @return List of employees in the specified department
     */
    public ArrayList<Employee> filterByDepartment(String department) {
        return employees.stream()
                .filter(emp -> emp.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filter employees by salary range
     * 
     * @param minSalary Minimum salary
     * @param maxSalary Maximum salary
     * @return List of employees within salary range
     */
    public ArrayList<Employee> filterBySalaryRange(double minSalary, double maxSalary) {
        return employees.stream()
                .filter(emp -> emp.getSalary() >= minSalary && emp.getSalary() <= maxSalary)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Get all unique departments
     * 
     * @return List of unique departments
     */
    public ArrayList<String> getAllDepartments() {
        return employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // ==================== CALCULATION OPERATIONS ====================

    /**
     * Calculate total salary expenditure
     * 
     * @return Total salary of all employees
     */
    public double calculateTotalSalaryExpenditure() {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    /**
     * Calculate average salary
     * 
     * @return Average salary of all employees
     */
    public double calculateAverageSalary() {
        if (employees.isEmpty()) {
            return 0;
        }
        return calculateTotalSalaryExpenditure() / employees.size();
    }

    /**
     * Find the highest-paid employee
     * 
     * @return Highest-paid employee, null if no employees
     */
    public Employee findHighestPaidEmployee() {
        return employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    /**
     * Find the lowest-paid employee
     * 
     * @return Lowest-paid employee, null if no employees
     */
    public Employee findLowestPaidEmployee() {
        return employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    /**
     * Get salary statistics for a department
     * 
     * @param department Department name
     * @return Array of [count, total, average, max, min]
     */
    public double[] getDepartmentSalaryStats(String department) {
        ArrayList<Employee> deptEmployees = filterByDepartment(department);
        if (deptEmployees.isEmpty()) {
            return new double[]{0, 0, 0, 0, 0};
        }

        double total = deptEmployees.stream().mapToDouble(Employee::getSalary).sum();
        double average = total / deptEmployees.size();
        double max = deptEmployees.stream().mapToDouble(Employee::getSalary).max().orElse(0);
        double min = deptEmployees.stream().mapToDouble(Employee::getSalary).min().orElse(0);

        return new double[]{deptEmployees.size(), total, average, max, min};
    }

    // ==================== VALIDATION METHODS ====================

    /**
     * Validate email format
     * 
     * @param email Email to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    /**
     * Validate phone number (10 digits)
     * 
     * @param phoneNumber Phone number to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    /**
     * Check if employee ID already exists
     * 
     * @param employeeId ID to check
     * @return true if exists, false otherwise
     */
    public boolean employeeIdExists(int employeeId) {
        return searchEmployeeById(employeeId) != null;
    }

    /**
     * Check if the employee list is empty
     * 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return employees.isEmpty();
    }

    /**
     * Clear all employees (use with caution)
     */
    public void clearAllEmployees() {
        employees.clear();
    }
}
