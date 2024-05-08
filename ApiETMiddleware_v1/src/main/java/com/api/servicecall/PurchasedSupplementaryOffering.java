package com.api.servicecall;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;

import com.api.Connection.GethttpConnection;
import com.api.Connection.GethttpsConnection;
import com.api.configuration.Config;
import com.api.util.FileOperations;

public class PurchasedSupplementaryOffering {

	

	
	public HashMap<String, Object> purchasedSupplementaryOfferingImp(LinkedHashMap<String,Object> request,Logger log) throws Exception
	{

		HashMap<String, Object> respJson = new HashMap<String, Object>();;
			
		
			log.info("***** purchasedSupplementaryOfferingImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String ReqTime = request.get("ReqTime").toString();
			
			String ServiceNumber = request.get("ServiceNumber").toString();
			
			log.info("transactionId :"+ transactionId);
						
			String requestXmlData = Config.PURCHASED_SUP_OFFERING_XML_DATA.replace(Config.REQUEST_TIME,ReqTime)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.SERVICE_NUMBER, ServiceNumber);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.PURCHASED_SUP_OFFERING_URL);
						
			log.info("requestUrl :"+ requestUrl);
			
		    Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));
	    	
			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
								
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.PURCHASED_SUP_OFFERING_URL, requestXmlData, Config.PURCHASED_SUP_OFFERING_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.PURCHASED_SUP_OFFERING_URL, requestXmlData, Config.PURCHASED_SUP_OFFERING_SOAP_ACTION_NAME, log);
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
		
		HashMap<String, Object> jsonObjres = new HashMap<>();
		
		log.debug("convertJsonObject");
		 
		List<HashMap<String, Object>> jArray = null;

		HashMap<String, Object> offerid = null;
		
		if(	jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryPurchasedSupplementaryOfferingRspMsg").has("com:RspHeader") )
		{
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryPurchasedSupplementaryOfferingRspMsg").getJSONObject("com:RspHeader").get("com:ReturnCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryPurchasedSupplementaryOfferingRspMsg").getJSONObject("com:RspHeader").get("com:ReturnMsg"));
			
			
		 	offerid = new HashMap<>();
	    	jArray = new ArrayList<HashMap<String, Object>>();
			
			if(!(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryPurchasedSupplementaryOfferingRspMsg").has("off:SupplementaryOffering")) )
			{
				System.out.println("sf");
				jsonObjres.put("OfferingId", jArray);
				return jsonObjres;
			}
			
			JSONObject dataObject = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryPurchasedSupplementaryOfferingRspMsg").optJSONObject("off:SupplementaryOffering");

		    if (dataObject != null) {
		       		    	    
	    	    	JSONObject offerIdInfo =  jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryPurchasedSupplementaryOfferingRspMsg").getJSONObject("off:SupplementaryOffering");
						
					Object name = offerIdInfo.get("off:OfferingName");
					
					Object id = offerIdInfo.getJSONObject("off:OfferingId").get("com:OfferingId");

					offerid.put("OfferingId", id + "|"+ name);	
					
					jArray.add(offerid);
					
					jsonObjres.put("OfferingId", jArray);
		
		    }else {
		    				    	
		    	JSONArray offerIdList =  jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryPurchasedSupplementaryOfferingRspMsg").getJSONArray("off:SupplementaryOffering");
		    	    	
		    	
		    	
				for(int i=0;i<offerIdList.length();i++) {
					
					offerid = new HashMap<>();
					
					JSONObject tempjobj = offerIdList.getJSONObject(i);
					
					JSONObject tempjobj1 = tempjobj.getJSONObject("off:OfferingId");

					offerid.put("OfferingId", tempjobj1.get("com:OfferingId") + "|"+ tempjobj.get("off:OfferingName"));	
					
				
					jArray.add(offerid);
					
				}
			
				
				jsonObjres.put("OfferingId", jArray);

		    }
			
		  }else {
			jArray = new ArrayList<HashMap<String, Object>>();
			jsonObjres.put("RetCode", Config.NILL_VALUE);
			jsonObjres.put("RetMsg", Config.NILL_VALUE);
			jsonObjres.put("OfferingId", jArray);
		}
		
		return jsonObjres;
}
	

	

}
