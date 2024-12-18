package com.student.studentapi.service.impl;

import com.student.studentapi.exception.StudentAlreadyExistsException;
import com.student.studentapi.exception.StudentNotFoundException;
import com.student.studentapi.model.Students;
import com.student.studentapi.repository.StudentRepository;
import com.student.studentapi.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logInfo = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentDetailsRepository;

    public StudentServiceImpl(StudentRepository studentDetailsRepository) {
        this.studentDetailsRepository = studentDetailsRepository;
    }

    @Override
    public String createStudentDetails(Students students) {
        logInfo.info("Entering createStudentDetails with studentDetails: {}", students);
        Optional<Students> existingStudent = studentDetailsRepository.findById(students.getSrn());
        if (existingStudent.isPresent()) {
            logInfo.warn("Student with ID {} already exists!", students.getSrn());
            logInfo.info("Please update the changes!");
            throw new StudentAlreadyExistsException("Student with ID " + students.getSrn() + " already exists!");
        }
        studentDetailsRepository.save(students);
        logInfo.info("Student details added successfully for ID: {}", students.getSrn());
        return "Student details added successfully";
    }

    @Override
    public String updateStudentDetails(String studentID, Students students) {
        logInfo.info("Entering updateStudentDetails with studentDetails: {}", students);
        Optional<Students> existingStudent = studentDetailsRepository.findById(students.getSrn());
        if (existingStudent.isEmpty()) {
            logInfo.error("Requested Student details do not exist for ID: {}", students.getSrn());
            throw new StudentNotFoundException("Requested Student details do not exist to update");
        }
        studentDetailsRepository.save(students);
        logInfo.info("Student details updated successfully for ID: {}", students.getSrn());
        return "Student details updated successfully!";
    }

    @Override
    public String deleteStudentDetails(String studentID) {
        logInfo.info("Entering deleteStudentDetails with studentID: {}", studentID);
        Optional<Students> existingStudent = studentDetailsRepository.findById(studentID);
        if (existingStudent.isEmpty()) {
            logInfo.error("Requested Student details do not exist for ID: {}", studentID);
            throw new StudentNotFoundException("Requested Student details do not exist to delete");
        }
        studentDetailsRepository.deleteById(studentID);
        logInfo.info("Student details deleted successfully for ID: {}", studentID);
        return "Student details deleted successfully!";
    }

    @Override
    public Students getStudentDetails(String studentID) {
        logInfo.info("Entering getStudentDetails with studentID: {}", studentID);
        Students details = studentDetailsRepository.findById(studentID)
                .orElseThrow(() -> {
                    logInfo.error("Requested Student details do not exist for ID: {}", studentID);
                    return new StudentNotFoundException("Requested Student details do not exist");
                });
        logInfo.info("Exiting getStudentDetails with details: {}", details);
        return details;
    }

    @Override
    public List<Students> getAllStudentDetails() {
        logInfo.info("Entering getAllStudentDetails");
        List<Students> studentDetails = studentDetailsRepository.findAll();
        if (studentDetails.isEmpty()) {
            logInfo.warn("No Student details found");
            throw new StudentNotFoundException("No Student details found");
        }
        logInfo.info("Exiting getAllStudentDetails with count: {}", studentDetails.size());
        return studentDetails;
    }

    @Override
    public List<Students> getStudentDetailsByName(String studentName) {
        logInfo.info("Entering getStudentDetailsByName with studentName: {}", studentName);
        List<Students> details = studentDetailsRepository.findByFname(studentName);
        if(details.isEmpty()) {
            logInfo.error("No Student found with name: {}", studentName);
            throw  new StudentNotFoundException("No Student found with name: " + studentName);
        }
        logInfo.info("Exiting getStudentDetailsByName with details: {}", details);
        return details;
    }
}
