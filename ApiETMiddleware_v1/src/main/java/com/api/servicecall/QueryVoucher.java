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

public class QueryVoucher {

	public HashMap<String, Object> queryVoucherImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
			HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** queryVoucherImp *****");
			
			String msgSquence = request.get("MSGSequence").toString();
			
			String languageCode = request.get("LanguageCode").toString();
			
			String serialNo = request.get("SerialNo").toString();
			
			
			log.info("msgSquence :"+ msgSquence);
			log.info("languageCode :"+ languageCode);
			log.info("serialNo :"+ serialNo);
			
			String requestXmlData = Config.QUERY_VOUCHER_XML_DATA.replace(Config.LANGUAGE_CODE,languageCode).replace(Config.MSG_SEQUENCE, msgSquence)
					.replace(Config.SERIAL_NO, serialNo);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.QUERY_VOUCHER_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
//			if ("https".equals(requestUrl.getProtocol())) { 
//				
//				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
//				outputData = oGethttpsConnection.postRequest(Config.QUERY_VOUCHER_URL, requestXmlData, Config.QUERY_VOUCHER_SOAP_ACTION_NAME, log);
//				
//            } else if ("http".equals(requestUrl.getProtocol())) {
//            	
//    			GethttpConnection oGethttpConnection = new GethttpConnection();
//    			outputData = oGethttpConnection.postRequest(Config.QUERY_VOUCHER_URL, requestXmlData, Config.QUERY_VOUCHER_SOAP_ACTION_NAME, log);
//
//            }
			
			
			log.info("outputData : "+ outputData);
			
			Date endDate = new Date();	        
		    log.info("API Request end Time :"+ new Timestamp(endDate.getTime()));
			
//		    respJson = this.convertJsonObject(outputData,log);
					    
		    respJson.put("StatusCode", "200");
		    respJson.put("StatusDescription", "Success");
		
		    return respJson;
		    
		

	}
	
	
	public HashMap<String, Object> convertJsonObject(String outputData,Logger log) throws Exception
	{
		
		
		JSONObject jsonObj = XML.toJSONObject(outputData);
		
		HashMap<String, Object> jsonObjres=new HashMap<>();
		
		log.debug("convertJsonObject");
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").has("soapenv:Fault")) {
			
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("soapenv:Fault").get("faultcode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("soapenv:Fault").get("faultstring"));
			jsonObjres.put("HotCardFlag",Config.NILL_VALUE);
			
		}else if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("uvc:QueryVoucherResultMsg").getJSONObject("QueryVoucherResult").has("uvc:VoucherInfo")) {
			
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("uvc:QueryVoucherResultMsg").getJSONObject("ResultHeader").get("uvc1:ResultCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("uvc:QueryVoucherResultMsg").getJSONObject("ResultHeader").get("uvc1:ResultDesc"));
			jsonObjres.put("HotCardFlag", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("uvc:QueryVoucherResultMsg").getJSONObject("QueryVoucherResult").getJSONObject("uvc:VoucherInfo").get("uvc2:HotCardFlag"));
		}else {
			
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("uvc:QueryVoucherResultMsg").getJSONObject("ResultHeader").get("uvc1:ResultCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("uvc:QueryVoucherResultMsg").getJSONObject("ResultHeader").get("uvc1:ResultDesc"));
			jsonObjres.put("HotCardFlag",Config.NILL_VALUE);
		}
		
		return jsonObjres;
	}






}
