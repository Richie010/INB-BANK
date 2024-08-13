package com.mind.entity;



import java.util.Date;

import javax.persistence.*;



@Entity
@Table(name="Bank_slips")
public class BankSlip {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bank_slip_id")
    private Long BankSlipId;
	
	@Column(name="savings_account_id")
    private int savingsAccountId;
	
	@Column(name="current_account_id")
    private Long currentAccountId;
	
	@Column(name="cheque_id")
    private Long ChequeId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="slip_date")
    private Date submissionDate = new Date();
	
	@Column(name="name")
	private String name;
	
	@Column(name="amount")
	private Long Amount;
	
	public BankSlip() {
		// TODO Auto-generated constructor stub
	}

	public BankSlip(Long bankSlipId, int savingsAccountId, Long currentAccountId, Long chequeId, Date submissionDate,
			String name,Long amount) {
		super();
		this.BankSlipId = bankSlipId;
		this.savingsAccountId = savingsAccountId;
		this.currentAccountId = currentAccountId;
		this.ChequeId = chequeId;
		this.submissionDate = submissionDate;
		this.name = name;
		this.Amount=amount;
	}

	public Long getBankSlipId() {
		return BankSlipId;
	}

	public void setBankSlipId(Long bankSlipId) {
		BankSlipId = bankSlipId;
	}

	public int getSavingsAccountId() {
		return savingsAccountId;
	}

	public void setSavingsAccountId(int savingsAccountId) {
		this.savingsAccountId = savingsAccountId;
	}

	public Long getCurrentAccountId() {
		return currentAccountId;
	}

	public void setCurrentAccountId(Long currentAccountId) {
		this.currentAccountId = currentAccountId;
	}

	public Long getChequeId() {
		return ChequeId;
	}

	public void setChequeId(Long chequeId) {
		ChequeId = chequeId;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAmount() {
		return Amount;
	}

	public void setAmount(Long double1) {
		Amount = double1;
	}
	
	

}

