package com.anurag.batch;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Value("classpath:/input/inputData*.csv")
	private Resource[] inputResources;
	
	@Bean
    public FlatFileItemReader<BankBranchDetails> reader() {
         FlatFileItemReader<BankBranchDetails> reader = new FlatFileItemReaderBuilder<BankBranchDetails>()
                .name("voltItemReader")                
                .delimited()                
                .names(new String[]{"Id", "BankCode","BranchCode","Address","IfscCode","SortCode"})
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<BankBranchDetails>() {{
                    setTargetType(BankBranchDetails.class);
                }})
                .build();
         reader.setLinesToSkip(1);
         
         
         return reader;
    }
	
	@Bean
	public MultiResourceItemReader<BankBranchDetails> multiResourceItemReader() 
	{
	    MultiResourceItemReader<BankBranchDetails> resourceItemReader = new MultiResourceItemReader<BankBranchDetails>();
	    resourceItemReader.setResources(inputResources);
	    resourceItemReader.setDelegate(reader());
	    return resourceItemReader;
	}
	
	@Bean
    public LineMapper<BankBranchDetails> lineMapper() {
        final DefaultLineMapper<BankBranchDetails> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] {"Id", "BankCode","BranchCode","Address","IfscCode","SortCode"});
        final BankBranchFieldSetMapper fieldSetMapper = new BankBranchFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }
	
	@Bean
    public BankBranchDetailsProcessor processor() {
        return new BankBranchDetailsProcessor();
    }
	
	@Bean
    public ItemWriter<BankBranchDetails> writer(List<BankBranchDetails> item) {
        return new ItemWriter<BankBranchDetails>() {
        	
        	@Override
        	 public void write(List<? extends BankBranchDetails> item) throws Exception {
        		System.out.println(item.size());
        	  System.out.println(item);
        	 }
		};
    }
	
	@Bean
    public Step step1(ItemWriter<BankBranchDetails> writer) {
        return stepBuilderFactory.get("step1")
                .<BankBranchDetails, BankBranchDetails> chunk(10)
                .reader(reader())                
                .processor(processor())
                .writer(writer)
                .build();
    }
	
	@Bean
    public Job importBankBranchDetailJob(NotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importBankBranchDetailJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }
}
