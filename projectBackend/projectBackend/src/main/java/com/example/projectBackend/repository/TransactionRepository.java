package com.example.projectBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projectBackend.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
