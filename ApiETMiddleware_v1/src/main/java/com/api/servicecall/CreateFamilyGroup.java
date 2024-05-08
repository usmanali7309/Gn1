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

public class CreateFamilyGroup {

	public HashMap<String, Object> CreateFamilyGroupImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
		HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** CreateFamilyGroupImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String serviceNumber = request.get("ServiceNumber").toString();
			
			String primaryOfferingCode = request.get("PrimaryOfferingCode").toString();
			
			String effectiveDate = request.get("EffectiveDate").toString();
			
			String offeringId = request.get("OfferingId").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("serviceNumber :"+ serviceNumber);
			log.info("primaryOfferingCode :"+ primaryOfferingCode);
			log.info("effectiveDate :"+ effectiveDate);
			log.info("offeringId :"+ offeringId);
			
						
			String requestXmlData = Config.CREATE_FAMILY_GROUP_XML_DATA.replace(Config.SERVICE_NUMBER,serviceNumber)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.PRIMARY_OFFERING_CODE, primaryOfferingCode).
									replace(Config.START_TIME, effectiveDate).replace(Config.OFFERING_ID, offeringId);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.CREATE_FAMILY_GROUP_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.CREATE_FAMILY_GROUP_URL, requestXmlData, Config.CREATE_FAMILY_GROUP_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.CREATE_FAMILY_GROUP_URL, requestXmlData, Config.CREATE_FAMILY_GROUP_SOAP_ACTION_NAME, log);

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
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:CreateFamilyGroupRspMsg").has("fam:CreateFamilyGroupRspBody")) {
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:CreateFamilyGroupRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:CreateFamilyGroupRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetMsg"));
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:CreateFamilyGroupRspMsg").getJSONObject("fam:CreateFamilyGroupRspBody").has("fam:ProcessingResult")) {
				jsonObjres.put("ProcessingResult", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:CreateFamilyGroupRspMsg").getJSONObject("fam:CreateFamilyGroupRspBody").get("fam:ProcessingResult"));
			}else {
				jsonObjres.put("ProcessingResult", Config.NILL_VALUE);
			}
			
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:CreateFamilyGroupRspMsg").getJSONObject("fam:CreateFamilyGroupRspBody").has("fam:Description")) {
				jsonObjres.put("Description", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:CreateFamilyGroupRspMsg").getJSONObject("fam:CreateFamilyGroupRspBody").get("fam:Description"));
			}else {
				jsonObjres.put("Description", Config.NILL_VALUE);
			}
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:CreateFamilyGroupRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:CreateFamilyGroupRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetMsg"));
			jsonObjres.put("ProcessingResult", Config.NILL_VALUE);
			jsonObjres.put("Description", Config.NILL_VALUE);
		}
		
		return jsonObjres;
	}



}
