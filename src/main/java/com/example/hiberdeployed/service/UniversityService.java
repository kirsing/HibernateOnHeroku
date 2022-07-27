package com.example.hiberdeployed.service;


import com.example.hiberdeployed.model.Student;
import com.example.hiberdeployed.model.University;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Service
//@AllArgsConstructor
//public class UniversityService {
//    SessionFactory sessionFactory;
//
//
//    @Transactional
//    public University createUniversity(University university) {
//        sessionFactory.getCurrentSession().persist(university);
//        return university;
//    }
//
//    @Transactional
//    public List<University> getAllUniversities() {
//        return sessionFactory.getCurrentSession()
//                .createQuery("FROM University", University.class)
//                .getResultList();
//    }
//    @Transactional
//    public University getUniversityId(int id) {
//        return sessionFactory.getCurrentSession()
//                .createQuery("FROM University u where u.id = :id", University.class)
//                .getSingleResult();
//    }
//    @Transactional
//    public void deleteUniversityById(int id) {
//        University university = getUniversityId(id);
//        sessionFactory.getCurrentSession()
//                .remove(university);
//    }
////    @Transactional
////    public void updateUniversity(University newUniversity) {
////        sessionFactory.getCurrentSession()
////                .save(newUniversity);
////    }
//}
