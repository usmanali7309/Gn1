package com.api.servicecall;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;


import org.slf4j.Logger;
import org.json.JSONObject;
import org.json.XML;

import com.api.Connection.GethttpConnection;
import com.api.Connection.GethttpsConnection;
import com.api.configuration.Config;
import com.api.util.AESEcription;
import com.api.util.FileOperations;

public class CheckPassword {



	public HashMap<String, Object> checkPasswordImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
			HashMap<String, Object> respJson = new HashMap<String, Object>();

			
			log.info("***** checkPasswordImp *****");
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String serviceNumber = request.get("ServiceNumber").toString();
			
			AESEcription aESEcription = new AESEcription();
			
			String password = aESEcription.encrypt(request.get("Password").toString(),Config.ENCRYPTION_KEY);
			
			log.info("transactionId :"+ transactionId);
			log.info("serviceNumber :"+ serviceNumber);
			
			String requestXmlData = Config.CHECK_PASSWORD_XML_DATA.replace(Config.SERVICE_NUMBER,serviceNumber).replace(Config.TRANSACTION_ID, transactionId)
					.replace(Config.PASSWORD, password)   ;
			
			log.info("requestXmlData :"+ requestXmlData);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));
			
			URL requestUrl = new URL(Config.CHECK_PASSWORD_URL);
			
			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.CHECK_PASSWORD_URL, requestXmlData, Config.CHECK_PASSWORD_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.CHECK_PASSWORD_URL, requestXmlData, Config.CHECK_PASSWORD_SOAP_ACTION_NAME, log);

            }
			
			
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
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:CheckPasswordResponse").has("quer:ResponseHeader")) {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:CheckPasswordResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:CheckPasswordResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
			
		}else {
			
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:CheckPasswordResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:CheckPasswordResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
			
		}
		
		return jsonObjres;
	}








}
