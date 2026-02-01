package com.example.projectBackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.projectBackend.entity.Account;
import com.example.projectBackend.entity.RegisteredUsers;
import com.example.projectBackend.repository.AccountRepository;
import com.example.projectBackend.repository.BankRepository;

@Service
public class UserService {
    
    @Autowired
    private BankRepository repo;
    
    @Autowired
    private AccountRepository accrepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    

    // Register user
   
    public RegisteredUsers addUser(RegisteredUsers user) {

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return repo.save(user);

    }



    public Optional<RegisteredUsers> findById(Long ssnId) {
        return repo.findById(ssnId);
    }

    


}
