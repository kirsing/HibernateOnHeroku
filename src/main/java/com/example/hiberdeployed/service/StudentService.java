package com.example.hiberdeployed.service;

import com.example.hiberdeployed.model.Student;
import com.example.hiberdeployed.model.University;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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


    public List<String> getCriteriaStudentNames() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);
        criteriaQuery.select(studentRoot.get("firstName"));
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public Short getSumOfDepartments() {
        CriteriaBuilder criteriaBuilder = sessionFactory.openSession()
                .getCriteriaBuilder();
        CriteriaQuery<Short> criteriaQuery = criteriaBuilder.createQuery(Short.class);
        Root<University> universityRoot = criteriaQuery.from(University.class);
        criteriaQuery.select(criteriaBuilder.sum(universityRoot.get("departments")));
        return sessionFactory.getCurrentSession()
                .createQuery(criteriaQuery)
                .getSingleResult();
    }
}
