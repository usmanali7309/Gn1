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

public class ChangeFamilyGroupSuppOffer 
{

	
	public HashMap<String, Object> changeFamilyGroupSuppOfferImp(LinkedHashMap<String,Object> request,Logger log) throws Exception
	{
		HashMap<String, Object> respJson = new HashMap<String, Object>();;
			
		
			log.info("***** changeFamilyGroupSuppOfferImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String GroupId = request.get("GroupId").toString();
			
			String OfferID = request.get("OfferID").toString();
			
			log.info("GroupId :"+ GroupId);
			
			log.info("transactionId :"+ transactionId);
						
			String requestXmlData = Config.CHANGE_FAMILY_GROUP_SUPP_OFFER_XML_DATA.replace(Config.GROUP_ID,GroupId)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.OFFERING_ID_VAR, OfferID);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.CHANGE_FAMILY_GROUP_SUPP_OFFER_URL);
						
			log.info("requestUrl :"+ requestUrl);
			
		    Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));
	    	
			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
								
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.CHANGE_FAMILY_GROUP_SUPP_OFFER_URL, requestXmlData, Config.CHANGE_FAMILY_GROUP_SUPP_OFFER_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.CHANGE_FAMILY_GROUP_SUPP_OFFER_URL, requestXmlData, Config.CHANGE_FAMILY_GROUP_SUPP_OFFER_SOAP_ACTION_NAME, log);
            }
			
			log.info("outputData : "+ outputData);
			
			Date endDate = new Date();	        
		    log.info("API Request end Time :"+ new Timestamp(endDate.getTime()));
			
		    respJson = this.convertJsonObject(outputData,log);
					    
		    respJson.put("StatusCode", "200");
		    respJson.put("StatusDescription", "Success");
		
		    log.info("outputData respJson : "+ respJson);
		    
		    return respJson;
		    
		

	}
	
	
	public HashMap<String, Object> convertJsonObject(String outputData,Logger log) throws Exception
	{
		JSONObject jsonObj = XML.toJSONObject(outputData);
		
		HashMap<String, Object> jsonObjres=new HashMap<>();
		
		System.out.println("convertJsonObject");
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyGroupSuppOfferRspMsg").has("fam:ResponseHeader")) 
		{
			//success case
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyGroupSuppOfferRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyGroupSuppOfferRspMsg").getJSONObject("fam:ResponseHeader").get("fam1:RetMsg"));
			
			
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyGroupSuppOfferRspMsg").has("fam:ChangeFamilyGroupSuppOfferRspBody"))
			{
				jsonObjres.put("ProcessingResult", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyGroupSuppOfferRspMsg").getJSONObject("fam:ChangeFamilyGroupSuppOfferRspBody").get("fam:ProcessingResult"));
				jsonObjres.put("Description", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("fam:ChangeFamilyGroupSuppOfferRspMsg").getJSONObject("fam:ChangeFamilyGroupSuppOfferRspBody").get("fam:Description"));
				
			}else {
				
				jsonObjres.put("ProcessingResult",Config.NILL_VALUE);
				jsonObjres.put("Description",Config.NILL_VALUE);
			}
		}
		else {

			jsonObjres.put("RetCode", Config.NILL_VALUE);
			jsonObjres.put("RetMsg", Config.NILL_VALUE);
			jsonObjres.put("ProcessingResult",Config.NILL_VALUE);
			jsonObjres.put("Description",Config.NILL_VALUE);
		}
		
		return jsonObjres;
}
	
	
}
