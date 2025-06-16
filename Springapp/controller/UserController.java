package com.examly.springapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.examly.springapp.entity.User;
import com.examly.springapp.service.serviceimpl.UserServiceImpl;



@RestController
@RequestMapping("/api/user")
public class UserController {
//!Constructor based injection
    private final UserServiceImpl service;
    public UserController(UserServiceImpl service){
        this.service=service;
    }
//!================== Resgistering User (Admin or Organizer) ===================
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return ResponseEntity.status(201).body(service.registerUser(user));
    }
//!================== Logging the user ===================
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user){
        return ResponseEntity.status(201).body(service.loginUser(user));
    }
//!================== Retriving all users ===================
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.status(200).body(service.getAllUser());
    }
}
