package com.twu.biblioteca.accounts.core;

public class Account {
    private String phoneNumber;
    private String address;
    private String email;
    private String name;

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
