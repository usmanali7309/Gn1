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
import com.api.util.AESEcription;
import com.api.util.FileOperations;
import com.api.util.testdata;

import org.slf4j.Logger;

public class GetSubscriber 
{

	
	public HashMap<String, Object> getSubscriberImp(LinkedHashMap<String,Object> request,Logger log) throws Exception
	{
		HashMap<String, Object> respJson = new HashMap<String, Object>();;
			
		
			log.info("***** getSubscriberImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String serviceNumber = request.get("ServiceNumber").toString();
			
			log.info("serviceNumber :"+ serviceNumber);
			
			log.info("transactionId :"+ transactionId);
						
			
			String requestXmlData = Config.GET_SUBSCRIBER_RQU_XML_DATA.replace(Config.SERVICE_NUMBER,serviceNumber)
					.replace(Config.TRANSACTION_ID, transactionId);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.GET_SUBSCRIBER_URL);
								
			log.info("requestUrl :"+ requestUrl);
			
		    Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));
	    	
			String outputData = null;
			
//			if ("https".equals(requestUrl.getProtocol())) { 
//								
//				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
//				outputData = oGethttpsConnection.postRequest(Config.GET_SUBSCRIBER_URL, requestXmlData, Config.GETSUBSCRIBER_SOAP_ACTION_NAME, log);
//				
//            } else if ("http".equals(requestUrl.getProtocol())) {
//            	
//    			GethttpConnection oGethttpConnection = new GethttpConnection();
//    			outputData = oGethttpConnection.postRequest(Config.GET_SUBSCRIBER_URL, requestXmlData, Config.GETSUBSCRIBER_SOAP_ACTION_NAME, log);
//            }
			
			log.info("outputData : "+ outputData);
			
			Date endDate = new Date();	        
		    log.info("API Request end Time :"+ new Timestamp(endDate.getTime()));
		    
		    
//		    respJson = this.convertJsonObject(outputData,log);
					    
		    respJson.put("StatusCode", "200");
		    respJson.put("StatusDescription", "Success");
		
		    log.info("outputData respJson : "+ respJson);
		    
		    return respJson;
		    
		

	}
	
	
	public HashMap<String, Object> convertJsonObject(String outputData,Logger log) throws Exception
	{
		JSONObject jsonObj = XML.toJSONObject(outputData);
		
		HashMap<String, Object> jsonObjres=new HashMap<>();
		
		log.debug("convertJsonObject");
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").has("quer:GetSubscriberBody")) {
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
		jsonObjres.put("SubscriberType", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").get("quer:SubscriberType"));
		jsonObjres.put("NetworkType", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").get("quer:NetworkType"));
		jsonObjres.put("Status", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").get("quer:Status"));
		//jsonObjres.put("AshamTeleActiveStatus", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").getJSONObject("quer:PrimaryOffering").get("bas:Status"));  
		
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").has("quer:PUK1")) {

			String tempPuknumber = (jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").get("quer:PUK1")).toString();
			
			AESEcription aESEcription = new AESEcription();
			
			
			String puknumber = aESEcription.decrypt(tempPuknumber,Config.ENCRYPTION_KEY);
			
			
			jsonObjres.put("pukNo", puknumber);
		}
		else
		{
			jsonObjres.put("pukNo", Config.NILL_VALUE);
		}
		
		
		JSONArray ParameterInfo = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").getJSONObject("quer:ExtParamList").getJSONArray("bas:ParameterInfo");

		for (int i=0;i< ParameterInfo.length();i++)
		{
			
			JSONObject rec = ParameterInfo.getJSONObject(i);
			Object paramName = rec.get("bas:ParamName");
		    
		    if("IVR Language".equalsIgnoreCase(paramName.toString()))
		    {
		    	Object langaugeCode = rec.get("bas:ParamValue");
		    	jsonObjres.put("langaugeCode", langaugeCode);
		    }
		    
		    if("CallCenterAccess".equalsIgnoreCase(paramName.toString()))
		    {
		    	Object langaugeCode = rec.get("bas:ParamValue");
		    	jsonObjres.put("CallCenterAccess", langaugeCode);
		    }
		   
		  }
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
			jsonObjres.put("SubscriberType", Config.NILL_VALUE);
			jsonObjres.put("NetworkType", Config.NILL_VALUE);
			jsonObjres.put("Status", Config.NILL_VALUE);
			jsonObjres.put("CallCenterAccess", Config.NILL_VALUE);
			jsonObjres.put("langaugeCode", Config.NILL_VALUE);
			jsonObjres.put("AshamTeleActiveStatus", Config.NILL_VALUE);
			jsonObjres.put("pukNo", Config.NILL_VALUE);
		}
		
		
		return jsonObjres;
}
	
	
}
