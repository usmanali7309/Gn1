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

public class TransferPoint {

	public HashMap<String, Object> transferPointImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
		HashMap<String, Object> respJson = new HashMap<>();
			
			log.info("***** transferPointImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String languageCode = request.get("LanguageCode").toString();
			
			String requestTime = request.get("RequestTime").toString();
			
			String trnsfrSrcOwnrSrvcNo = request.get("TransferSrcOwnerSrvcNo").toString();
			
			String trnsfrAmt = request.get("TransferAmount").toString();
			
			String trnsfrDestOwnrSrvcNo = request.get("TransferDestOwnerSrvcNo").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("languageCode :"+ languageCode);
			log.info("trnsfrSrcOwnrSrvcNo :"+ trnsfrSrcOwnrSrvcNo);
			log.info("trnsfrAmt :"+ trnsfrAmt);
			log.info("trnsfrDestOwnrSrvcNo :"+ trnsfrDestOwnrSrvcNo);
			
			String requestXmlData = Config.TRANSFER_POINT_XML_DATA.replace(Config.LANGUAGE_CODE,languageCode)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.REQUEST_TIME, requestTime)
									.replace(Config.TRNSFR_SRC_OWNR_SRVC_NO, trnsfrSrcOwnrSrvcNo).replace(Config.TRNSFR_AMT, trnsfrAmt)
									.replace(Config.TRNSFR_DEST_OWNR_SRVC_NO, trnsfrDestOwnrSrvcNo);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.TRANSFER_POINT_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.TRANSFER_POINT_URL, requestXmlData, Config.TRANSFER_POINT_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.TRANSFER_POINT_URL, requestXmlData, Config.TRANSFER_POINT_SOAP_ACTION_NAME, log);

            }
			
			
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
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:TransferPointResponse").has("upd:ResponseHeader")) {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:TransferPointResponse").getJSONObject("upd:ResponseHeader").get("bas:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:TransferPointResponse").getJSONObject("upd:ResponseHeader").get("bas:RetMsg"));
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:TransferPointResponse").getJSONObject("upd:ResponseHeader").get("bas:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:TransferPointResponse").getJSONObject("upd:ResponseHeader").get("bas:RetMsg"));
		}
		
		return jsonObjres;
	}





}
