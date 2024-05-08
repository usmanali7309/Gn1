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

public class QueryUnbilledAmount {

	public HashMap<String, Object> queryUnbilledAmountImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
			HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** queryUnbilledAmountImp *****");
			
			
			String MSGSequence = request.get("MSGSequence").toString();
			
			String InputNumber = request.get("InputNumber").toString();
			
			String InputType = request.get("InputType").toString();
			
			String tagAndVaue = "";
			
			
			if(InputType.equalsIgnoreCase("ServiceNumber"))
			{
				tagAndVaue ="<bbc:PrimaryIdentity>[SERVICE_NUMBER]</bbc:PrimaryIdentity>";
			}
			else if(InputType.equalsIgnoreCase("AccountNumber"))
			{
				tagAndVaue ="<bbc:AccountCode>[SERVICE_NUMBER]</bbc:AccountCode>";
			}
			
			log.info("transactionId :"+ MSGSequence);
			log.info("InputNumber :"+ InputNumber);
			
			String requestXmlData = Config.QUERY_UNBILLED_AMOUNT_XML_DATA.replace(Config.TAG_AND_VALUE,tagAndVaue)
									.replace(Config.MSG_SEQUENCE, MSGSequence).replace(Config.SERVICE_NUMBER, InputNumber);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.QUERY_UNBILLED_AMOUNT_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
//			if ("https".equals(requestUrl.getProtocol())) { 
//				
//				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
//				outputData = oGethttpsConnection.postRequest(Config.QUERY_UNBILLED_AMOUNT_URL, requestXmlData, Config.QUERY_UNBILLED_AMOUNT_SOAP_ACTION_NAME, log);
//				
//            } else if ("http".equals(requestUrl.getProtocol())) {
//            	
//    			GethttpConnection oGethttpConnection = new GethttpConnection();
//    			outputData = oGethttpConnection.postRequest(Config.QUERY_UNBILLED_AMOUNT_URL, requestXmlData, Config.QUERY_UNBILLED_AMOUNT_SOAP_ACTION_NAME, log);
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
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("bbs:QueryUnbilledAmountResultMsg").has("QueryUnbilledAmountResult")) {
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("bbs:QueryUnbilledAmountResultMsg").getJSONObject("ResultHeader").get("cbs:ResultCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("bbs:QueryUnbilledAmountResultMsg").getJSONObject("ResultHeader").get("cbs:ResultDesc"));
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("bbs:QueryUnbilledAmountResultMsg").getJSONObject("QueryUnbilledAmountResult").has("bbs:UnbilledAmountList")) {
				
			
			JSONObject dataObject = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("bbs:QueryUnbilledAmountResultMsg").getJSONObject("QueryUnbilledAmountResult").optJSONObject("bbs:UnbilledAmountList");

		    
		    if (dataObject != null) {
		    	
		    	JSONObject UnbilledAmountList = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("bbs:QueryUnbilledAmountResultMsg").getJSONObject("QueryUnbilledAmountResult").getJSONObject("bbs:UnbilledAmountList");
				
				double chargeAmt= 0;
				double adjestAmt= 0;
			
					if(UnbilledAmountList.getJSONObject("bbs:UnbilledInfo").has("bbs:NRecurringCharge") && UnbilledAmountList.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:NRecurringCharge").has("bbs:ChargeAmount")) {
						chargeAmt=chargeAmt+UnbilledAmountList.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:NRecurringCharge").getLong("bbs:ChargeAmount");
					}
					
					if(UnbilledAmountList.getJSONObject("bbs:UnbilledInfo").has("bbs:UsageCharge") && UnbilledAmountList.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:UsageCharge").has("bbs:ChargeAmount")) {
						chargeAmt=chargeAmt+UnbilledAmountList.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:UsageCharge").getLong("bbs:ChargeAmount");
					}
					
					if(UnbilledAmountList.getJSONObject("bbs:UnbilledInfo").has("bbs:RecurringCharge") && UnbilledAmountList.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:RecurringCharge").has("bbs:ChargeAmount")) {
						chargeAmt=chargeAmt+UnbilledAmountList.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:RecurringCharge").getLong("bbs:ChargeAmount");
					}
					
					//not in use
					if(UnbilledAmountList.getJSONObject("bbs:UnbilledInfo").has("bbs:Adjustment") && UnbilledAmountList.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:Adjustment").has("bbs:AdjustAmount")) {
						adjestAmt=adjestAmt+UnbilledAmountList.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:Adjustment").getLong("bbs:AdjustAmount");
					}
						
				double UnbilledAmount = (chargeAmt)/10000;
				
				jsonObjres.put("UnbilledAmount",UnbilledAmount);
		    	
		    }else
		    {
		    	JSONArray UnbilledAmountList = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("bbs:QueryUnbilledAmountResultMsg").getJSONObject("QueryUnbilledAmountResult").getJSONArray("bbs:UnbilledAmountList");
				
				long chargeAmt= 0;
				long adjestAmt= 0;
				
				for (int i=0;i<UnbilledAmountList.length();i++)
				{
					
					JSONObject rec = UnbilledAmountList.getJSONObject(i);
					
					if(rec.getJSONObject("bbs:UnbilledInfo").has("bbs:NRecurringCharge") && rec.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:NRecurringCharge").has("bbs:ChargeAmount")) {
						chargeAmt=chargeAmt+rec.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:NRecurringCharge").getLong("bbs:ChargeAmount");
					}
					
					if(rec.getJSONObject("bbs:UnbilledInfo").has("bbs:UsageCharge") && rec.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:UsageCharge").has("bbs:ChargeAmount")) {
						chargeAmt=chargeAmt+rec.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:UsageCharge").getLong("bbs:ChargeAmount");
					}
					
					if(rec.getJSONObject("bbs:UnbilledInfo").has("bbs:RecurringCharge") && rec.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:RecurringCharge").has("bbs:ChargeAmount")) {
						chargeAmt=chargeAmt+rec.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:RecurringCharge").getLong("bbs:ChargeAmount");
					}
					
					//not in use
					if(rec.getJSONObject("bbs:UnbilledInfo").has("bbs:Adjustment") && rec.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:Adjustment").has("bbs:AdjustAmount")) {
						adjestAmt=adjestAmt+rec.getJSONObject("bbs:UnbilledInfo").getJSONObject("bbs:Adjustment").getLong("bbs:AdjustAmount");
					}
					
				  }
				
				//double UnbilledAmount = (chargeAmt+adjestAmt)/10000;
				
				double UnbilledAmount = (chargeAmt)/10000;
				
				jsonObjres.put("UnbilledAmount",UnbilledAmount);
		    }
			
			     
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("bbs:QueryUnbilledAmountResultMsg").getJSONObject("ResultHeader").get("cbs:ResultCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("bbs:QueryUnbilledAmountResultMsg").getJSONObject("ResultHeader").get("cbs:ResultDesc"));
			jsonObjres.put("UnbilledAmount","0");
		}
		
		
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("bbs:QueryUnbilledAmountResultMsg").getJSONObject("ResultHeader").get("cbs:ResultCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("bbs:QueryUnbilledAmountResultMsg").getJSONObject("ResultHeader").get("cbs:ResultDesc"));
			jsonObjres.put("UnbilledAmount",Config.NILL_VALUE);	
		}
		
		return jsonObjres;
}



}
