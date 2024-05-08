package com.api.servicecall;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;


import org.slf4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.api.Connection.GethttpConnection;
import com.api.Connection.GethttpsConnection;
import com.api.configuration.Config;
import com.api.util.FileOperations;

public class GetCustomerDataViaaccountCode {

	public HashMap<String, Object> getCustomerDataViaaccountCodeImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
		    HashMap<String, Object> respJson = new HashMap<String, Object>();

			log.info("***** getCustomerDataViaaccountCodeImp *****");
			
			

			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String requestTime = request.get("RequestTime").toString();
			
			//String languageCode = request.get("LanguageCode").toString();
			
			String accountCode = request.get("AccountCode").toString();
			
			
			log.info("transactionId :"+ transactionId);
			log.info("requestTime :"+ requestTime);
			//log.info("languageCode :"+ languageCode);
			log.info("accountCode :"+ accountCode);
			
			String requestXmlData = Config.GET_CUSTOMER_DATA_VIA_ACCOUNT_CODE_XML_DATA.replace(Config.SERVICE_NUMBER,accountCode)
									.replace(Config.REQUEST_TIME, requestTime).
									replace(Config.TRANSACTION_ID, transactionId);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.GET_CUSTOMER_DATA_VIA_ACCOUNT_CODE_URL);
			
			log.info("requestUrl :"+ requestUrl);

			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));
			
			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.GET_CUSTOMER_DATA_VIA_ACCOUNT_CODE_URL, requestXmlData, Config.GET_CUSTOMER_DATA_VIA_ACCOUNT_CODE_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.GET_CUSTOMER_DATA_VIA_ACCOUNT_CODE_URL, requestXmlData, Config.GET_CUSTOMER_DATA_VIA_ACCOUNT_CODE_SOAP_ACTION_NAME, log);

            }
			
			
			log.info("outputData : "+ outputData);
			

			Date endDate = new Date();	        
		    log.info("API Request end Time :"+ new Timestamp(endDate.getTime()));
			
		    respJson = this.convertJsonObject(outputData,log);
					    
		    respJson.put("StatusCode", "200");
		    respJson.put("StatusDescription", "Success");
		
		    return respJson;
		    
		

	}
	
	
	public HashMap<String, Object> convertJsonObject(String outputData,Logger log) throws Exception
	{
		JSONObject jsonObj = XML.toJSONObject(outputData);
		
		HashMap<String, Object> jsonObjres=new HashMap<>();
		
		log.debug("convertJsonObject");
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerDataViaAccountCodeResponse").has("quer:ResponseHeader")) {
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerDataViaAccountCodeResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerDataViaAccountCodeResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
		
		JSONArray ParameterInfo = null;
		
		Object PaymentType		= null;
		
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerDataViaAccountCodeResponse").has("quer:CustomerData") && 
					jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerDataViaAccountCodeResponse").getJSONObject("quer:CustomerData").getJSONObject("quer:SubscriberList").has("bas:SubscriberAbstractInfo")){	
			
				
				JSONObject dataObject = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerDataViaAccountCodeResponse").getJSONObject("quer:CustomerData").getJSONObject("quer:SubscriberList").optJSONObject("bas:SubscriberAbstractInfo");

			    
			    if (dataObject != null) {
			    	
			    	JSONObject	ParameterInfo1 = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerDataViaAccountCodeResponse").getJSONObject("quer:CustomerData").getJSONObject("quer:SubscriberList").getJSONObject("bas:SubscriberAbstractInfo");
					
					  ArrayList<String> memberObjList = new ArrayList<String>();
					  
					  Object PaymentTypeVal = ParameterInfo1.get("bas:PaymentType");
					  	  
					  memberObjList.add(PaymentTypeVal.toString());
					  
					 jsonObjres.put("PaymentType", memberObjList);
			    }
				
			    else {
								
					  ParameterInfo = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerDataViaAccountCodeResponse").getJSONObject("quer:CustomerData").getJSONObject("quer:SubscriberList").getJSONArray("bas:SubscriberAbstractInfo");
					
					  ArrayList<String> memberObjList = new ArrayList<String>();
		
					 for (int i=0;i< ParameterInfo.length();i++)
					 {
					  
					  JSONObject rec = ParameterInfo.getJSONObject(i);
					  
					  PaymentType = rec.get("bas:PaymentType");
					
					  memberObjList.add(PaymentType.toString());
					  
					 }
					 
					 jsonObjres.put("PaymentType", memberObjList);
			    }
			
			}else {
				jsonObjres.put("PaymentType", Config.NILL_VALUE);
			}
		
		
		
		}else {
			jsonObjres.put("RetCode", Config.NILL_VALUE);
			jsonObjres.put("RetMsg", Config.NILL_VALUE);
			jsonObjres.put("PaymentType", Config.NILL_VALUE);
		}
		
		return jsonObjres;
	 }






}
