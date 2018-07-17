package com.twu.biblioteca.accounts.core;

public class AccountBuilder {
    private String phoneNumber;
    private String address;
    private String email;
    private String name;

    public Account build() {
        Account account = new Account();
        account.setName(name);
        account.setEmail(email);
        account.setAddress(address);
        account.setPhoneNumber(phoneNumber);
        return account;
    }

    public AccountBuilder withPhoneNumber(String aPhoneNumber) {
        this.phoneNumber = aPhoneNumber;
        return this;
    }

    public AccountBuilder withAddress(String anAddress) {
        this.address = anAddress;
        return this;
    }

    public AccountBuilder withEmail(String anEmail) {
        this.email = anEmail;
        return this;
    }

    public AccountBuilder withName(String aName) {
        this.name = aName;
        return this;
    }
}
