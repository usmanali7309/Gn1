package com.api.servicecall;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.api.Connection.GethttpConnection;
import com.api.Connection.GethttpsConnection;
import com.api.configuration.Config;
import com.api.util.FileOperations;

import org.slf4j.Logger;

public class GetCustomer {
	public HashMap<String, Object> getCustomerImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
		HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** getCustomerImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String serviceNumber = request.get("ServiceNumber").toString();
			
			String languageCode = request.get("LanguageCode").toString();
			
			String requestTime = request.get("RequestTime").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("serviceNumber :"+ serviceNumber);
			log.info("languageCode  :"+ languageCode);
			log.info("requestTime :"+ requestTime);
			
						
			String requestXmlData = Config.GET_CUSTOMER_XML_DATA.replace(Config.SERVICE_NUMBER,serviceNumber)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.LANGUAGE_CODE, languageCode).replace(Config.REQUEST_TIME, requestTime);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.GET_CUSTOMER_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
//			if ("https".equals(requestUrl.getProtocol())) { 
//				
//				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
//				outputData = oGethttpsConnection.postRequest(Config.GET_CUSTOMER_URL, requestXmlData, Config.GET_CUSTOMER_SOAP_ACTION_NAME, log);
//				
//            } else if ("http".equals(requestUrl.getProtocol())) {
//            	
//    			GethttpConnection oGethttpConnection = new GethttpConnection();
//    			outputData = oGethttpConnection.postRequest(Config.GET_CUSTOMER_URL, requestXmlData, Config.GET_CUSTOMER_SOAP_ACTION_NAME, log);
//
//            }
			
			
			log.info("outputData : "+ outputData);
			
			Date endDate = new Date();	        
		    log.info("API Request end Time :"+ new Timestamp(endDate.getTime()));
			
//		    respJson = this.convertJsonObject(outputData,log);
					    
		    respJson.put("StatusCode", "200");
		    respJson.put("StatusDescription", "Success");
		
		    return respJson;
		    
		

	}
	
	
	public HashMap<String, Object> convertJsonObject(String outputData,Logger log) throws Exception
	{
		JSONObject jsonObj = XML.toJSONObject(outputData);
		
		HashMap<String, Object> jsonObjres=new HashMap<>();
		
		log.debug("convertJsonObject");
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").has("quer:ResponseHeader")) {
		
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
			
			

			
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").has("quer:GetCustomerBody")) {
			
			jsonObjres.put("CustomerCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:CustomerCode"));
//			jsonObjres.put("CustomerLanguage", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:CustomerLanguage"));
			jsonObjres.put("CustomerLevel", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:CustomerLevel"));


			
			JSONArray ParameterInfo = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").getJSONObject("quer:ExtParamList").getJSONArray("bas:ParameterInfo");
			
			
			String CustomerType = Config.NILL_VALUE;
			String CustomerCategory = Config.NILL_VALUE;
			String CustomerSubCategory = Config.NILL_VALUE;
			
			for (int i=0;i< ParameterInfo.length();i++)
			{
				
				JSONObject rec = ParameterInfo.getJSONObject(i);
				Object paramName = rec.get("bas:ParamName");
			    
			    if("CustomerType".equalsIgnoreCase(paramName.toString()))
			    {
			    	Object CustomerType1 = rec.get("bas:ParamValue");
			    	CustomerType = CustomerType1.toString();
			    	
			    }
			    
			    
			    if(CustomerType.equalsIgnoreCase("1"))
		    	{
			    	
			    	
			    	if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").has("quer:FirstName")){
			    		jsonObjres.put("FirstName", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:FirstName"));

			    	}else {
			    		jsonObjres.put("FirstName", Config.NILL_VALUE);
			    	}
			    	
			    	if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").has("quer:MiddleName")) {
			    		jsonObjres.put("MiddleName", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:MiddleName"));

			    	}else {
			    		jsonObjres.put("MiddleName", Config.NILL_VALUE);
			    	}
			    	
			    	if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").has("quer:LastName")) {
			    		jsonObjres.put("LastName", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:LastName"));

			    	}else {
			    		jsonObjres.put("LastName", Config.NILL_VALUE);
			    	}
			    	
			    	if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").has("quer:Nationality")) {
			    		jsonObjres.put("Nationality", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:Nationality"));

			    	}else {
			    		jsonObjres.put("Nationality", Config.NILL_VALUE);
			    	}
			    	
			    	if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").has("quer:Gender")) {
			    		jsonObjres.put("Gender", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:Gender"));

			    	}else {
			    		jsonObjres.put("Gender", Config.NILL_VALUE);

			    	}
			    		
			    	jsonObjres.put("BranchName",Config.NILL_VALUE);
		    	}
		    	
		    	if(CustomerType.equalsIgnoreCase("2"))
		    	{
		    		 if("Enterprise Customer Name".equalsIgnoreCase(paramName.toString()))
		 		    {
		    			Object CustomerType1 = rec.get("bas:ParamValue");
		 		    	String firstname = CustomerType1.toString();
		 	    		jsonObjres.put("FirstName",firstname);
		 	    		
		 	    		jsonObjres.put("MiddleName", Config.NILL_VALUE);
		 	    		jsonObjres.put("LastName", Config.NILL_VALUE);
		 	    		jsonObjres.put("Nationality", Config.NILL_VALUE);
		 	    		jsonObjres.put("Gender", Config.NILL_VALUE);
		 		    }
		    		 
		    		 if("Branch Name".equalsIgnoreCase(paramName.toString()))
		    		 {
		    		    	Object branchName = rec.get("bas:ParamValue");   		    	
		    		    	jsonObjres.put("BranchName",branchName.toString());
		    		 }
		    	}
		    	
			    
			    
			    if("Customer Category".equalsIgnoreCase(paramName.toString()))
			    {
			    	Object CustomerCategory1 = rec.get("bas:ParamValue");
			    	CustomerCategory = CustomerCategory1.toString();
			    
			    }
			    if("Customer Sub-Category".equalsIgnoreCase(paramName.toString()))
			    {
			    	Object CustomerSubCategory1 = rec.get("bas:ParamValue");
			    	CustomerSubCategory = CustomerSubCategory1.toString();
			    }

			  }
			
			jsonObjres.put("CustomerType", CustomerType);
			jsonObjres.put("CustomerCategory", CustomerCategory);
			jsonObjres.put("CustomerSubCategory", CustomerSubCategory);

			}else {
				jsonObjres.put("CustomerCode", Config.NILL_VALUE);
				jsonObjres.put("CustomerCategory", Config.NILL_VALUE);
//				jsonObjres.put("CustomerLanguage", Config.NILL_VALUE);
				jsonObjres.put("CustomerLevel", Config.NILL_VALUE);
				jsonObjres.put("CustomerType", Config.NILL_VALUE);
				jsonObjres.put("CustomerSubCategory", Config.NILL_VALUE);
				
				jsonObjres.put("FirstName", Config.NILL_VALUE);
				jsonObjres.put("MiddleName", Config.NILL_VALUE);
				jsonObjres.put("LastName", Config.NILL_VALUE);
				jsonObjres.put("Nationality", Config.NILL_VALUE);
				jsonObjres.put("Gender", Config.NILL_VALUE);
				jsonObjres.put("BranchName",Config.NILL_VALUE);
			}
			
		}else{
			jsonObjres.put("RetCode",  Config.NILL_VALUE);
			jsonObjres.put("RetMsg",  Config.NILL_VALUE);
			
		}
		
		
		
		
		
		return jsonObjres;
}
}
