package com.example.hiberdeployed.service;

import com.example.hiberdeployed.model.Student;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class StudentService {


    SessionFactory sessionFactory;
@PersistenceContext
    EntityManager entityManager;




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
    public ResponseEntity<Student> getStudentById(int id) {
        try {
            Optional<Student> studentOptional = Optional.ofNullable(sessionFactory.getCurrentSession()
                    .createQuery("FROM Student s where s.id = :id", Student.class)
                    .setParameter("id", id)
                    .getSingleResult());
            return new ResponseEntity(studentOptional.get(), HttpStatus.OK);
        } catch (NoResultException ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public void deleteStudentById(int id) {
        Student student = sessionFactory.getCurrentSession()
                .createQuery("FROM Student s where s.id = :id", Student.class)
                .setParameter("id", id)
                .getSingleResult();
            sessionFactory.getCurrentSession().remove(student);
    }
    @Transactional
    public void updateStudent(Student newStudent, int id) {
        Query q= sessionFactory.getCurrentSession().createQuery("update Student set firstName=:firstname, lastName=:lastname where id=:id");
        q.setParameter("firstname",newStudent.getFirstName());
        q.setParameter("lastname",newStudent.getLastName());
        q.setParameter("id",id);

        int status=q.executeUpdate();
        System.out.println(status);

    }

    public List<String> getCriteriaStudentNames() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Student> houseRoot = criteriaQuery.from(Student.class);
        criteriaQuery.select(houseRoot.get("firstName"));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


//    public List<String> getCriteriaStudentNames() {
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
//        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
//        Root<Student> studentRoot = criteriaQuery.from(Student.class);
//        criteriaQuery.select(studentRoot.get("firstName"));
//        return session.createQuery(criteriaQuery).getResultList();
//    }

//        public List<Student> findAllSQLByDayDesc() {
//        return em.createNativeQuery("SELECT * FROM student ORDER BY day1 DESC", Student.class).getResultList();
//    }

    // выборки по енамам с помощью em и jpa repo
    // в Embedeable тоже самое ( ex. студентов живущий в какой-то определенной стране)

}
