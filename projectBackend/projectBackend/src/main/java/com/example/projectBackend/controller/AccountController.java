package com.example.projectBackend.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.projectBackend.entity.Account;
import com.example.projectBackend.entity.RegisteredUsers;
import com.example.projectBackend.service.AccService;
import com.example.projectBackend.service.UserService;

import jakarta.validation.Valid;

@RestController
public class AccountController {
    
    @Autowired
    UserService ser;
    
    @Autowired
    AccService accser;
    

    @PostMapping("/create/{ssnId}")
    public ResponseEntity<Account> createAccount(@Valid
            @PathVariable Long ssnId,
            @RequestBody Account acc) {

        Account created = accser.createBankAccount(ssnId, acc);
        return ResponseEntity.ok(created);
    }

 
    // //home dashboard
    // @GetMapping("/home/{accNo}")
    // public ResponseEntity<RegisteredUsers> getAccountDetails(@PathVariable Long accNo) {
    //     return ser.findById(accNo)
    //             .map(ResponseEntity::ok)
    //             .orElse(ResponseEntity.notFound().build());
    // }

//     redirect home home after this in frontend itself
        @GetMapping("/home/{accNo}")
        public ResponseEntity<Account> getHomeDetails(@PathVariable Long accNo) {

           return accser.getAccountDetails(accNo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        }

}
