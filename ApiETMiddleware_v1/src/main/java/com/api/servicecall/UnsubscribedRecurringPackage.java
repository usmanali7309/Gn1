package com.api.servicecall;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;

import com.api.Connection.GethttpConnection;
import com.api.Connection.GethttpsConnection;
import com.api.configuration.Config;
import com.api.util.FileOperations;

public class UnsubscribedRecurringPackage {


	

	
	public HashMap<String, Object> unsubscribedRecurringPackageImp(LinkedHashMap<String,Object> request,Logger log) throws Exception
	{

		HashMap<String, Object> respJson = new HashMap<String, Object>();;
			
		
			log.info("***** unsubscribedRecurringPackageImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
		
			String ReqTime = request.get("ReqTime").toString();
			
			String ServiceNumber = request.get("ServiceNumber").toString();
			
			String OfferingId = request.get("OfferingId").toString();

			String requestXmlData = Config.UNSUBSCRIBED_RECURRING_PACKAGE_XML_DATA.replace(Config.REQUEST_TIME,ReqTime).replace(Config.OFFERING_ID, OfferingId)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.SERVICE_NUMBER, ServiceNumber);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.PURCHASED_SUP_OFFERING_URL);
						
			log.info("requestUrl :"+ requestUrl);
			
		    Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));
	    	
			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
								
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.UNSUBSCRIBED_RECURRING_PACKAGE_URL, requestXmlData, Config.UNSUBSCRIBED_RECURRING_PACKAGE_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.UNSUBSCRIBED_RECURRING_PACKAGE_URL, requestXmlData, Config.UNSUBSCRIBED_RECURRING_PACKAGE_SOAP_ACTION_NAME, log);
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
		
		log.debug("convertJsonObject");
		

		if(	jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:ChangeSupplementaryOfferingRspMsg").has("com:RspHeader") )
		{
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:ChangeSupplementaryOfferingRspMsg").getJSONObject("com:RspHeader").get("com:ReturnCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:ChangeSupplementaryOfferingRspMsg").getJSONObject("com:RspHeader").get("com:ReturnMsg"));
			
		}else {
			jsonObjres.put("RetCode", Config.NILL_VALUE);
			jsonObjres.put("RetMsg", Config.NILL_VALUE);
		}
		
		return jsonObjres;
}
	

	



}
