package com.mind.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Savingfixeddeposit")
public class FixedDeposit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FixedDeposi_Id")
	private int depositId;
	@Column(name="account_Id")
	private int accountId;
	@Column(name="DEPOSITAMOUNT")
	private double depositAmount;
	private int duration ;
	@Column(name="INTERESTRATE")
	private double interestRate;
	public int getDepositId() {
		return depositId;
	}
	public void setDepositId(int depositId) {
		this.depositId = depositId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	

	public FixedDeposit(int depositId, int accountId, double depositAmount, int duration, double interestRate) {
		super();
		this.depositId = depositId;
		this.accountId = accountId;
		this.depositAmount = depositAmount;
		this.duration = duration;
		this.interestRate = interestRate;
	}
	public FixedDeposit() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
