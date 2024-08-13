package com.mind.entity;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DepositRate {
	@Id
	private int duration;
	private double interestRate;
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
	public DepositRate(int duration, double interestRate) {
		super();
		this.duration = duration;
		this.interestRate = interestRate;
	}
	public DepositRate() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
