package com.mind.entity;


import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;



@Entity
@Table(name="current_account")
public class CurrentAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACCOUNT_ID")
    private long accountId;
    
    @Column(name="name")
    private String name;
   
    @Column(name="email")
    private String email;
    private long phone;
    public CurrentAccount(Long accountId, String name, String email, long phone, String city, String state, Date dob,
		long userId, double balance, double overdraftLimit, boolean enableOverdraft, String password, int count,
			LocalDate lastInterestCalculationDate) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.state = state;
		this.dob = dob;
		this.userId = userId;
		this.balance = balance;
		this.overdraftLimit = overdraftLimit;
		this.enableOverdraft = enableOverdraft;
		this.password = password;
		this.count = count;
		this.lastInterestCalculationDate = lastInterestCalculationDate;
	}

	@Column(name="CITY")
    private String city;
    private String state;
   private Date dob;
   @Column(name="user_id")
   private long userId;
   
   private long idproof;
    
    public long getIdproof() {
	return idproof;
}


public void setIdproof(long idproof) {
	this.idproof = idproof;
}


	public String getName() {
	return name;
}


public CurrentAccount(String name, String email, long phone, String city, String state, Date dob, long userId,
		long idproof, double balance, double overdraftLimit, String password, int count,
			LocalDate lastInterestCalculationDate) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.state = state;
		this.dob = dob;
		this.userId = userId;
		this.idproof = idproof;
		this.balance = balance;
		this.overdraftLimit = overdraftLimit;
		this.password = password;
		this.count = count;
		this.lastInterestCalculationDate = lastInterestCalculationDate;
	}


public void setName(String name) {
	this.name = name;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
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


public Date getDob() {
	return dob;
}


public void setDob(Date dob) {
	this.dob = dob;
}


public long getUserId() {
	return userId;
}


public void setUserId(long userId) {
	this.userId = userId;
}

	@Column(nullable = false)
    private double balance;
    
    @Column(name="OVERDRAFT_BALANCE",nullable = false)
    private double overdraftLimit=200000;
    
    @Column(nullable = false)
    private boolean enableOverdraft = false;
    
    @Column(name="password")
    private String password;
    @Column(name="PASWORDCOUNT")
    private int count;
    public CurrentAccount(Long account_id,String password, double balance, int count,double overdraftLimit, boolean enableOverdraft,
			LocalDate lastInterestCalculationDate) {
		super();
		this.accountId = account_id;
		
		this.count=count;
		this.password=password;
		this.balance = balance;
		this.overdraftLimit = overdraftLimit;
		this.enableOverdraft = enableOverdraft;
		this.lastInterestCalculationDate = LocalDate.now();
	}
   
    
    public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="LAST_INTEREST_DATE")
    private LocalDate lastInterestCalculationDate;

	

	public CurrentAccount() {
		// TODO Auto-generated constructor stub
	}

	

	public CurrentAccount(String name, long phone, String city, String state, Date dob, long userId,
			long idproof, double balance, String email, int count,
			double overdraftLimit, String password) {
		
		this.name=name;
		this.phone=phone;
		this.city=city;
		this.state=state;
		this.dob=dob;
		this.userId=userId;
		this.idproof=idproof;
		this.balance=balance;
		this.email=email;
		this.count=count;
		this.overdraftLimit=overdraftLimit;
		this.password=password;
		// TODO Auto-generated constructor stub
	}


	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long account_id) {
		this.accountId = account_id;
	}

	

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public boolean isEnableOverdraft() {
        return enableOverdraft;
    }

    public void setEnableOverdraft(boolean enableOverdraft) {
        this.enableOverdraft = enableOverdraft;
    }
    
   

	public LocalDate getLastInterestCalculationDate() {
		return lastInterestCalculationDate;
	}

	public void setLastInterestCalculationDate(LocalDate lastInterestCalculationDate) {
		this.lastInterestCalculationDate = lastInterestCalculationDate;
	}

	@Override
	public String toString() {
		return "CurrentAccount [account_id=" + accountId + ", userId=" + ", balance=" + balance
				+ ", overdraftLimit=" + overdraftLimit + ", enableOverdraft=" + enableOverdraft + "]";
	}
	
	
	
	public boolean epay(Double amount) {
        if (balance > amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
}

