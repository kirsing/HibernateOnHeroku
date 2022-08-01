package com.example.hiberdeployed.controller;


import com.example.hiberdeployed.model.Day;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/education")
@AllArgsConstructor

public class EducationController {
        StudentService studentService;
        UniversityService universityService;


//        StudentRepository studentRepository;

        @Operation(summary = "Get all students")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Found the student",
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = Student.class))}),
                @ApiResponse(responseCode = "400", description = "Invalid supplied",
                        content = @Content),
                @ApiResponse(responseCode = "404", description = "Student not found",
                        content = @Content)})
        @GetMapping("/students")
        public List<Student> getAllStudents() {
                return studentService.getAllStudents();
        }

        @PostMapping("/students")
        public String createStudent(@Valid @RequestBody Student student) {
                studentService.createStudent(student);
                return "You created a student";
        }

        @GetMapping("/universities")
        public List<University> getAllUniversities() {
                return universityService.getAllUniversities();
        }

        @PostMapping("/universities")
        public String createUniversity(@Valid @RequestBody University university) {
                universityService.createUniversity(university);
                return "You created a university";
        }

        @DeleteMapping("/students/{studentId}")
        public void deleteStudentById(@PathVariable int studentId) {
                studentService.deleteStudentById(studentId);
        }

        @DeleteMapping("/universities/{universityId}")
        public void deleteUniversityById(@PathVariable int universityId) {
                universityService.deleteUniversityById(universityId);
        }

        @Operation(summary = "Get a student by ID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Found the student",
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = Student.class))}),
                @ApiResponse(responseCode = "400", description = "Invalid supplied",
                        content = @Content),
                @ApiResponse(responseCode = "404", description = "Student not found",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class)))})
        @GetMapping("/students/{studentId}")
        public ResponseEntity<Student> getStudentById(@PathVariable int studentId) {
               return studentService.getStudentById(studentId);
        }


//        @GetMapping("/criteria/names")
//        public List<String> getCriteriaStudentsNames() {
//                return studentService.getCriteriaStudentNames();
//        }

        @GetMapping("/criteria/sumofdepartments")
        public Short getSumOfDepartments() {
                return universityService.getSumOfDepartments();
        }

        @PutMapping("/criteria/{studentId}")
        public String updateStudent(@PathVariable int studentId, @RequestBody Student student) {
                studentService.updateStudent(student, studentId);
                return "Updated";
        }

        @GetMapping("/enum")
        public Student getEnums(@RequestParam("day1") Day day1, @RequestParam("day2") Day day2) {
                Student student = new Student();
                student.setDay1(day1);
                student.setDay2(day2);
                return studentService.createStudent(student);
        }
  @GetMapping("/sort")
      public List<String> getSorted() {
              return studentService.getCriteriaStudentNames();
       }

}
