package com.example.projectBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.projectBackend.dto.LoginRequest;
import com.example.projectBackend.dto.LoginResponse;
// import com.example.projectBackend.entity.Account;
import com.example.projectBackend.entity.RegisteredUsers;
import com.example.projectBackend.repository.AccountRepository;
import com.example.projectBackend.repository.BankRepository;



    @Service
    public class AuthService {

        @Autowired
        private BankRepository userRepo;

        @Autowired
        private AccountRepository accRepo;

        @Autowired
        private BCryptPasswordEncoder passwordEncoder;

        public LoginResponse login(LoginRequest request) {

            RegisteredUsers user = userRepo.findByEmail(request.getEmail().trim())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.UNAUTHORIZED,
                            "Invalid credentials"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid credentials");
            }

            // ✅ DO NOT THROW 404
            return accRepo.findFirstByUser(user)
                    .map(account -> new LoginResponse(
                            account.getAccNo(),
                            user.getSsnID(),
                            true))
                    .orElse(
                            new LoginResponse(
                                    null,
                                    user.getSsnID(),
                                    false));
        }
    }

