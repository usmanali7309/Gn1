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

public class ChangeFamilyFreeResourceShare {

	public HashMap<String, Object> ChangeFamilyFreeResourceShareAddImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
		HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** ChangeFamilyFreeResourceShareAddImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String serviceNumber = request.get("ServiceNumber").toString();
			
			String groupId = request.get("GroupdID").toString();
			
			String resourceType = request.get("ResourceType").toString();
			
			String limitUnit = request.get("LimitUnit").toString();
			
			String limitValue = request.get("LimitValue").toString();
			
			String actionType = request.get("ActionType").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("serviceNumber :"+ serviceNumber);
			log.info("groupId :"+ groupId);
			log.info("resourceType :"+ resourceType);
			log.info("limitUnit :"+ limitUnit);
			log.info("limitValue :"+ limitValue);
			log.info("actionType :"+ actionType);
			
						
			String requestXmlData = Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_ADD_XML_DATA.replace(Config.SERVICE_NUMBER,serviceNumber)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.GROUP_ID, groupId)
									.replace(Config.RESOURCE_TYPE, resourceType).replace(Config.LIMIT_UNIT, limitUnit)
									.replace(Config.LIMIT_VALUE, limitValue).replace(Config.ACTION_TYPE, actionType);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_URL, requestXmlData, Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_URL, requestXmlData, Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_SOAP_ACTION_NAME, log);

            }
			
			
			log.info("outputData : "+ outputData);
			
			Date endDate = new Date();	        
		    log.info("API Request end Time :"+ new Timestamp(endDate.getTime()));
			
		    respJson = this.convertJsonObject(outputData,log);
					    
		    respJson.put("StatusCode", "200");
		    respJson.put("StatusDescription", "Success");
		
		    return respJson;

	}
	
	
	public HashMap<String, Object> ChangeFamilyFreeResourceShareModifyImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
		HashMap<String, Object> respJson = new HashMap<String, Object>();

			
			log.info("***** ChangeFamilyFreeResourceShareModifyImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			String serviceNumber = request.get("ServiceNumber").toString();
			
			String groupId = request.get("GroupdID").toString();
			
			String resourceType = request.get("ResourceType").toString();
			
			String limitUnit = request.get("LimitUnit").toString();
			
			String limitValue = request.get("LimitValue").toString();
			
			String actionType = request.get("ActionType").toString();
			
			String RelationKey = request.get("RelationKey").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("serviceNumber :"+ serviceNumber);
			log.info("groupId :"+ groupId);
			log.info("resourceType :"+ resourceType);
			log.info("limitUnit :"+ limitUnit);
			log.info("limitValue :"+ limitValue);
			log.info("actionType :"+ actionType);
			log.info("RelationKey :"+ RelationKey);
			
						
			String requestXmlData = Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_MODIFY_XML_DATA.replace(Config.SERVICE_NUMBER,serviceNumber)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.GROUP_ID, groupId)
									.replace(Config.RESOURCE_TYPE, resourceType).replace(Config.LIMIT_UNIT, limitUnit)
									.replace(Config.LIMIT_VALUE, limitValue).replace(Config.ACTION_TYPE, actionType).replace(Config.RELATION_KEY, RelationKey);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_URL, requestXmlData, Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_URL, requestXmlData, Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_SOAP_ACTION_NAME, log);

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
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyFreeResourceShareRspMsg").has("fam:AddFamilyGroupMemberRspBody")) {
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyFreeResourceShareRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyFreeResourceShareRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetMsg"));
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyFreeResourceShareRspMsg").getJSONObject("fam:AddFamilyGroupMemberRspBody").has("fam:ProcessingResult")) {
				jsonObjres.put("ProcessingResult", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyFreeResourceShareRspMsg").getJSONObject("fam:AddFamilyGroupMemberRspBody").get("fam:ProcessingResult"));
			}else {
				jsonObjres.put("ProcessingResult", Config.NILL_VALUE);
			}
			
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyFreeResourceShareRspMsg").getJSONObject("fam:AddFamilyGroupMemberRspBody").has("fam:Description")) {
				jsonObjres.put("Description", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyFreeResourceShareRspMsg").getJSONObject("fam:AddFamilyGroupMemberRspBody").get("fam:Description"));
			}else {
				jsonObjres.put("Description", Config.NILL_VALUE);
			}
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyFreeResourceShareRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyFreeResourceShareRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetMsg"));
			jsonObjres.put("ProcessingResult", Config.NILL_VALUE);
			jsonObjres.put("Description", Config.NILL_VALUE);
		}
		
		return jsonObjres;
	}





}
