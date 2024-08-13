package com.mind.dto;

import java.util.List;

public class MultiAccountRequest {
    private Long userId;
    private List<AccountRequest> accounts;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<AccountRequest> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountRequest> accounts) {
        this.accounts = accounts;
    }
}

