package com.student.studentapi.service;

import com.student.studentapi.model.Students;

import java.util.List;

public interface StudentService {
    String createStudentDetails(Students students);

    String updateStudentDetails(String studentID, Students students);

    String deleteStudentDetails(String studentID);

    Students getStudentDetails(String studentID);

    List<Students> getAllStudentDetails();

    List<Students> getStudentDetailsByName(String studentName);
}
