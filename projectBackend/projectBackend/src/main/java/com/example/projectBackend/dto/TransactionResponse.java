package com.example.projectBackend.dto;

import java.time.LocalDateTime;

public class TransactionResponse {

    private String message;
    private Long balance;
    private String type;
    private LocalDateTime time;

    public TransactionResponse(String message, Long balance, String type, LocalDateTime time) {
        this.message = message;
        this.balance = balance;
        this.type = type;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public Long getBalance() {
        return balance;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
