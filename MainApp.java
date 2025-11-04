package com.example.sm.app;

import com.example.sm.config.AppConfig;
import com.example.sm.model.Course;
import com.example.sm.model.Student;
import com.example.sm.service.StudentService;
import com.example.sm.service.FeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        StudentService studentService = ctx.getBean(StudentService.class);
        FeeService feeService = ctx.getBean(FeeService.class);

        Scanner sc = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add student");
            System.out.println("2. List students");
            System.out.println("3. Update student");
            System.out.println("4. Delete student");
            System.out.println("5. Make payment");
            System.out.println("6. Refund");
            System.out.println("7. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(sc.nextLine());
            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Initial balance: ");
                        Double bal = Double.parseDouble(sc.nextLine());
                        // For demo: create OR find a course (simplify: null course)
                        Student s = new Student();
                        s.setName(name);
                        s.setBalance(bal);
                        Long id = studentService.addStudent(s);
                        System.out.println("Added id: " + id);
                    }
                    case 2 -> {
                        List<Student> list = studentService.listAll();
                        list.forEach(st -> System.out.println(st.getStudentId() + " | " + st.getName() + " | Balance: " + st.getBalance() + " | Course: " + (st.getCourse()!=null?st.getCourse().getCourseName():"-")));
                    }
                    case 3 -> {
                        System.out.print("Student id to update: ");
                        Long id = Long.parseLong(sc.nextLine());
                        Student st = studentService.getStudent(id);
                        if (st == null) { System.out.println("Not found"); break; }
                        System.out.print("New name (blank to skip): ");
                        String newName = sc.nextLine();
                        if (!newName.isBlank()) st.setName(newName);
                        studentService.updateStudent(st);
                        System.out.println("Updated.");
                    }
                    case 4 -> {
                        System.out.print("Student id to delete: ");
                        Long id = Long.parseLong(sc.nextLine());
                        studentService.deleteStudent(id);
                        System.out.println("Deleted.");
                    }
                    case 5 -> {
                        System.out.print("Student id: ");
                        Long id = Long.parseLong(sc.nextLine());
                        System.out.print("Amount: ");
                        Double amt = Double.parseDouble(sc.nextLine());
                        feeService.makePayment(id, amt);
                        System.out.println("Payment done.");
                    }
                    case 6 -> {
                        System.out.print("Student id: ");
                        Long id = Long.parseLong(sc.nextLine());
                        System.out.print("Refund amount: ");
                        Double amt = Double.parseDouble(sc.nextLine());
                        feeService.refund(id, amt);
                        System.out.println("Refund processed.");
                    }
                    case 7 -> run = false;
                    default -> System.out.println("Invalid");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        ctx.close();
        sc.close();
    }
}
