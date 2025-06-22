import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input student details
        System.out.println("Enter student ID: ");
        String id = scanner.nextLine();

        System.out.println("Enter student name: ");
        String name = scanner.nextLine();

        System.out.println("Enter student grade: ");
        String grade = scanner.nextLine();

        // Create MVC components
        Student student = new Student(id, name, grade);
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);

        // Show student data
        controller.updateView();

        // Option to update student info
        System.out.println("\nDo you want to update the student details? (yes/no): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Enter new name: ");
            String newName = scanner.nextLine();

            System.out.println("Enter new grade: ");
            String newGrade = scanner.nextLine();

            controller.setStudentName(newName);
            controller.setStudentGrade(newGrade);

            System.out.println("\nUpdated Student Information:");
            controller.updateView();
        }

        scanner.close();
    }
}
