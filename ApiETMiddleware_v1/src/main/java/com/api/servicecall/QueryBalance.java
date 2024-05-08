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

public class QueryBalance {

	public HashMap<String, Object> queryBalanceImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
			HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** queryBalanceImp *****");
			
			
			String MSGSequence = request.get("MSGSequence").toString();
			
			String serviceNumber = request.get("ServiceNumber").toString();
			
			log.info("MSGSequence :"+ MSGSequence);
			log.info("serviceNumber :"+ serviceNumber);
						
						
			String requestXmlData = Config.QUERY_BALANCE_XML_DATA.replace(Config.SERVICE_NUMBER,serviceNumber)
									.replace(Config.MSG_SEQUENCE, MSGSequence);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.QUERY_BALANCE_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.QUERY_BALANCE_URL, requestXmlData, Config.QUERY_BALANCE_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.QUERY_BALANCE_URL, requestXmlData, Config.QUERY_BALANCE_SOAP_ACTION_NAME, log);

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
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryBalanceResultMsg").has("QueryBalanceResult")) {
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryBalanceResultMsg").getJSONObject("ResultHeader").get("cbs:ResultCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryBalanceResultMsg").getJSONObject("ResultHeader").get("cbs:ResultDesc"));
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryBalanceResultMsg").getJSONObject("QueryBalanceResult").has("ars:AcctList") && 
				jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryBalanceResultMsg").getJSONObject("QueryBalanceResult").getJSONObject("ars:AcctList").has("ars:OutStandingList") &&
				jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryBalanceResultMsg").getJSONObject("QueryBalanceResult").getJSONObject("ars:AcctList").getJSONObject("ars:OutStandingList").has("ars:OutStandingDetail") &&
				jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryBalanceResultMsg").getJSONObject("QueryBalanceResult").getJSONObject("ars:AcctList").getJSONObject("ars:OutStandingList").getJSONObject("ars:OutStandingDetail").has("ars:OutStandingAmount")) {
			
			jsonObjres.put("OutStandingAmount", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryBalanceResultMsg").getJSONObject("QueryBalanceResult").getJSONObject("ars:AcctList").getJSONObject("ars:OutStandingList").getJSONObject("ars:OutStandingDetail").get("ars:OutStandingAmount"));
		}else {
			jsonObjres.put("OutStandingAmount", Config.NILL_VALUE);
		}
		
		
		
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryBalanceResultMsg").getJSONObject("ResultHeader").get("cbs:ResultCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryBalanceResultMsg").getJSONObject("ResultHeader").get("cbs:ResultDesc"));
			jsonObjres.put("OutStandingAmount",  Config.NILL_VALUE);
		}
		
		return jsonObjres;
	}





}
