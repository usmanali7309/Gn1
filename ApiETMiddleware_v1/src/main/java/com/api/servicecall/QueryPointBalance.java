package com.api.servicecall;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;


import org.slf4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.api.Connection.GethttpConnection;
import com.api.Connection.GethttpsConnection;
import com.api.configuration.Config;
import com.api.util.FileOperations;

public class QueryPointBalance {

	public HashMap<String, Object> queryPointBalanceImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
			HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** queryPointBalanceImp *****");
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String serviceNumber = request.get("ServiceNumber").toString();
			
			String requestTime = request.get("RequestTime").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("serviceNumber :"+ serviceNumber);
			
			String requestXmlData = Config.QUERY_POINT_BALANCE_XML_DATA.replace(Config.SERVICE_NUMBER,serviceNumber)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.REQUEST_TIME, requestTime);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.QUERY_POINT_BALANCE_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.QUERY_POINT_BALANCE_URL, requestXmlData, Config.QUERY_POINT_BALANCE_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.QUERY_POINT_BALANCE_URL, requestXmlData, Config.QUERY_POINT_BALANCE_SOAP_ACTION_NAME, log);

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
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryPointBalanceResponse").has("upd:QueryPointBalanceBody") && 
				jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryPointBalanceResponse").getJSONObject("upd:QueryPointBalanceBody").getJSONObject("upd:PointBalanceInfo").has("upd:BalanceDetail") ) {
		
		    jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryPointBalanceResponse").getJSONObject("upd:ResponseHeader").get("bas:RetCode"));
		    jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryPointBalanceResponse").getJSONObject("upd:ResponseHeader").get("bas:RetMsg"));

		
	        JSONObject dataObject = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryPointBalanceResponse").getJSONObject("upd:QueryPointBalanceBody").getJSONObject("upd:PointBalanceInfo").getJSONObject("upd:BalanceDetail").optJSONObject("upd:BalanceInfo") ;

		    if (dataObject != null) {
		    	    	
		    	jsonObjres.put("Status", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryPointBalanceResponse").getJSONObject("upd:QueryPointBalanceBody").getJSONObject("upd:PointBalanceInfo").get("upd:Status"));
		    	jsonObjres.put("TotalAmount", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryPointBalanceResponse").getJSONObject("upd:QueryPointBalanceBody").getJSONObject("upd:PointBalanceInfo").get("upd:TotalAmount"));
		    	jsonObjres.put("ExpireDate", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryPointBalanceResponse").getJSONObject("upd:QueryPointBalanceBody").getJSONObject("upd:PointBalanceInfo").getJSONObject("upd:BalanceDetail").getJSONObject("upd:BalanceInfo").get("upd:ExpireDate"));
		    	
		    }else
		    {
		    	jsonObjres.put("Status", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryPointBalanceResponse").getJSONObject("upd:QueryPointBalanceBody").getJSONObject("upd:PointBalanceInfo").get("upd:Status"));
		    	jsonObjres.put("TotalAmount", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryPointBalanceResponse").getJSONObject("upd:QueryPointBalanceBody").getJSONObject("upd:PointBalanceInfo").get("upd:TotalAmount"));
		    	
		    	JSONArray memberListArray = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryPointBalanceResponse").getJSONObject("upd:QueryPointBalanceBody").getJSONObject("upd:PointBalanceInfo").getJSONObject("upd:BalanceDetail").getJSONArray("upd:BalanceInfo");
				
				JSONObject memberObj = memberListArray.getJSONObject(0);
				
				memberObj.get("upd:ExpireDate");
						
		    	jsonObjres.put("ExpireDate", memberObj.get("upd:ExpireDate") );
	
		    }
		
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryPointBalanceResponse").getJSONObject("upd:ResponseHeader").get("bas:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("upd:QueryPointBalanceResponse").getJSONObject("upd:ResponseHeader").get("bas:RetMsg"));
			jsonObjres.put("Status", Config.NILL_VALUE);
			jsonObjres.put("TotalAmount", Config.NILL_VALUE);
			jsonObjres.put("ExpireDate", Config.NILL_VALUE);
		}
		
		return jsonObjres;
}




}
