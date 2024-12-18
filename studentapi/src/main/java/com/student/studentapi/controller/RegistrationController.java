package com.student.studentapi.controller;

import com.student.studentapi.dto.UserResponseDTO;
import com.student.studentapi.exception.StudentNotFoundException;
import com.student.studentapi.exception.MissingInputFieldException;
import com.student.studentapi.model.MyUser;
import com.student.studentapi.repository.MyUserRepository;
import com.student.studentapi.service.MyUserDetailService;
import com.student.studentapi.webtoken.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RegistrationController {

    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    MyUserDetailService myUserDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @PostMapping("/register/user")
    public UserResponseDTO createUser(@RequestBody MyUser user) {
        logger.info("Registering a user!");
        Optional<MyUser> optional = myUserRepository.findByUsername(user.getUsername());
        if (optional.isPresent()) {
            logger.warn("User already exist: {}", optional);
            throw new MissingInputFieldException("Username is already exist choose different one!");
        } else if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            logger.warn("Username or Password is missing!");
            throw new MissingInputFieldException("Username or Password is missing!");
        } else {
            logger.info("Saving user in the repository");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setAdgroup(toUpper(user.getAdgroup()));
            logger.info("Password is encoded: {}", passwordEncoder.encode(user.getPassword()));

            MyUser savedUser = myUserRepository.save(user);

            return new UserResponseDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getAdgroup());
        }
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestParam String username, String password) {
        if (username == null || password == null) {
            logger.warn("Username or Password is missing");
            throw new MissingInputFieldException("Username or Password is missing!");
        } else {

            logger.info("Authenticating user ...!");
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (authentication.isAuthenticated()) {

                logger.info("User is authenticated!");

                logger.info("Jwt token is generated!");
                return "Bearer " + jwtService.generateToken(myUserDetailService.loadUserByUsername(username));
            } else {
                logger.error("Invalid credentials!");
                throw new AuthenticationCredentialsNotFoundException("Invalid credentials");
            }
        }
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        Optional<MyUser> optional = myUserRepository.findById(id);
        if (optional.isEmpty()) {
            throw new StudentNotFoundException("User id does not exist to delete!");
        }
        myUserRepository.deleteById(id);
        return "User deleted successfully";
    }

    @GetMapping()
    public String welcomePage() {
        return "Welcome to the apprentice Details application!";
    }

    public String toUpper(String object) {
        return object.toUpperCase();
    }
}
