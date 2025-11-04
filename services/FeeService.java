package com.example.sm.service;
import com.example.sm.model.Student;

public interface FeeService {
    void makePayment(Long studentId, Double amount) throws Exception;
    void refund(Long studentId, Double amount) throws Exception;
}
