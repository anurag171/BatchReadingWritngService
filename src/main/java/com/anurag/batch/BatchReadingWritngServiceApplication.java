package com.anurag.batch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BatchReadingWritngServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BatchReadingWritngServiceApplication.class);
//		 /app.setEnvironment(false);
		ConfigurableApplicationContext ctx= app.run(args);
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
        JobParameters xmlJobParameters = new JobParametersBuilder()
            .addDate("date", new Date()).addString(BatchConstants.XML_INPUT_DIRECTORY, "/xmlinput/")
            .toJobParameters(); 
        
        JobParameters csvJobParameters = new JobParametersBuilder()
                .addDate("date", new Date()).addString(BatchConstants.CSV_INPUT_DIRECTORY, "/csvinput/")
                .toJobParameters(); 
        
        JobParameters xlsJobParameters = new JobParametersBuilder()
                .addDate("date", new Date()).addString(BatchConstants.XLS_INPUT_DIRECTORY, "/xlsinput/")
                .toJobParameters(); 
        
        JobParameters xlsxJobParameters = new JobParametersBuilder()
                .addDate("date", new Date()).addString(BatchConstants.XLSX_INPUT_DIRECTORY, "/xlsxinput/")
                .toJobParameters(); 
        
        String filenameExtention = getFileName(args[0]);
        
        if("csv".equals(filenameExtention)) {
        	
        	Job addNewCsvJob = ctx.getBean("importBankBranchDetailJob",Job.class);
        	try {
				JobExecution jobExecution = jobLauncher.run(addNewCsvJob, csvJobParameters);
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        }else if("xls".equals(filenameExtention)) {
        	
        }else if("xlsx".equals(filenameExtention)) {
        	
        }else if("csv".equals(filenameExtention)) {
        	
        }
	}

	private static String getFileName(String fileName) {
		String dot = ".";
		int indexOfDot = fileName.lastIndexOf(dot);
		int lenght = fileName.length();
		return fileName.substring(indexOfDot+1, lenght);
	}

}
