package com.ishika.smartstudent.ui;

import com.ishika.smartstudent.model.Student;
import com.ishika.smartstudent.service.AdminService;

import java.util.List;
import java.util.Scanner;

/**
 * Simple console interface for admin interactions.
 * Uses AdminService to perform all business logic.
 */
public class ConsoleUI {

    private final AdminService service;
    private final Scanner sc = new Scanner(System.in);

    public ConsoleUI(AdminService service) {
        this.service = service;
    }

    /** Entry point called by Main */
    public void start() {
        if (!loginPrompt()) {
            System.out.println("Invalid credentials. Application exiting.");
            return;
        }
        int choice;
        do {
            menu();
            choice = parseIntSafe(sc.nextLine());
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> listStudents(service.viewAllStudents());
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> searchMenu();
                case 6 -> stats();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("⚠️  Invalid option, try again.");
            }
        } while (choice != 0);
    }

    /* ---------- Private helper methods ---------- */

    private boolean loginPrompt() {
        System.out.print("Username: ");
        String u = sc.nextLine().trim();
        System.out.print("Password: ");
        String p = sc.nextLine().trim();
        return service.login(u, p);
    }

    private void menu() {
        System.out.println("""
                
                ===== SmartStudent Admin Panel =====
                1. Add Student
                2. View All Students
                3. Update Student
                4. Delete Student
                5. Search / Filter
                6. Statistics
                0. Exit
                ------------------------------------
                Enter your choice:\s""");
    }

    private void addStudent() {
        System.out.println("=== Add New Student ===");
        System.out.print("Name        : "); String name = sc.nextLine();
        System.out.print("Roll No     : "); String roll = sc.nextLine();
        System.out.print("Department  : "); String dept = sc.nextLine();
        System.out.print("Email       : "); String email = sc.nextLine();
        System.out.print("Phone       : "); String phone = sc.nextLine();
        System.out.print("Marks (0-100): "); int marks = parseIntSafe(sc.nextLine());

        Student s = new Student(name, roll, dept, email, phone, marks);
        boolean ok = service.addStudent(s);
        System.out.println(ok ? "✅ Student added successfully." : "❌ Failed to add student.");
    }

    private void listStudents(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\nID  Name       Roll  Dept       Email                Phone        Mk");
        System.out.println("-----------------------------------------------------------------------");
        list.forEach(System.out::println);
    }

    private void updateStudent() {
        System.out.print("Enter Roll No of student to update: ");
        String roll = sc.nextLine();
        Student s = service.findByRoll(roll);

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Current Record: " + s);

        System.out.print("New Name (" + s.getName() + "): ");
        String name = sc.nextLine();
        if (!name.isBlank()) s.setName(name);

        System.out.print("New Department (" + s.getDepartment() + "): ");
        String dept = sc.nextLine();
        if (!dept.isBlank()) s.setDepartment(dept);

        System.out.print("New Email (" + s.getEmail() + "): ");
        String email = sc.nextLine();
        if (!email.isBlank()) s.setEmail(email);

        System.out.print("New Phone (" + s.getPhone() + "): ");
        String phone = sc.nextLine();
        if (!phone.isBlank()) s.setPhone(phone);

        System.out.print("New Marks (" + s.getMarks() + "): ");
        String mk = sc.nextLine();
        if (!mk.isBlank()) s.setMarks(parseIntSafe(mk));

        boolean ok = service.updateStudent(s);
        System.out.println(ok ? "✅ Student updated." : "❌ Update failed.");
    }

    private void deleteStudent() {
        System.out.print("Enter Roll No to delete: ");
        boolean ok = service.deleteByRoll(sc.nextLine());
        System.out.println(ok ? "✅ Student deleted." : "❌ Delete failed.");
    }

    private void searchMenu() {
        System.out.println("""
                --- Search Menu ---
                1. By Department
                2. By Marks Range
                0. Back""");
        int ch = parseIntSafe(sc.nextLine());
        switch (ch) {
            case 1 -> {
                System.out.print("Department: ");
                listStudents(service.searchByDept(sc.nextLine()));
            }
            case 2 -> {
                System.out.print("Min Marks: ");
                int min = parseIntSafe(sc.nextLine());
                System.out.print("Max Marks: ");
                int max = parseIntSafe(sc.nextLine());
                listStudents(service.searchByMarks(min, max));
            }
            default -> System.out.println("Returning to main menu.");
        }
    }

    private void stats() {
        System.out.printf("""
                --- Statistics ---
                Total Students : %d
                Highest Marks  : %d
                Lowest Marks   : %d
                
                """,
                service.totalStudents(),
                service.highestMarks(),
                service.lowestMarks());
    }

    /** Parses int safely; returns 0 if not a number */
    private int parseIntSafe(String s) {
        try { return Integer.parseInt(s.trim()); }
        catch (NumberFormatException ex) { return 0; }
    }
}
