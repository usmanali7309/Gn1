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
import com.api.util.FileOperations;

public class DeactivateSub {

	public HashMap<String, Object> DeactivateSubImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
		HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** DeactivateSubImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String groupId = request.get("GroupId").toString();
			
			String requestTime = request.get("RequestTime").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("groupId :"+ groupId);
			log.info("requestTime :"+ requestTime);			
						
			String requestXmlData = Config.DEACTIVATE_SUBSCRIBTION_XML_DATA.replace(Config.GROUP_ID,groupId)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.REQUEST_TIME, requestTime);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.DEACTIVATE_SUBSCRIBTION_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.DEACTIVATE_SUBSCRIBTION_URL, requestXmlData, Config.DEACTIVATE_SUBSCRIBTION_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.DEACTIVATE_SUBSCRIBTION_URL, requestXmlData, Config.DEACTIVATE_SUBSCRIBTION_SOAP_ACTION_NAME, log);

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
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("sub:DeactivateSubRspMsg").has("com:RspHeader")) {
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("sub:DeactivateSubRspMsg").getJSONObject("com:RspHeader").get("com:ReturnCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("sub:DeactivateSubRspMsg").getJSONObject("com:RspHeader").get("com:ReturnMsg"));
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("sub:DeactivateSubRspMsg").getJSONObject("com:RspHeader").has("com:RspTime")) {
				jsonObjres.put("RspTime", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("sub:DeactivateSubRspMsg").getJSONObject("com:RspHeader").get("com:RspTime"));
			}else {
				jsonObjres.put("RspTime", Config.NILL_VALUE);
			}
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("sub:DeactivateSubRspMsg").getJSONObject("com:RspHeader").get("com:ReturnCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("sub:DeactivateSubRspMsg").getJSONObject("com:RspHeader").get("com:ReturnMsg"));
			jsonObjres.put("RspTime", Config.NILL_VALUE);
		}
		
		return jsonObjres;
	}

}
