package com.mind.dto;

public class ChequeBankSlipDTO {
    private Long chequeId;
      // Replace with actual fields
    private Long bankSlipId;
    private String bankSlipDetails;  // Replace with actual fields

    // Constructor
    public ChequeBankSlipDTO(Long chequeId, String chequeDetails, Long bankSlipId) {
        this.setChequeId(chequeId);
        
        this.setBankSlipId(bankSlipId);
        this.setBankSlipDetails(bankSlipDetails);
    }

	

	public String getBankSlipDetails() {
		return bankSlipDetails;
	}

	public void setBankSlipDetails(String bankSlipDetails) {
		this.bankSlipDetails = bankSlipDetails;
	}

	public Long getBankSlipId() {
		return bankSlipId;
	}

	public void setBankSlipId(Long bankSlipId) {
		this.bankSlipId = bankSlipId;
	}

	public Long getChequeId() {
		return chequeId;
	}

	public void setChequeId(Long chequeId) {
		this.chequeId = chequeId;
	}

    // Getters and setters
}
