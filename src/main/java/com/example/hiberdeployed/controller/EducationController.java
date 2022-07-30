package com.example.hiberdeployed.controller;


import com.example.hiberdeployed.model.Student;
import com.example.hiberdeployed.model.University;
import com.example.hiberdeployed.service.StudentService;
//import com.example.hiberdeployed.service.UniversityService;
import com.example.hiberdeployed.service.UniversityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
@AllArgsConstructor
public class EducationController {
        StudentService studentService;
        UniversityService universityService;

        @Operation(summary = "Get all students")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Found the student",
                        content = { @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Student.class)) }),
                @ApiResponse(responseCode = "400", description = "Invalid supplied",
                        content = @Content),
                @ApiResponse(responseCode = "404", description = "Student not found",
                        content = @Content) })
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
                return "You created a university";
        }
        @DeleteMapping("/students/delete/{studentId}")
        public void deleteStudentById(@PathVariable int studentId) {
                studentService.deleteStudentById(studentId);
        }
        @DeleteMapping("/universities/delete/{universityId}")
        public void deleteUniversityById(@PathVariable int universityId) {
                universityService.deleteUniversityById(universityId);
        }
        @GetMapping("/students/get/{studentId}")
        public Student getStudentById(@PathVariable int studentId) {
                return studentService.getStudentById(studentId);
        }


        @GetMapping("/criteria/names")
        public List<String> getCriteriaStudentsNames() {
                return studentService.getCriteriaStudentNames();
        }

        @GetMapping("/criteria/sumofdepartments")
        public Short getSumOfDepartments() {
                return universityService.getSumOfDepartments();
        }

        @PutMapping("/criteria/update/{studentId}")
        public String updateStudent(@PathVariable int studentId, @RequestBody Student student) {
                return "Updated";
        }
}
