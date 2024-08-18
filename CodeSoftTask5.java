/*                         CODSOFT TASK 5 ***STUDENT MANAGEMENT SYSTEM***
__________________________________________________________________________________________________
1.Create a Student class to represent individual students. Include attributes such as name, roll
number, grade, and any other relevant details.
2.Implement a StudentManagementSystem class to manage the collection of students. Include
methods to add a student, remove a student, search for a student, and display all students.
3.Design the user interface for the Student Management System. This can be a console-based
interface or a graphical user interface (GUI) using libraries like Swing or JavaFX.
4. Implement methods to read and write student data to a storage medium, such as a file or a
database.
5.Allow users to interact with the Student Management System by providing options such as
adding a new student, editing an existing student's information, searching for a student, displaying all
students, and exiting the application.
6.Implement input validation to ensure that required fields are not left empty and that the student
data is in the correct format */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeSoftTask5 {

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter roll number: ");
                    String rollNumber = scanner.nextLine();

                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();

                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();

                    Student newStudent = new Student(name, rollNumber, grade, age, address);
                    sms.addStudent(newStudent);
                    break;

                case 2:
                    System.out.print("Enter roll number to remove: ");
                    String rollNumberToRemove = scanner.nextLine();
                    sms.removeStudent(rollNumberToRemove);
                    break;

                case 3:
                    System.out.print("Enter roll number to search: ");
                    String rollNumberToSearch = scanner.nextLine();
                    Student student = sms.searchStudent(rollNumberToSearch);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    sms.displayAllStudents();
                    break;

                case 5:
                    System.out.print("Enter roll number to edit: ");
                    String rollNumberToEdit = scanner.nextLine();
                    sms.editStudent(rollNumberToEdit);
                    break;

                case 6:
                    exit = true;
                    System.out.println("Exiting the application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}

class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;
    private int age;
    private String address;

    public Student(String name, String rollNumber, String grade, int age, String address) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Name='" + name + '\'' +
                ", Roll Number='" + rollNumber + '\'' +
                ", Grade='" + grade + '\'' +
                ", Age=" + age +
                ", Address='" + address + '\'' +
                '}';
    }
}

class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadStudents();
    }

    public void addStudent(Student student) {
        if (getStudentByRollNumber(student.getRollNumber()) != null) {
            System.out.println("A student with this roll number already exists.");
            return;
        }
        students.add(student);
        saveStudents();
        System.out.println("Student added successfully.");
    }

    public void removeStudent(String rollNumber) {
        Student student = getStudentByRollNumber(rollNumber);
        if (student != null) {
            students.remove(student);
            saveStudents();
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public Student searchStudent(String rollNumber) {
        return getStudentByRollNumber(rollNumber);
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private Student getStudentByRollNumber(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equalsIgnoreCase(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    private void saveStudents() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    private void loadStudents() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) in.readObject();
        } catch (FileNotFoundException e) {
            // File not found, proceed with an empty list
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }

    public void editStudent(String rollNumber) {
        Student student = getStudentByRollNumber(rollNumber);
        if (student != null) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter new name (current: " + student.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                student.setName(name);
            }

            System.out.print("Enter new grade (current: " + student.getGrade() + "): ");
            String grade = scanner.nextLine();
            if (!grade.isEmpty()) {
                student.setGrade(grade);
            }

            System.out.print("Enter new age (current: " + student.getAge() + "): ");
            int age = scanner.nextInt();
            scanner.nextLine();
            if (age > 0) {
                student.setAge(age);
            }

            System.out.print("Enter new address (current: " + student.getAddress() + "): ");
            String address = scanner.nextLine();
            if (!address.isEmpty()) {
                student.setAddress(address);
            }

            saveStudents();
            System.out.println("Student information updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }
}
