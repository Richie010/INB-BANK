package com.mind.entity;


import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="Cheque")
public class Cheque {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cheque_id")
    private Long chequeId;
	
	@Column(name="savings_account_id")
    private Long savingsAccountId;
	
	@Column(name="current_account_id")
    private Long currentAccountId;
	
	@Column(name="name")
    private String name;
	
	@Column(name="amount")
    private double amount;

    @Column(name="status")
    private String status = "Not received";

    @Temporal(TemporalType.DATE)
    @Column(name="submission_date")
    private Date submissionDate = new Date();
    
    public Cheque() {
		// TODO Auto-generated constructor stub
	}

    
	public Cheque(Long chequeId, Long savingsAccountId, Long currentAccountId, String name, Double amount,
			String status, Date submissionDate) {
		super();
		this.chequeId = chequeId;
		this.savingsAccountId = savingsAccountId;
		this.currentAccountId = currentAccountId;
		this.name = name;
		this.amount = amount;
		this.status = status;
		this.submissionDate = submissionDate;
	}


	@Override
	public String toString() {
		return "Cheque [chequeId=" + chequeId + ", savingsAccountId=" + savingsAccountId + ", currentAccountId="
				+ currentAccountId + ", name=" + name + ", amount=" + amount + ", status=" + status
				+ ", submissionDate=" + submissionDate + "]";
	}

	public Long getChequeId() {
		return chequeId;
	}

	public void setChequeId(Long chequeId) {
		this.chequeId = chequeId;
	}

	public Long getSavingsAccountId() {
		return savingsAccountId;
	}

	public void setSavingsAccountId(Long savingsAccountId) {
		this.savingsAccountId = savingsAccountId;
	}

	public Long getCurrentAccountId() {
		return currentAccountId;
	}

	public void setCurrentAccountId(Long currentAccountId) {
		this.currentAccountId = currentAccountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
    
    

}
