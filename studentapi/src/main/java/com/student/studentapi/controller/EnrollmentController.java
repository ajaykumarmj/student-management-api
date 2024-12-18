package com.student.studentapi.controller;

import com.student.studentapi.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public String enrollStudentInSubject(@RequestParam String studentId, @RequestParam Long subjectId) {
        return enrollmentService.enrollStudentInSubject(studentId, subjectId);
    }
}
