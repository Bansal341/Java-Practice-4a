package com.nimbus.studentmgmt.dao.impl;

import com.nimbus.studentmgmt.dao.StudentDAO;
import com.nimbus.studentmgmt.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session session() { return sessionFactory.getCurrentSession(); }

    @Override
    public Long save(Student s) { return (Long) session().save(s); }

    @Override
    public Student findById(Long id) { return session().get(Student.class, id); }

    @Override
    public List<Student> findAll() {
        return session().createQuery("from Student", Student.class).list();
    }

    @Override
    public void update(Student s) { session().update(s); }

    @Override
    public void delete(Student s) { session().delete(s); }
}
