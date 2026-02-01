package com.example.projectBackend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tId")
    @SequenceGenerator(
            name = "tId",
            sequenceName = "tId",
            initialValue = 1000,
            allocationSize = 1
    )
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "acc_no")
    private Account account;

    private String transactionType;   // DEPOSIT / WITHDRAW
    private Long amount;
    private Long balanceAfterTxn;
    private String status;             // SUCCESS / FAILED
    private LocalDateTime transactionTime;

    public Transaction() {}

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getBalanceAfterTxn() {
        return balanceAfterTxn;
    }

    public void setBalanceAfterTxn(Long balanceAfterTxn) {
        this.balanceAfterTxn = balanceAfterTxn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

}


