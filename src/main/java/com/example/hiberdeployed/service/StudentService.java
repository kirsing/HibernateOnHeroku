package com.example.hiberdeployed.service;

import com.example.hiberdeployed.model.Student;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import javax.transaction.Transactional;
import java.util.List;

@Service
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
    @Transactional
    public Student getStudentById(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Student s where s.id = :id", Student.class)
                .getSingleResult();
    }
    @Transactional
    public void deleteStudentById(int id) {
        Student student = getStudentById(id);
        sessionFactory.getCurrentSession()
                .remove(student);
    }
    @Transactional
    public void updateStudent(Student newStudent, int id) {
        sessionFactory.getCurrentSession()
                .save(newStudent);
    }
}
