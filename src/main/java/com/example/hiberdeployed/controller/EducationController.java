package com.example.hiberdeployed.controller;


import com.example.hiberdeployed.model.Student;
import com.example.hiberdeployed.model.University;
import com.example.hiberdeployed.service.StudentService;
//import com.example.hiberdeployed.service.UniversityService;
import com.example.hiberdeployed.service.UniversityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
@AllArgsConstructor
public class EducationController {
        StudentService studentService;
        UniversityService universityService;

        @GetMapping("/students")
        public List<Student> getAllStudents() {
              return  studentService.getAllStudents();
        }
        @PostMapping("/students/create")
        public String createStudent(@RequestBody Student student) {
                studentService.createStudent(student);
                return "You created a student";
        }
        @GetMapping("/universities")
        public List<University> getAllUniversities() {
                return  universityService.getAllUniversities();
        }

        @PostMapping("/universities/create")
        public String createUniversity(@RequestBody University university) {
                universityService.createUniversity(university);
                return "You created a student";
        }
        @DeleteMapping("/students/delete/{studentId}")
        public void deleteStudentById(@PathVariable int studentId) {
                studentService.deleteStudentById(studentId);
        }
        @DeleteMapping("/universities/delete/{universityId}")
        public void deleteUniversityById(@PathVariable int universityId) {
                universityService.deleteUniversityById(universityId);
        }
        @GetMapping("/criteria/names")
        public List<String> getCriteriaStudentsNames() {
                return studentService.getCriteriaStudentNames();
        }

        @GetMapping("/criteria/sumofdepartments")
        public Short getSumOfDepartments() {
                return universityService.getSumOfDepartments();
        }
}
