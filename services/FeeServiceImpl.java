package com.example.sm.service.impl;

import com.example.sm.dao.StudentDAO;
import com.example.sm.dao.PaymentDAO;
import com.example.sm.model.Student;
import com.example.sm.model.Payment;
import com.example.sm.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private PaymentDAO paymentDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void makePayment(Long studentId, Double amount) throws Exception {
        Student s = studentDAO.findById(studentId);
        if (s == null) throw new Exception("Student not found");
        // Deduct from student's balance (example: they pay, so balance reduces)
        if (s.getBalance() < amount) {
            throw new Exception("Insufficient balance for payment");
        }
        s.setBalance(s.getBalance() - amount);
        studentDAO.update(s);

        Payment p = new Payment(s, amount);
        paymentDAO.save(p);

        // If any further step fails, the whole transaction will roll back
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refund(Long studentId, Double amount) throws Exception {
        Student s = studentDAO.findById(studentId);
        if (s == null) throw new Exception("Student not found");

        // Add amount to student's balance
        s.setBalance(s.getBalance() + amount);
        studentDAO.update(s);

        Payment p = new Payment(s, -amount); // negative entry for refund, or create a separate refund table
        paymentDAO.save(p);

        // If anything fails, rollback
    }
}
