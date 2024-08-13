package com.mind.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "REGISTRATION")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    
    private double balance;

    public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
//	@Column(name="is_alreadyexists" ,nullable=false)
//	private boolean already_exist =false;
//	

	
	private String name;
    private String email;
    private String password;
    @Column(name = "account_type")
    private String accountType;
    private long phone;
    private String city;
    private String state;
    private Date dob;

	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public void setIdproof(long idproof) {
		this.idproof = idproof;
	}

	private long idproof;
    
   
   
@Column(name="isApproved",nullable = false)
    private boolean isApproved =false;






    // Getters and Setters
public Registration() {
	this.isApproved=false;
}
    public long getPhone() {
		return phone;
	}
//
//	public void setPhone(long phone) {
//		this.phone = phone;
//	}

	public boolean getisApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
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

//	public void setIdproof(long idproof) {
//		this.idproof = idproof;
//	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
//	public boolean getAlready_exist() {
//		return already_exist;
//	}
//	public void setAlready_exist(boolean already_exist) {
//		this.already_exist = already_exist;
//	}
	public boolean isApproved() {
		return isApproved;
	}
	public Registration(Long userId, double balance, String name, String email, String password,
			String accountType, long phone, String city, String state, Date dob, long idproof,
			boolean isApproved) {
		super();
		this.userId = userId;
		this.balance = balance;
	//	this.already_exist = already_exist;
		this.name = name;
		this.email = email;
		this.password = password;
		this.accountType = accountType;
		this.phone = phone;
		this.city = city;
		this.state = state;
		this.dob = dob;
		this.idproof = idproof;
		this.isApproved = isApproved;
	}
    
}


