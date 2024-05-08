
package com.api.configuration;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class InnitReadConfig {

	//private static Logger log = Logger.getLogger("init_log");
	private static final Logger log = LoggerFactory.getLogger("init_log");

	
	@Value("${name}")
	private String message;
	
	@Value("${externalPropFilePath}")
	private String externalPropFilePath;
	
	@Value("${QueryUnPaidBillStartTime}")
	private String QueryUnPaidBillStartTime;
	
	@Value("${QueryUnPaidBillEndTime}")
	private String QueryUnPaidBillEndTime;


	 @PostConstruct
	 public void ReadInitConfig(){
		 
		 try {
			 
			 log.info("testname : "+ message);
			 log.info("externalPropFilePath : "+ externalPropFilePath);
			 
			 ReadApplicationProperty oPro = new ReadApplicationProperty();
			 oPro.readAppProperty(externalPropFilePath,QueryUnPaidBillStartTime,QueryUnPaidBillEndTime,log);

		 }
		 catch (Exception e) {
			// TODO: handle exception
			 log.error("Exception in InnitReadConfig : "+ e);
		}
    }
}