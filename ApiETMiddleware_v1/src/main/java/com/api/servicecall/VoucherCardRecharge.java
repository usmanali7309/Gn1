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
import com.api.util.AESEcription;

public class VoucherCardRecharge {
	
	public HashMap<String, Object> voucherCardRechargeImp(LinkedHashMap<String,Object> request,Logger log) throws Exception
	{

		HashMap<String, Object> respJson = new HashMap<String, Object>();;
			
		
			log.info("***** voucherCardRechargeImp *****" );
			
			
			String MessageSeq = request.get("MessageSeq").toString();

			String ServiceNumber = request.get("ServiceNumber").toString();
			

			String CardPinNumbertemp = request.get("CardPinNumber").toString();
			
			
			AESEcription oAESEcription = new AESEcription();
			
		
			String CardPinNumber= oAESEcription.encrypt(CardPinNumbertemp, Config.ENCRYPTION_KEY);
			
						
			String requestXmlData = Config.VOUCHER_CARD_RECHARGE_XML_DATA.replace(Config.MSG_SEQUENCE,MessageSeq)
									.replace(Config.SERVICE_NUMBER, ServiceNumber).replace(Config.CARD_PIN_NUMBER, CardPinNumber);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.VOUCHER_CARD_RECHARGE_URL);
						
			log.info("requestUrl :"+ requestUrl);
			
		    Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));
	    	
			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
								
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.VOUCHER_CARD_RECHARGE_URL, requestXmlData, Config.VOUCHER_CARD_RECHARGE_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.VOUCHER_CARD_RECHARGE_URL, requestXmlData, Config.VOUCHER_CARD_RECHARGE_SOAP_ACTION_NAME, log);
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
		
		//System.out.println("convertJsonObject");
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:RechargeResultMsg").has("ResultHeader")) 
		{
			jsonObjres.put("ResultCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:RechargeResultMsg").getJSONObject("ResultHeader").get("cbs:ResultCode"));
		
			jsonObjres.put("ResultDesc", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:RechargeResultMsg").getJSONObject("ResultHeader").get("cbs:ResultDesc"));
			jsonObjres.put("MsgLanguageCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:RechargeResultMsg").getJSONObject("ResultHeader").get("cbs:MsgLanguageCode"));

		
		}
		else {

			jsonObjres.put("ResultCode", Config.NILL_VALUE);
			jsonObjres.put("ResultDesc", Config.NILL_VALUE);
			jsonObjres.put("MsgLanguageCode", Config.NILL_VALUE);

		}
		
		return jsonObjres;
}
	

	
}
