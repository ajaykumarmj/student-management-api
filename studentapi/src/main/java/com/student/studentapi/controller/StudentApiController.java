package com.student.studentapi.controller;

import com.student.studentapi.model.Students;
import com.student.studentapi.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentdetails")
public class StudentApiController {

    @Autowired
    private StudentService studentService;

    private static final Logger logInfo = LoggerFactory.getLogger(StudentApiController.class);

    @GetMapping("/{studentID}")
    public Students getStudentDetails(@PathVariable String studentID) {
        return studentService.getStudentDetails(studentID);
    }

    @GetMapping()
    public List<Students> getAllStudentDetails() {
        return studentService.getAllStudentDetails();
    }

    @PostMapping
    public String createNewStudentDetails(@RequestBody Students students) {
        return studentService.createStudentDetails(students);
    }

    @PutMapping("/{studentID}")
    public String updateStudentDetails(@PathVariable String studentID, @RequestBody Students students) {
        return studentService.updateStudentDetails(studentID, students);
    }

    @DeleteMapping("/{studentID}")
    public String deleteStudentDetails(@PathVariable String studentID) {
        return studentService.deleteStudentDetails(studentID);
    }

    @GetMapping("/name/{studentName}")
    public List<Students> getStudentDetailsByName(@PathVariable String studentName) {
        return studentService.getStudentDetailsByName(studentName);
    }
}
