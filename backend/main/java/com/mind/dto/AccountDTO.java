package com.mind.dto;

public class AccountDTO {
    private Long accountId;
    private Double balance;
    private String name;

    public AccountDTO(Long accountId, Double balance, String name) {
        this.accountId = accountId;
        this.balance = balance;
        this.name = name;
    }

    // Getters and setters
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

