package com.mind.entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Currenttransactions")
public class CurrentTransactions {
	



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="account_id")
	
	private long accountId;
	
	@Column(name=" transaction_date")
	@Temporal(TemporalType.DATE)
	private Date transactionDate=new Date() ;
	
	
	private double amount;
	
	
	private String description;
	@Column(name="ACCOUNTTYPE")
	private String accounType="CURRENT";
	@Column(name="TARGETACCOUNTID")
	private long TargetAccount;
	
	@Column(name="type")
	private String type = "transfer";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccounType() {
		return accounType;
	}

	public void setAccounType(String accounType) {
		this.accounType = accounType;
	}

	public long getTargetAccount() {
		return TargetAccount;
	}

	public void setTargetAccount(long targetAccount) {
		this.TargetAccount = targetAccount;
	}



	public CurrentTransactions(long accountId ,double amount, String description) {
		super();
		this.accountId = accountId;
		this.amount = amount;
		this.description = description;
		
	}
	public CurrentTransactions(long accountId ,long TargetAccount, double amount, String description) {
		super();
		this.accountId = accountId;
		this.amount = amount;
		this.description = description;
		this.TargetAccount=TargetAccount;
		
	}



	public CurrentTransactions() {
		// TODO Auto-generated constructor stub
	}
	

}
