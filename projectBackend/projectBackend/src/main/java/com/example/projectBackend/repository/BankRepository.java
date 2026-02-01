package com.example.projectBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projectBackend.entity.RegisteredUsers;

public interface BankRepository extends JpaRepository<RegisteredUsers, Long> {

    Optional<RegisteredUsers> findByEmail(String email);

}
