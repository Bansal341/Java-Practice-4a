package com.example.sm.service.impl;

import com.example.sm.dao.StudentDAO;
import com.example.sm.model.Student;
import com.example.sm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDAO studentDAO;

    @Override
    @Transactional
    public Long addStudent(Student s) {
        return studentDAO.save(s);
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudent(Long id) {
        return studentDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> listAll() {
        return studentDAO.findAll();
    }

    @Override
    @Transactional
    public void updateStudent(Student s) {
        studentDAO.update(s);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        Student s = studentDAO.findById(id);
        if (s != null) studentDAO.delete(s);
    }
}
