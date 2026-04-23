package com.employeemgmt.model;

/**
 * Employee Model Class
 * Represents an employee with their details
 * 
 * @author Employee Management System
 * @version 1.0
 */
public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private String designation;
    private double salary;
    private String email;
    private String phoneNumber;

    /**
     * Constructor to initialize Employee object
     */
    public Employee(int employeeId, String name, String department, 
                   String designation, double salary, String email, String phoneNumber) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // ==================== Getters ====================

    /**
     * Get the employee ID
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Get the employee name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the employee department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Get the employee designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Get the employee salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Get the employee email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the employee phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // ==================== Setters ====================

    /**
     * Set the employee name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the employee department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Set the employee designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Set the employee salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Set the employee email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set the employee phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // ==================== toString() Method ====================

    /**
     * Override toString() to display employee details in formatted manner
     */
    @Override
    public String toString() {
        return String.format(
            "┌─────────────────────────────────────────────────────┐\n" +
            "│ Employee ID      : %-31d │\n" +
            "│ Name             : %-31s │\n" +
            "│ Department       : %-31s │\n" +
            "│ Designation      : %-31s │\n" +
            "│ Salary           : %-31.2f │\n" +
            "│ Email            : %-31s │\n" +
            "│ Phone Number     : %-31s │\n" +
            "└─────────────────────────────────────────────────────┘",
            employeeId, name, department, designation, salary, email, phoneNumber
        );
    }

    /**
     * Simple toString() for CSV-like display
     */
    public String toShortString() {
        return String.format("ID: %d | Name: %-15s | Dept: %-12s | Designation: %-15s | Salary: %.2f",
            employeeId, name, department, designation, salary);
    }
}
