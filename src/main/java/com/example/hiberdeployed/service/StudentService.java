package com.example.hiberdeployed.service;

import com.example.hiberdeployed.model.Student;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@AllArgsConstructor
public class StudentService {
    SessionFactory sessionFactory;


    @Transactional
    public Student createStudent(Student student) {
        sessionFactory.getCurrentSession().persist(student);
        return student;
    }

    @Transactional
    public List<Student> getAllStudents() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Student", Student.class)
                .getResultList();
    }
}
