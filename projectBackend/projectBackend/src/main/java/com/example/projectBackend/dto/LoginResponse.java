package com.example.projectBackend.dto;


public class LoginResponse {

    private Long accNo;
    private Long ssnId;
    private boolean hasAccount;

    public LoginResponse(Long accNo, Long ssnId, boolean hasAccount) {
        this.accNo = accNo;
        this.ssnId = ssnId;
        this.hasAccount = hasAccount;
    }

    public Long getAccNo() { return accNo; }
    public Long getSsnId() { return ssnId; }
    public boolean isHasAccount() { return hasAccount; }
}
