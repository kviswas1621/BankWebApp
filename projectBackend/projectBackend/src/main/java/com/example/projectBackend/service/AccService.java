package com.example.projectBackend.service;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.projectBackend.entity.Account;
import com.example.projectBackend.entity.RegisteredUsers;
import com.example.projectBackend.repository.AccountRepository;
import com.example.projectBackend.repository.BankRepository;

@Service
public class AccService {

    @Autowired
    AccountRepository accrepo;

    @Autowired
    BankRepository repo;

    public Optional<Account> getAccountDetails(Long accNo){
        return accrepo.findById(accNo);
    }

    public Account createBankAccount(Long ssnId, Account acc) {

        RegisteredUsers user = repo.findById(ssnId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        
        List<Account> userAccounts = accrepo.findAllByUser_SsnID(ssnId);

         // Same user → Aadhaar & PAN must match first account
        if (!userAccounts.isEmpty()) {
            Account first = userAccounts.get(0);

            if (!first.getAadhaar().equals(acc.getAadhaar())) {
                throw new IllegalArgumentException(
                        "Aadhaar must match existing Aadhaar for this user");
            }

            if (!first.getPanNo().equals(acc.getPanNo())) {
                throw new IllegalArgumentException(
                        "PAN must match existing PAN for this user");
            }
        }

        //  Aadhaar cannot belong to another user
        for (Account a : accrepo.findAllByAadhaar(acc.getAadhaar())) {
            if (!a.getUser().getSsnID().equals(ssnId)) {
                throw new IllegalArgumentException(
                        "Aadhaar already linked to another user");
            }
        }

        //  PAN cannot belong to another user
        for (Account a : accrepo.findAllByPanNo(acc.getPanNo())) {
            if (!a.getUser().getSsnID().equals(ssnId)) {
                throw new IllegalArgumentException(
                        "PAN already linked to another user");
            }
        }

        acc.setUser(user);
        return accrepo.save(acc);
    }

}

    

