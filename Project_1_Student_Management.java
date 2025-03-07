import java.util.ArrayList;
import java.util.Scanner;

// Student Class
class Student {
    private String name;
    private int age;
    private int rollNumber;
    private String course;

    // Constructor
    public Student(String name, int age, int rollNumber, String course) {
        this.name = name;
        this.age = age;
        this.rollNumber = rollNumber;
        this.course = course;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // toString Method for Displaying Student Details
    @Override
    public String toString() {
        return "\nRoll Number: " + rollNumber + "\nName: " + name + "\nAge: " + age + "\nCourse: " + course + "\n";
    }
}

// Main Class
public class Project_1_Student_Management {

    public static void clearScreen() {
        // ANSI escape code to clear screen and move the cursor to top-left
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void makeChoice() {

        System.out.println("\n------ Student Management System ------");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        start(choice);
    }

    public static void start(int choice) {

        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                displayAllStudents();
                break;
            case 3:
                searchStudent();
                break;
            case 4:
                updateStudent();
                break;
            case 5:
                deleteStudent();
                break;
            case 6:
                System.out.println("Exiting the system. Goodbye! \n \n");
                System.exit(0);
            default:
                System.out.println("Invalid choice! Please try again.");
                makeChoice();
        }
    }
    public static void main(String[] args) {
        clearScreen();
        makeChoice();
    }

    // 1. Add Student
    
    private static void addStudent() {

        clearScreen();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();

        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        // Check for duplicate roll number
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Error: Roll number already exists!");
                return;
            }
        }

        students.add(new Student(name, age, rollNumber, course));
        System.out.println("Student added successfully!\n");
        makeChoice();
    }

    // 2. Display All Students
    private static void displayAllStudents() {

        clearScreen();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            makeChoice();
            return;
        }

        int num = 1;
        System.out.println("\n--- List of Students ---");
        for (Student student : students) {
            System.out.println("\nDetails of student " + num + " are as follow: " +  student);
            num++;
        }
        makeChoice();
    }

    // 3. Search Student
    private static void searchStudent() {

        clearScreen();
        System.out.print("Enter Roll Number to Search: ");
        int rollNumber = scanner.nextInt();

        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Student Found: " + student);
                makeChoice();
                return;
            }
        }

        System.out.println("Student with Roll Number " + rollNumber + " not found.\n");
        makeChoice();
    }

    // 4. Update Student
    private static void updateStudent() {
        
        clearScreen();
        System.out.print("Enter Roll Number to Update: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        for (Student student : students) {

            if (student.getRollNumber() == rollNumber) {

                System.out.println("The student details with roll number " + rollNumber + " is as follow: " + student);
                System.out.print("Enter New Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter New Age: ");
                int age = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                System.out.print("Enter New Course: ");
                String course = scanner.nextLine();

                student.setName(name);
                student.setAge(age);
                student.setCourse(course);

                System.out.println("Student updated successfully!");
                makeChoice();
                return;
            }
        }

        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // 5. Delete Student
    private static void deleteStudent() {
        
        clearScreen();
        System.out.print("Enter Roll Number to Delete: ");
        int rollNumber = scanner.nextInt();

        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                students.remove(student);
                System.out.println("Student with details " + student + "deleted successfully!");
                makeChoice();
                return;
            }
        }

        System.out.println("Student with Roll Number " + rollNumber + " not found.");
        makeChoice();
    }
}
