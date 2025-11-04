package com.example.sm.service;
import com.example.sm.model.Student;
import java.util.List;

public interface StudentService {
    Long addStudent(Student s);
    Student getStudent(Long id);
    List<Student> listAll();
    void updateStudent(Student s);
    void deleteStudent(Long id);
}
