package com.mind.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SAVINGS_ACCOUNT")
public class SavingsAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="IS_ALREADYEXISTS")
	private String isalreadyexist;
	
    private long phone;
    @Column(name="CITY")
    private String city;
    private String state;
    private Date dob;
   @Column(name="user_id")
    private int userId;
	public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
	public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}

	private long idproof;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getIdproof() {
		return idproof;
	}
	public void setIdproof(long idproof) {
		this.idproof = idproof;
	}
	public SavingsAccount() {
		// TODO Auto-generated constructor stub
	}
    public SavingsAccount(Long accountId,int userId, Double balance, int count, Double minimumBalance,
			Date lastWithdrawalDate, Double dailyWithdrawalLimit, String password) {
		super();
		this.accountId = accountId;
		this.userId=userId;
		this.balance = balance;
		
		this.count = count;
		this.minimumBalance = minimumBalance;
		this.lastWithdrawalDate = lastWithdrawalDate;
		this.dailyWithdrawalLimit = dailyWithdrawalLimit;
		this.password = password;
	}

	
    private Double balance;
    @Column(name="PASWORDCOUNT")
    private int count;

	public int getCount() {
	return count;
}

public void setCount(int count) {
	this.count = count;
}

	@Column(name="MINIMUMBALANCE" ,nullable=false)
    private Double minimumBalance = 2500.0;
    // Interest rate in percentage
    
   
    @Transient
    private Date lastWithdrawalDate;
    
    
    public Double getDailyWithdrawalLimit() {
		return dailyWithdrawalLimit;
	}

	public void setDailyWithdrawalLimit(Double dailyWithdrawalLimit) {
		this.dailyWithdrawalLimit = dailyWithdrawalLimit;
	}
	
	@Column(name="dailywithdrawallimit", nullable=false)
	private Double dailyWithdrawalLimit=10000.0;
    
    
    public Date getLastWithdrawalDate() {
		return lastWithdrawalDate;
	}

	public void setLastWithdrawalDate(Date lastWithdrawalDate) {
		this.lastWithdrawalDate = lastWithdrawalDate;
	}

	@OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",insertable = false,updatable = false)
    private Registration registration;
    
    
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="password")
    private String password;
  
   
    public boolean withdraw(Double amount) {
        if (balance - amount >= minimumBalance) {
            balance -= amount;
            return true;
        }
        return false;
    }
    public boolean epay(Double amount) {
        if (balance > amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(Double amount) {
        balance += amount;
    }

    public boolean transfer(SavingsAccount targetAccount, Double amount) {
        if (withdraw(amount)) {
            targetAccount.deposit(amount);
            return true;
        }
        return false;
    }
/*
    public boolean canWithdrawToday() {
        LocalDate today = LocalDate.now();
        long todayCount = transactionDates.stream()
                .filter(date -> date.equals(today))
                .count();
        return todayCount < 5;
    }
*/
    public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	

	

	public SavingsAccount(String name, long phone, String city, String state, Date dob, int userId, long idproof,
			Double balance,String email,String isalreadyexist,  int count, Double minimumBalance,Double dailyWithdrawalLimit,
			String password) {
		super();
		this.name = name;
		this.phone = phone;
		this.city = city;
		this.state = state;
		this.isalreadyexist=isalreadyexist;
		this.dob = dob;
		this.email=email;
		this.userId = userId;
		this.idproof = idproof;
		this.balance = balance;
		this.count = count;
		this.minimumBalance = minimumBalance;
		this.dailyWithdrawalLimit = dailyWithdrawalLimit;
		
		this.password = password;
	}
	public String getIsalreadyexist() {
		return isalreadyexist;
	}
	public void setIsalreadyexist(String isalreadyexist) {
		this.isalreadyexist = isalreadyexist;
	}
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(Double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

public boolean payBill(Double amount) {
	return withdraw(amount);
}
public SavingsAccount(String name, String email, String isalreadyexist, long phone, String city, String state,
		Date dob, int userId, long idproof, Double balance, int count, Double minimumBalance,
		Double dailyWithdrawalLimit, String password) {
	super();
	this.name = name;
	this.email = email;
	this.isalreadyexist = isalreadyexist;
	this.phone = phone;
	this.city = city;
	this.state = state;
	this.dob = dob;
	this.userId = userId;
	this.idproof = idproof;
	this.balance = balance;
	this.count = count;
	this.minimumBalance = minimumBalance;
	this.dailyWithdrawalLimit = dailyWithdrawalLimit;
	this.password = password;
}
	

  
}



    // Getters and Setters
