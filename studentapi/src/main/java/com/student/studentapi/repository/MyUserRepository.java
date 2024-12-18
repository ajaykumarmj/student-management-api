package com.student.studentapi.repository;

import com.student.studentapi.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    Optional<com.student.studentapi.model.MyUser> findByUsername(String username);

}
