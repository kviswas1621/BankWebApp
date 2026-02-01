package com.example.projectBackend.entity;

import jakarta.validation.constraints.*;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class RegisteredUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_seq")
    @SequenceGenerator(
            name = "cust_seq",
            sequenceName = "cust_seq",
            initialValue = 100000000,
            allocationSize = 1
    )
    private Long ssnID;

   @NotBlank(message = "Enter FirstName")
    private String firstName;

    @NotBlank(message = "Enter LastName")
    private String lastName;

    @NotBlank(message = "Email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid Email Format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password")
    @Size(min = 5)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank(message = "Enter Address")
    private String address;
    
    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Contact number must be a valid 10-digit Indian mobile number")
    @Column(nullable = false, unique = true)
    private String contactNumber;

    public RegisteredUsers() {}

    public Long getSsnID() {
        return ssnID;
    }

    public void setSsnID(Long ssnID) {
        this.ssnID = ssnID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
