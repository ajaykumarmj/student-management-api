package com.student.studentapi.service;

import com.student.studentapi.model.Students;
import com.student.studentapi.model.Subject;
import com.student.studentapi.repository.StudentRepository;
import com.student.studentapi.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public String enrollStudentInSubject(String studentId, Long subjectId) {
        Optional<Students> studentOptional = studentRepository.findById(studentId);
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);

        if (studentOptional.isPresent() && subjectOptional.isPresent()) {
            Students student = studentOptional.get();
            Subject subject = subjectOptional.get();

            student.getSubjects().add(subject);
            studentRepository.save(student);
            return "Student enrolled successfully in the subject!";
        } else {
            return "Student or Subject not found!";
        }
    }
}
