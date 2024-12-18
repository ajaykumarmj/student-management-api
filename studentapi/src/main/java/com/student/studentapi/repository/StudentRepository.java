package com.student.studentapi.repository;

import com.student.studentapi.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Students, String> {

    List<Students> findByFname(String fname);  // Search by first name

    List<Students> findByFnameAndLname(String fname, String lname);  // Search by full name (first + last)
}
