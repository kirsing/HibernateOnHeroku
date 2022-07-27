package com.example.hiberdeployed.repository;

import com.example.hiberdeployed.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
