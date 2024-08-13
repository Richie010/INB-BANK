package com.mind.entity;

import java.util.Date;

public class TransactionRequest {

   

    // Getters and Setters
private Long accountId;
	
	private String servicename;

    private Double amount;
    
    private boolean enable;
    
    private String accountType;
    
    private Date scheduledDate;

    public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    public Double getAmount() {
    	return amount;
    }
    
    public void setAmount(Double amount) {
    	this.amount = amount;
    }

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	
	
    


   
}
