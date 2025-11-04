package com.nimbus.studentmgmt.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Double amount;
    private LocalDateTime date;

    public Payment() {}
    public Payment(Student student, Double amount) {
        this.student = student;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    // getters/setters
    public Long getPaymentId() { return paymentId; }
    public Student getStudent() { return student; }
    public Double getAmount() { return amount; }
    public LocalDateTime getDate() { return date; }
}
