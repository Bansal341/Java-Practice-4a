package com.nimbus.studentmgmt.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String name;
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Student() {}
    public Student(String name, Course course, Double balance) {
        this.name = name;
        this.course = course;
        this.balance = balance;
    }

    // getters and setters
    public Long getStudentId() { return studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
