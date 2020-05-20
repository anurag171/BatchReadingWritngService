package com.anurag.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BankBranchDetailsProcessor implements ItemProcessor<BankBranchDetails, BankBranchDetails> {

	@Override
	public BankBranchDetails process(final BankBranchDetails item) throws Exception {
		final int id = item.getId();
		//"Id", "BankCode","BranchCode","Address","IfscCode","SortCode"
        final String BankCode = item.getBankCode();
        final String BranchCode = item.getBranchCode();
        final String Address = item.getAddress();
        final String IfscCode = item.getIfscCode();
        final String SortCode = item.getSortCode();
        final BankBranchDetails processedBankBranchDetails = new BankBranchDetails(id,BankCode,BranchCode,Address,IfscCode,SortCode);        
        return processedBankBranchDetails;
	}

}
