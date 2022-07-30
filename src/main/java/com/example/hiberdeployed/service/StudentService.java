package com.example.hiberdeployed.service;

import com.example.hiberdeployed.model.Student;
import com.example.hiberdeployed.model.University;
import com.example.hiberdeployed.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    SessionFactory sessionFactory;



    @Transactional
    public Student createStudent(Student student) {
        sessionFactory.getCurrentSession().save(student); // persist -
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
                .setParameter("id", id)
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
       Student studentOld = sessionFactory.getCurrentSession()
        .get(Student.class, id);
        studentOld.setFirstName(newStudent.getFirstName());
        sessionFactory.getCurrentSession()
                .saveOrUpdate(newStudent);
    }



    public List<String> getCriteriaStudentNames() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);
        criteriaQuery.select(studentRoot.get("firstName"));
        return session.createQuery(criteriaQuery).getResultList();
    }


}
