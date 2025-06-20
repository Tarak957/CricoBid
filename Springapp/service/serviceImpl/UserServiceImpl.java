package com.examly.springapp.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.entity.User;
import com.examly.springapp.exception.PlayerAlreadyAssignedException;

import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.service.UserService;
@Service
public class UserServiceImpl{
//!Constructor based injection
    private UserRepo userRepo;
    private PasswordEncoder encoder;
    public UserServiceImpl(UserRepo userRepo,PasswordEncoder encoder){
        this.userRepo=userRepo;
        this.encoder=encoder;
    }

//!================== Resgistering User (Admin or Organizer) ===================
    @Override
    public User registerUser(User user) {
        if(userRepo.findByUsername(user.getUsername())!=null)
            throw new PlayerAlreadyAssignedException("User with Username "+user.getUsername()+" already exists");
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
//!================== Logging the user ===================

     @Override
    public User loginUser(User user) {
        User existingUser = userRepo.findByUsername(user.getUsername());
        if (existingUser == null) {
            throw new PlayerAlreadyAssignedException("User not found");
        }
        if (!encoder.matches(user.getPassword(),existingUser.getPassword())) {
            throw new PlayerAlreadyAssignedException("Invalid Credentials!!");
        }
        if (!existingUser.getRole().equals(user.getRole())) {
            throw new PlayerAlreadyAssignedException("Access Denied!!");
        }
        return existingUser;
    }
    
//!================== Retriving all users ===================
    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}


















package com.examly.springapp.exceptions;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
 
@ControllerAdvice
public class GlobalExceptionHandler  {
 
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> exception1(UserNotFoundException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }
 
    @ExceptionHandler(UsernameAlreadyExist.class)
    public ResponseEntity<?> exception2(UsernameAlreadyExist e){
        return ResponseEntity.status(400).body(e.getMessage());
    }
}

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
 
@ControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        Map<String, String> map = new HashMap<>();
        for(FieldError err: errors){
            map.put(err.getField(), err.getDefaultMessage());
        }
        return ResponseEntity.status(400).body(map.toString());
    }
}
 
 
 
