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

public class DeleteFamilyGroupMember {

	public HashMap<String, Object> DeleteFamilyGroupMemberImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
		HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** DeleteFamilyGroupMemberImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String serviceNumber = request.get("ServiceNumber").toString();
			
			String groupId = request.get("GroupdID").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("serviceNumber :"+ serviceNumber);
			log.info("groupId :"+ groupId);			
						
			String requestXmlData = Config.DELETE_FAMILY_GROUP_MEMBER_XML_DATA.replace(Config.SERVICE_NUMBER,serviceNumber)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.GROUP_ID, groupId);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.DELETE_FAMILY_GROUP_MEMBER_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.DELETE_FAMILY_GROUP_MEMBER_URL, requestXmlData, Config.DELETE_FAMILY_GROUP_MEMBER_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.DELETE_FAMILY_GROUP_MEMBER_URL, requestXmlData, Config.DELETE_FAMILY_GROUP_MEMBER_SOAP_ACTION_NAME, log);

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
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:DeleteFamilyGroupMemberRspMsg").has("fam:DeleteFamilyGroupMemberRspBody")) {
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:DeleteFamilyGroupMemberRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:DeleteFamilyGroupMemberRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetMsg"));
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:DeleteFamilyGroupMemberRspMsg").getJSONObject("fam:DeleteFamilyGroupMemberRspBody").has("fam:ProcessingResult")) {
				jsonObjres.put("ProcessingResult", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:DeleteFamilyGroupMemberRspMsg").getJSONObject("fam:DeleteFamilyGroupMemberRspBody").get("fam:ProcessingResult"));
			}else {
				jsonObjres.put("ProcessingResult", Config.NILL_VALUE);
			}
			
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:DeleteFamilyGroupMemberRspMsg").getJSONObject("fam:DeleteFamilyGroupMemberRspBody").has("fam:Description")) {
				jsonObjres.put("Description", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:DeleteFamilyGroupMemberRspMsg").getJSONObject("fam:DeleteFamilyGroupMemberRspBody").get("fam:Description"));
			}else {
				jsonObjres.put("Description", Config.NILL_VALUE);
			}
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:DeleteFamilyGroupMemberRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:DeleteFamilyGroupMemberRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetMsg"));
			jsonObjres.put("ProcessingResult", Config.NILL_VALUE);
			jsonObjres.put("Description", Config.NILL_VALUE);
		}
		
		return jsonObjres;
	}






}
