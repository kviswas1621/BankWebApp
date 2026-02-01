package com.example.projectBackend.entity;

import java.time.LocalDate;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc")
    @SequenceGenerator(
            name = "acc",
            sequenceName = "acc",
            initialValue = 100000,
            allocationSize = 1
    )
    private Long accNo;
    
    @NotNull(message = "User is required")// look into this later
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ssnID",nullable = false)
    private RegisteredUsers user;
    
    @NotBlank(message = "Aadhaar number is required")
    @Pattern(regexp = "^[0-9]{12}$", message = "Aadhaar must be exactly 12 digits")
    @Column(nullable = false)
    private String aadhaar;



 

    @NotBlank(message = "PAN number is required")
    @Pattern(
        regexp = "[A-Z]{5}[0-9]{4}[A-Z]",
        message = "Invalid PAN format. Example: ABCDE1234F"
    )
    @Column(nullable = false)
    private String panNo;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in past")
    private LocalDate dob;

    @NotBlank(message = "Gender is required")
    @Column(nullable = false)
    private String gender;


    private String ifsc="TSC0012";
    
    @NotBlank(message = "Marital status is required")
    private String maritalStatus;

    @NotNull(message = "Initial balance is required")
    @Min(value = 0, message = "Balance cannot be negative")
    private Long accBalance;
   

    public Account(){
    }

    
    public Long getAccNo() {
        return accNo;
    }

    public void setAccNo(Long accNo) {
        this.accNo = accNo;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
       this.aadhaar = aadhaar;
    }

    public RegisteredUsers getUser() {
        return user;
    }

    public void setUser(RegisteredUsers user) {
        this.user = user;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    // public String getIfsc() {
    //     return ifsc;
    // }

    // public void setIfsc(String ifsc) {
    //     this.ifsc = ifsc;
    // }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Long getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(Long accBalance) {
        this.accBalance = accBalance;
    }



    
}


