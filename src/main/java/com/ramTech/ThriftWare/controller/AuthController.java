package com.ramTech.ThriftWare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ramTech.ThriftWare.models.User;
import com.ramTech.ThriftWare.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String hello() {
        return String.format("Hello world from Java Spring Boot!");
    }

    @PutMapping("sign-in")
    public ResponseEntity<Object> signIn(@RequestBody User loginRequest) {
        return authService.verifyUser(loginRequest);
    }

    @PostMapping("/create-user")
    public ResponseEntity<Object> createUser(@RequestBody User createUserRequest) {
        return authService.addUser(createUserRequest);
    }

}
