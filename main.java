import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String id;
    private String name;
    private Map<String, Double> grades;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.grades = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addGrade(String subject, double grade) {
        if (grade >= 0 && grade <= 100) {
            grades.put(subject, grade);
        } else {
            System.out.println("Invalid grade. Must be between 0 and 100.");
        }
    }

    public double calculateAverage() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0.0;
        for (double grade : grades.values()) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public void displayGrades() {
        System.out.println("\nStudent: " + name + " (ID: " + id + ")");
        if (grades.isEmpty()) {
            System.out.println("No grades recorded.");
        } else {
            for (Map.Entry<String, Double> entry : grades.entrySet()) {
                System.out.println("Subject: " + entry.getKey() + ", Grade: " + entry.getValue());
            }
            System.out.printf("Average Grade: %.2f\n", calculateAverage());
        }
    }
}

class GradeManagementSystem {
    private ArrayList<Student> students;
    private Scanner scanner;

    public GradeManagementSystem() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        if (findStudent(id) == null) {
            students.add(new Student(id, name));
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student ID already exists.");
        }
    }

    public void addGrade() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        Student student = findStudent(id);

        if (student != null) {
            System.out.print("Enter subject: ");
            String subject = scanner.nextLine();
            System.out.print("Enter grade (0-100): ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); // Clear buffer
            student.addGrade(subject, grade);
            System.out.println("Grade added successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void displayAllRecords() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
        } else {
            for (Student student : students) {
                student.displayGrades();
            }
        }
    }

    private Student findStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void run() {
        while (true) {
            System.out.println("\nStudent Grade Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Display All Records");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
            } catch (Exception e) {
                scanner.nextLine(); // Clear invalid input
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGrade();
                    break;
                case 3:
                    displayAllRecords();
                    break;
                case 4:
                    System.out.println("Exiting system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        GradeManagementSystem system = new GradeManagementSystem();
        system.run();
    }
}
