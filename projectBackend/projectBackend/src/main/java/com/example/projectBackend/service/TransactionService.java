package com.example.projectBackend.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.projectBackend.dto.TransactionResponse;
import com.example.projectBackend.entity.Account;
import com.example.projectBackend.entity.Transaction;
import com.example.projectBackend.repository.AccountRepository;
import com.example.projectBackend.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private TransactionRepository txnRepo;

    // DEPOSIT
    public TransactionResponse deposit(Long accNo, Long amount) {

        Account account = accountRepo.findById(accNo)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Long newBalance = account.getAccBalance() + amount;
        account.setAccBalance(newBalance);
        accountRepo.save(account);

        Transaction txn = new Transaction();
        txn.setAccount(account);
        txn.setTransactionType("DEPOSIT");
        txn.setAmount(amount);
        txn.setBalanceAfterTxn(newBalance);
        txn.setStatus("SUCCESS");
        txn.setTransactionTime(LocalDateTime.now());

        txnRepo.save(txn);

        return new TransactionResponse(
                "Deposit successful",
                newBalance,
                "DEPOSIT",
                txn.getTransactionTime());
    }

    // WITHDRAW
    public TransactionResponse withdraw(Long accNo, Long amount) {

        Account account = accountRepo.findById(accNo)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getAccBalance() < amount) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Insufficient balance"
            );
        }

        Long newBalance = account.getAccBalance() - amount;
        account.setAccBalance(newBalance);
        accountRepo.save(account);

        Transaction txn = new Transaction();
        txn.setAccount(account);
        txn.setTransactionType("WITHDRAW");
        txn.setAmount(amount);
        txn.setBalanceAfterTxn(newBalance);
        txn.setStatus("SUCCESS");
        txn.setTransactionTime(LocalDateTime.now());

        txnRepo.save(txn);

        return new TransactionResponse(
                "Withdraw successful",
                newBalance,
                "WITHDRAW",
                txn.getTransactionTime());
    }

    public Long getBalance(Long accNo) {
        Account account = accountRepo.findById(accNo)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getAccBalance();
    }
}
