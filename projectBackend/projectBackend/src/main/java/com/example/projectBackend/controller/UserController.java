package com.example.projectBackend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.projectBackend.entity.RegisteredUsers;
import com.example.projectBackend.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService ser;

    @GetMapping("/name")
    public String getName(){
        return "Viswas";
    }

    @PostMapping("/add")
    public ResponseEntity<RegisteredUsers> addUser(@Valid @RequestBody RegisteredUsers user) {
        RegisteredUsers savedUser = ser.addUser(user);
        return ResponseEntity.ok(savedUser);
    }

}
