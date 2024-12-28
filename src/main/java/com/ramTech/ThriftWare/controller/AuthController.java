package com.ramTech.ThriftWare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ramTech.ThriftWare.Repository.UserRepository;
import com.ramTech.ThriftWare.models.User;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String hello() {
      return String.format("Hello world from Java Spring Boot!");
    }

    @PutMapping("sign-in")
    public ResponseEntity<String> signIn(@RequestBody User loginRequest) {
        try {
            User fetchedUser = userRepository.findByMailId(loginRequest.getMailId());
            if (fetchedUser == null) {
                return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
            }
            if (fetchedUser.getPassword().equals(loginRequest.getPassword())) {
                return new ResponseEntity<>("Sign-in successful.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid credentials.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred during sign-in: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody User createUserRequest) {
        try {
            userRepository.save(createUserRequest);
            return new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
