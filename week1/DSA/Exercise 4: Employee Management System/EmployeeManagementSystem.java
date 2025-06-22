/*
Scenario:
You are developing an employee management system for a company. Efficiently managing employee records is crucial.

1. Understand Array Representation:
Arrays in Java are stored in contiguous memory locations. Each element is accessed using an index (starting from 0).
- Advantages:
  - Fast access using indices (O(1))
  - Simple and easy to use for fixed-size collections
- Limitations:
  - Fixed size (cannot grow dynamically)
  - Insertion/deletion in the middle requires shifting elements (O(n))

This example demonstrates employee management using arrays.
*/

import java.util.Scanner;

class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + employeeId + ", Name: " + name + ", Position: " + position + ", Salary: $" + salary;
    }
}

public class EmployeeManagementSystem {

    static final int MAX_EMPLOYEES = 100;
    static Employee[] employees = new Employee[MAX_EMPLOYEES];
    static int count = 0; // keeps track of how many employees are stored

    // Add an employee
    public static void addEmployee(Scanner sc) {
        if (count >= MAX_EMPLOYEES) {
            System.out.println("Employee list is full!");
            return;
        }
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // clear buffer
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Position: ");
        String position = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        employees[count++] = new Employee(id, name, position, salary);
        System.out.println("Employee added successfully.");
    }

    // Search by employee ID
    public static void searchEmployee(Scanner sc) {
        System.out.print("Enter Employee ID to search: ");
        int id = sc.nextInt();
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                System.out.println("Employee Found: " + employees[i]);
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    // Traverse all employees
    public static void listEmployees() {
        if (count == 0) {
            System.out.println("No employees in the system.");
        } else {
            System.out.println("\n--- Employee List ---");
            for (int i = 0; i < count; i++) {
                System.out.println(employees[i]);
            }
        }
    }

    // Delete an employee by ID
    public static void deleteEmployee(Scanner sc) {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                // Shift remaining elements left
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--count] = null;
                System.out.println("Employee deleted successfully.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    // Print analysis of time complexities
    public static void printAnalysis() {
        System.out.println("\n--- Time Complexity Analysis ---");
        System.out.println("Add Employee    : O(1) [at end of array]");
        System.out.println("Search Employee : O(n) [linear search]");
        System.out.println("Traverse        : O(n)");
        System.out.println("Delete Employee : O(n) [shifting elements after deletion]");

        System.out.println("\n--- Array Limitations ---");
        System.out.println("- Fixed size (not dynamic).");
        System.out.println("- Inserting or deleting in the middle is costly (O(n)).");
        System.out.println("- Better alternatives: ArrayList (dynamic), HashMap (faster search), etc.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Employee Management Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. List All Employees");
            System.out.println("4. Delete Employee");
            System.out.println("5. Show Analysis");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(sc);
                    break;
                case 2:
                    searchEmployee(sc);
                    break;
                case 3:
                    listEmployees();
                    break;
                case 4:
                    deleteEmployee(sc);
                    break;
                case 5:
                    printAnalysis();
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}
