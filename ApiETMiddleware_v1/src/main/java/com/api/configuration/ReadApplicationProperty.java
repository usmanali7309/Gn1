package com.api.configuration;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;



public class ReadApplicationProperty {
	
	
    public void readAppProperty(String externalPropFilePath,String QueryUnPaidBillStartTime,String QueryUnPaidBillEndTime ,Logger log ){
    	
		// FileInputStream out = null;

    	try
	    {
		 //Reading application configurations
		// File config = ResourceUtils.getFile("classpath:application.properties");
		// Properties prop = new Properties();
		// prop.load(out = new FileInputStream(config.toPath().toString()));			 
		 
		 		
		 //Config.EXTERNAL_FILEPATH 		=   (String) prop.get("externalPropFilePath");	
		 Config.EXTERNAL_FILEPATH 			=   externalPropFilePath;	
//		 System.out.println(Config.EXTERNAL_FILEPATH);
		 
		 Config.QUERY_UNPAIDBILL_STARTTIME 	= QueryUnPaidBillStartTime;
		 Config.QUERY_UNPAIDBILL_ENDTIME 	= QueryUnPaidBillEndTime;	 

		 ReadExternlProperty oReadPro = new ReadExternlProperty();
		 oReadPro.readProperty(log);
			
	 }
	 catch (Exception e) 
	 {
		 log.error("Exception in Reading configuration from property file " +e);
	 }
	 finally {
		 try {
			// out.close();
		} catch (Exception e) {

			 log.error("Exception in Reading configuration from property file " +e);
		}
	}
  }
  
}
