package com.example.projectBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.projectBackend.dto.AmountRequest;
import com.example.projectBackend.dto.TransactionResponse;
import com.example.projectBackend.service.TransactionService;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService txnService;

    @PostMapping("/deposit/{accNo}")
    public TransactionResponse deposit(
            @PathVariable Long accNo,
            @RequestBody AmountRequest req) {

        return txnService.deposit(accNo, req.getAmount());
    }

    @PostMapping("/withdraw/{accNo}")
    public TransactionResponse withdraw(
            @PathVariable Long accNo,
            @RequestBody AmountRequest req) {

        return txnService.withdraw(accNo, req.getAmount());
    }

    @GetMapping("/balance/{accNo}")
    public Long getBalance(@PathVariable Long accNo) {
        return txnService.getBalance(accNo);
    }
}
