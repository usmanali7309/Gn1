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

public class QueryMember {

	public HashMap<String, Object> QueryMemberImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
		HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** QueryMemberImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String serviceNumber = request.get("ServiceNumber").toString();
			
			String requestTime = request.get("RequestTime").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("serviceNumber :"+ serviceNumber);
			log.info("requestTime :"+ requestTime);
			
						
			String requestXmlData = Config.QUERY_MEMBER_XML_DATA.replace(Config.SERVICE_NUMBER,serviceNumber)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.REQUEST_TIME, requestTime);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.QUERY_MEMBER_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.QUERY_MEMBER_URL, requestXmlData, Config.QUERY_MEMBER_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.QUERY_MEMBER_URL, requestXmlData, Config.QUERY_MEMBER_SOAP_ACTION_NAME, log);

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
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryMemberResponse").has("upd:QueryMemberBody")) {
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryMemberResponse").getJSONObject("upd:ResponseHeader").get("bas:RetCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryMemberResponse").getJSONObject("upd:ResponseHeader").get("bas:RetMsg"));
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryMemberResponse").getJSONObject("upd:QueryMemberBody").has("bas:MemberStatus")) {
				jsonObjres.put("MemberStatus", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryMemberResponse").getJSONObject("upd:QueryMemberBody").get("bas:MemberStatus"));
			}else {
				jsonObjres.put("MemberStatus", Config.NILL_VALUE);
			}
			
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryMemberResponse").getJSONObject("upd:QueryMemberBody").has("bas:MemberLevelID")) {
				jsonObjres.put("MemberLevelID", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryMemberResponse").getJSONObject("upd:QueryMemberBody").get("bas:MemberLevelID"));
			}else {
				jsonObjres.put("MemberLevelID", Config.NILL_VALUE);
			}
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryMemberResponse").getJSONObject("upd:ResponseHeader").get("bas:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryMemberResponse").getJSONObject("upd:ResponseHeader").get("bas:RetMsg"));
			jsonObjres.put("MemberStatus", Config.NILL_VALUE);
			jsonObjres.put("MemberLevelID", Config.NILL_VALUE);
		}
		
		return jsonObjres;
	}


}
