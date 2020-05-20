package com.anurag.batch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BankBranchDetails {
	@Id
    @Column (name = "Id", nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;	
	@Column (name = "BankCode", nullable = false)
	private String bankCode;
	@Column (name = "BranchCode", nullable = false)
	private String branchCode;
	@Column (name = "Address", nullable = false)
	private String address;
	@Column (name = "IfscCode", nullable = false)
	private String ifscCode;
	@Column (name = "SortCode", nullable = false)
	private String sortCode;
	
	public BankBranchDetails() {
		
	}
	
	public BankBranchDetails(int id,String bankCode,String branchCode,String address,String ifscCode,String sortCode) {
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
	public void setId(int id) {
		this.id = id;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getSortCode() {
		return sortCode;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
	
}

