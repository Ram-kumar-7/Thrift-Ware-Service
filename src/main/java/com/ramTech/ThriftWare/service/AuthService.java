package com.ramTech.ThriftWare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ramTech.ThriftWare.Repository.UserRepository;
import com.ramTech.ThriftWare.Response.AuthResponse;
import com.ramTech.ThriftWare.models.User;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public ResponseEntity<Object> verifyUser(User user) {
        try {
            User fetchedUser = userRepository.findByMailId(user.getMailId());
            if (fetchedUser == null) {
                return new ResponseEntity<>(new AuthResponse(false, "Invaild Mail Id..!"), HttpStatus.NOT_FOUND);
            }
            if (fetchedUser.getPassword().equals(user.getPassword())) {
                String token = jwtService.generateToken(fetchedUser);
                return new ResponseEntity<>(new AuthResponse(true, "Sign-in successful..!", token), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new AuthResponse(false, "Invaild Password..!"), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthResponse(false, "An error occurred during sign-in: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> addUser(User user) {
        try {
            userRepository.save(user);
            return new ResponseEntity<>(new AuthResponse(false, "Invaild Password..!"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthResponse(false, "Failed to create user: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public User getUserDetails(String mailId) {
        try {
            User fetchedUser = userRepository.findByMailId(mailId);
            if (fetchedUser == null) {
                throw null;
            }
            return fetchedUser;
        } catch (Exception e) {
            throw e;
        }
    }

}
