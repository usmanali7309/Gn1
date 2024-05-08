package com.api.servicecall;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.JSONObject;
import org.json.XML;

import com.api.Connection.GethttpConnection;
import com.api.Connection.GethttpsConnection;
import com.api.configuration.Config;
import com.api.util.FileOperations;

import org.slf4j.Logger;

public class ChangeSubInfo {
	public HashMap<String, Object> changeSubInfoImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
		HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** changeSubInfoImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String serviceNumber = request.get("ServiceNumber").toString();
			
			String languageCode = request.get("languageCode").toString();
			
			String requestTime = request.get("RequestTime").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("serviceNumber :"+ serviceNumber);
			log.info("languageCode  :"+ languageCode);
			
			log.info("Config.CHANGE_SUBINFO_URL  :"+ Config.CHANGE_SUBINFO_URL);
			
			String requestXmlData = Config.CHANGE_SUBINFO_RQU_XML_DATA.replace(Config.SERVICE_NUMBER,serviceNumber)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.LANGUAGE_CODE, languageCode).replace(Config.REQUEST_TIME, requestTime);
			
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.CHANGE_SUBINFO_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
//			if ("https".equals(requestUrl.getProtocol())) { 
//				
//				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
//				outputData = oGethttpsConnection.postRequest(Config.CHANGE_SUBINFO_URL, requestXmlData, Config.GETSUBSCRIBER_SOAP_ACTION_NAME, log);
//				
//            } else if ("http".equals(requestUrl.getProtocol())) {
//            	
//    			GethttpConnection oGethttpConnection = new GethttpConnection();
//    			outputData = oGethttpConnection.postRequest(Config.CHANGE_SUBINFO_URL, requestXmlData, Config.CHANGE_SUBINFO_SOAP_ACTION_NAME, log);
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
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("sub:ChangeSubInfoRspMsg").has("com:RspHeader")) {
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("sub:ChangeSubInfoRspMsg").getJSONObject("com:RspHeader").get("com:ReturnCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("sub:ChangeSubInfoRspMsg").getJSONObject("com:RspHeader").get("com:ReturnMsg"));
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("sub:ChangeSubInfoRspMsg").getJSONObject("com:RspHeader").has("com:RspTime")) {
			jsonObjres.put("RspTime", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("sub:ChangeSubInfoRspMsg").getJSONObject("com:RspHeader").get("com:RspTime"));
		}else {
			jsonObjres.put("RspTime", Config.NILL_VALUE);
		}
		
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
			jsonObjres.put("RspTime", Config.NILL_VALUE);
		}
		
		return jsonObjres;
	}
}
