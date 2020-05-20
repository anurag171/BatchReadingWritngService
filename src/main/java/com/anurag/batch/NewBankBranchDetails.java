package com.anurag.batch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class NewBankBranchDetails {
	private final int id;	
	private final String bankCode;
	private final String branchCode;
	private final String address;
	private final String ifscCode;
	private final String sortCode;
	
	
	public NewBankBranchDetails(int id,String bankCode,String branchCode,String address,String ifscCode,String sortCode) {
		this.id = id;
		this.bankCode = bankCode;
		this.branchCode = branchCode;
		this.address = address;
		this.ifscCode = ifscCode;
		this.sortCode = sortCode;
		
	}
	public int getId() {
		return id;
	}
	
	public String getBankCode() {
		return bankCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public String getAddress() {
		return address;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public String getSortCode() {
		return sortCode;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("Id = ").append(id).append(":")
				.append("bankCode = ").append(bankCode).append(":")
				.append("branchCode = ").append(branchCode).append(":")
				.append("address = ").append(address).append(":")
				.append("ifscCode = ").append(ifscCode).append(":")
				.append("sortCode = ").append(sortCode).append(":").toString();
				
	}
}

