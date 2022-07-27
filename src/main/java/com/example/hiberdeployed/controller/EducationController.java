package com.example.hiberdeployed.controller;


import com.example.hiberdeployed.model.Student;
import com.example.hiberdeployed.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
@AllArgsConstructor
public class EducationController {
        StudentService studentService;

        @GetMapping("/students")
        public List<Student> getAllStudents() {
              return  studentService.getAllStudents();
        }
        @PostMapping("/students/create")
        public String createStudent(@RequestBody Student student) {
                studentService.createStudent(student);
                return "You created a student";
        }


}
