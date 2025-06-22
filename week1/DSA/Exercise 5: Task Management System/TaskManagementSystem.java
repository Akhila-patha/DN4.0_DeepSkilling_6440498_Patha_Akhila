/*
1. Understand Linked Lists:

Singly Linked List:
- Each node stores data and a reference to the next node.
- Traversal is one-directional.
- Efficient for insertion and deletion at beginning or middle.

Doubly Linked List:
- Each node has references to both previous and next nodes.
- Supports backward and forward traversal.
- Requires more memory due to extra pointer.

This program uses a singly linked list for task management.
*/

import java.util.Scanner;

class Task {
    int taskId;
    String taskName;
    String status;
    Task next;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }

    public String toString() {
        return "Task ID: " + taskId + ", Name: " + taskName + ", Status: " + status;
    }
}

public class TaskManagementSystem {
    private Task head = null;

    // Add task to end of list
    public void addTask(int id, String name, String status) {
        Task newTask = new Task(id, name, status);
        if (head == null) {
            head = newTask;
        } else {
            Task temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newTask;
        }
        System.out.println("Task added successfully.");
    }

    // Search task by ID
    public void searchTask(int id) {
        Task temp = head;
        while (temp != null) {
            if (temp.taskId == id) {
                System.out.println("Task Found: " + temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Task not found.");
    }

    // Traverse and display all tasks
    public void traverseTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("\n--- Task List ---");
        Task temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // Delete task by ID
    public void deleteTask(int id) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        if (head.taskId == id) {
            head = head.next;
            System.out.println("Task deleted successfully.");
            return;
        }

        Task current = head;
        Task previous = null;

        while (current != null && current.taskId != id) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Task not found.");
        } else {
            previous.next = current.next;
            System.out.println("Task deleted successfully.");
        }
    }

    // Print time complexity and comparison
    public void printAnalysis() {
        System.out.println("\n--- Time Complexity Analysis ---");
        System.out.println("Add Task       : O(n) [traverse to end]");
        System.out.println("Search Task    : O(n)");
        System.out.println("Traverse Tasks : O(n)");
        System.out.println("Delete Task    : O(n)");

        System.out.println("\n--- Advantages of Linked Lists Over Arrays ---");
        System.out.println("- Dynamic size: can grow or shrink as needed.");
        System.out.println("- No need to shift elements when inserting/deleting.");
        System.out.println("- Efficient for frequent insertions/deletions.");
        System.out.println("- Drawback: No direct access via index (unlike arrays).");
    }

    // Main menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManagementSystem system = new TaskManagementSystem();
        int choice;

        do {
            System.out.println("\n--- Task Management Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. Search Task");
            System.out.println("3. Traverse Tasks");
            System.out.println("4. Delete Task");
            System.out.println("5. Show Analysis");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Task ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Status (Pending/Completed): ");
                    String status = sc.nextLine();
                    system.addTask(id, name, status);
                    break;
                case 2:
                    System.out.print("Enter Task ID to search: ");
                    system.searchTask(sc.nextInt());
                    break;
                case 3:
                    system.traverseTasks();
                    break;
                case 4:
                    System.out.print("Enter Task ID to delete: ");
                    system.deleteTask(sc.nextInt());
                    break;
                case 5:
                    system.printAnalysis();
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
