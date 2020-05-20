package com.anurag.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

@Component
public class BankBranchFieldSetMapper implements FieldSetMapper<BankBranchDetails> {

	@Override
    public BankBranchDetails mapFieldSet(FieldSet fieldSet) {
        final BankBranchDetails details = new BankBranchDetails();
        //"Id", "BankCode","BranchCode","Address","IfscCode","SortCode"
        details.setId(fieldSet.readInt("Id"));
        details.setBankCode(fieldSet.readString("BankCode"));
        details.setBranchCode(fieldSet.readString("BranchCode"));
        details.setAddress(fieldSet.readString("Address"));
        details.setIfscCode(fieldSet.readString("IfscCode"));
        details.setSortCode(fieldSet.readString("SortCode"));
        return details;
    }
}
