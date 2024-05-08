package com.api.servicecall;

import java.net.URL;
import java.sql.Timestamp;
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

public class GetSubFreeRscRelaInfo {

	public HashMap<String, Object> GetSubFreeRscRelaInfoImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
		HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** GetSubFreeRscRelaInfoImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String DestServiceNumber = request.get("DestServiceNumber").toString();
			
			String groupNo = request.get("GroupNo").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("DestServiceNumber :"+ DestServiceNumber);
			log.info("groupNo :"+ groupNo);			
						
			String requestXmlData = Config.SUB_FREE_RSC_RELA_INFO_XML_DATA.replace(Config.SERVICE_NUMBER,DestServiceNumber)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.GROUP_NO, groupNo);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.SUB_FREE_RSC_RELA_INFO_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.SUB_FREE_RSC_RELA_INFO_URL, requestXmlData, Config.DEACTIVATE_SUBSCRIBTION_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.SUB_FREE_RSC_RELA_INFO_URL, requestXmlData, Config.DEACTIVATE_SUBSCRIBTION_SOAP_ACTION_NAME, log);

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
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubFreeRscRelaInfoResponse").has("quer:ResponseHeader")) 
		{
			//success case
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubFreeRscRelaInfoResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubFreeRscRelaInfoResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
			
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubFreeRscRelaInfoResponse").has("quer:GetSubFreeRscRelaInfoBody")) {
					
					JSONObject dataObject = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubFreeRscRelaInfoResponse").getJSONObject("quer:GetSubFreeRscRelaInfoBody").getJSONObject("quer:ShareFreeRscRelationList").optJSONObject("bas:ShareFreeRscRelationInfo");
			
				    if (dataObject != null) {
				    	
				    	JSONObject memberListObj = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubFreeRscRelaInfoResponse").getJSONObject("quer:GetSubFreeRscRelaInfoBody").getJSONObject("quer:ShareFreeRscRelationList").getJSONObject("bas:ShareFreeRscRelationInfo");
						
				    						
						jsonObjres.put("RelationKey",memberListObj.get("bas:RelationKey"));
				    	
				    }else {
				    	JSONArray memberListArray = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubFreeRscRelaInfoResponse").getJSONObject("quer:GetSubFreeRscRelaInfoBody").getJSONObject("quer:ShareFreeRscRelationList").getJSONArray("bas:ShareFreeRscRelationInfo");
			
				    	JSONObject memberObj =memberListArray.getJSONObject(0);
				    	
				    	jsonObjres.put("RelationKey",memberObj.get("bas:RelationKey"));
				    }
					
			}else {
				jsonObjres.put("RelationKey", Config.NILL_VALUE);	

			}
			
			return jsonObjres;
		}
		else {

			jsonObjres.put("RetCode", Config.NILL_VALUE);	
			jsonObjres.put("RetMsg", Config.NILL_VALUE);	
			jsonObjres.put("RelationKey", Config.NILL_VALUE);	
			return jsonObjres;
		}
		
		
}
}
