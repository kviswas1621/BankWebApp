package com.example.projectBackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectBackend.entity.Account;
import com.example.projectBackend.entity.RegisteredUsers;


public interface AccountRepository extends JpaRepository<Account, Long> {
    
    // List<Account> findByAadhaar(String aadhaar);

    // List<Account> findByPanNo(String panNo);

    Optional<Account> findFirstByUser(RegisteredUsers user);
    
    // List<Account> findAllByAadhaar(String aadhaar);

    // List<Account> findAllByPanNo(String panNo);

    List<Account> findAllByUser_SsnID(Long ssnID);

    List<Account> findAllByAadhaar(String aadhaar);

    List<Account> findAllByPanNo(String panNo);
}




