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

public class PresentServicegift {

	public HashMap<String, Object> PresentServicegiftImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
		HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** PresentServicegiftImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String serviceNumberA = request.get("ServiceNumberA").toString();
			
			String chargeCode = request.get("ChargeCode").toString();
			
			String ChargeAmt = request.get("ChargeAmt").toString(); 
			
			//double convertChargeAmt = Double.parseDouble(ChargeAmtTemp);  
			
			//double finalChargeAmt = (double) Math.round(convertChargeAmt * 100) / 100;

			//String ChargeAmt 	  = String.valueOf(finalChargeAmt);
			
			String offeringId = request.get("OfferingId").toString();
			
			String serviceNumberB = request.get("ServiceNumberB").toString();
			
			
			log.info("transactionId :"+ transactionId);
			log.info("serviceNumberA :"+ serviceNumberA);
			log.info("chargeCode  :"+ chargeCode);
			log.info("ChargeAmt  :"+ ChargeAmt);
			log.info("offeringId  :"+ offeringId);
			log.info("serviceNumberB  :"+ serviceNumberB);
			
			log.info("PRESENT_SERVICE_GIFT_XML_DATA  :"+ Config.PRESENT_SERVICE_GIFT_XML_DATA);
			
						
			String requestXmlData = Config.PRESENT_SERVICE_GIFT_XML_DATA.replace(Config.TRNSFR_SRC_OWNR_SRVC_NO,serviceNumberA)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.TRNSFR_DEST_OWNR_SRVC_NO, serviceNumberB).replace(Config.TRNSFR_AMT, ChargeAmt)
									.replace(Config.CHARGE_CODE, chargeCode).replace(Config.OFFERING_ID, offeringId);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.PRESENT_SERVICE_GIFT_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.PRESENT_SERVICE_GIFT_URL, requestXmlData, Config.PRESENT_SERVICE_GIFT_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.PRESENT_SERVICE_GIFT_URL, requestXmlData, Config.PRESENT_SERVICE_GIFT_SOAP_ACTION_NAME, log);

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
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("han:PresentServiceGiftResponse").has("han:ResponseHeader")) {
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("han:PresentServiceGiftResponse").getJSONObject("han:ResponseHeader").get("bas:RetCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("han:PresentServiceGiftResponse").getJSONObject("han:ResponseHeader").get("bas:RetMsg"));
		
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("han:PresentServiceGiftResponse").getJSONObject("han:ResponseHeader").get("bas:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("han:PresentServiceGiftResponse").getJSONObject("han:ResponseHeader").get("bas:RetMsg"));
			
		}
		
		return jsonObjres;
	}

}
