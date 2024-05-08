package com.api.servicecall;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


import org.slf4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.api.Connection.GethttpConnection;
import com.api.Connection.GethttpsConnection;
import com.api.configuration.Config;

public class QueryRechargeLog {

	public HashMap<String, Object> queryRechargeLogImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
			HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** queryRechargeLogImp *****");
			
			String msgSquence = request.get("MSGSequence").toString();
			
			String customerCode = request.get("CustomerCode").toString();
			
			String endDate = request.get("EndDate").toString();
			
			
			SimpleDateFormat transactiondatetime_formate = new SimpleDateFormat("yyyyMMdd");

			String tempEndDate = transactiondatetime_formate.format(new Date());
			
			endDate = tempEndDate+"000000";
			
			String startDate =  request.get("StartDate").toString();// static from xml
			
			String servivceNumber =  request.get("servivceNumber").toString();
			
//			Calendar calendar = Calendar.getInstance();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//			System.out.println(formatter.format(calendar.getTime()));
//			String endDate = formatter.format(calendar.getTime());
//			
//			calendar = Calendar.getInstance();
//			calendar.add(Calendar.DATE, -7);
//			System.out.println("Date = "+ calendar.getTime());
//			String startDate = formatter.format(calendar.getTime());
			
			log.info("msgSquence :"+ msgSquence);
			log.info("customerCode :"+ customerCode);
			log.info("endDate :"+ endDate);
			log.info("startDate :"+ startDate);
			log.info("servivceNumber :"+ servivceNumber);
			
			String requestXmlData = Config.QUERY_RECHARGE_LOG_XML_DATA.replace(Config.CUSTOMER_CODE,customerCode).replace(Config.MSG_SEQUENCE, msgSquence)
					.replace(Config.END_TIME, endDate).replace(Config.SERVICE_NUMBER, servivceNumber);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.QUERY_RECHARGE_LOG_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDateTemp = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDateTemp.getTime()));

			String outputData = null;
			
//			if ("https".equals(requestUrl.getProtocol())) { 
//				
//				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
//				outputData = oGethttpsConnection.postRequest(Config.QUERY_RECHARGE_LOG_URL, requestXmlData, Config.QUERY_RECHARGE_LOG_SOAP_ACTION_NAME, log);
//				
//            } else if ("http".equals(requestUrl.getProtocol())) {
//            	
//    			GethttpConnection oGethttpConnection = new GethttpConnection();
//    			outputData = oGethttpConnection.postRequest(Config.QUERY_RECHARGE_LOG_URL, requestXmlData, Config.QUERY_RECHARGE_LOG_SOAP_ACTION_NAME, log);
//
//            }
			
			
			log.info("outputData : "+ outputData);
			
			Date endDateTemp = new Date();	        
		    log.info("API Request end Time :"+ new Timestamp(endDateTemp.getTime()));
			
//		    respJson = this.convertJsonObject(outputData,log);
					    
		    respJson.put("StatusCode", "200");
		    respJson.put("StatusDescription", "Success");
		
		    return respJson;
		    
		

	}
	
	
	public HashMap<String, Object> convertJsonObject(String outputData,Logger log) throws Exception
	{
		JSONObject jsonObj = XML.toJSONObject(outputData);
		
		HashMap<String, Object> jsonObjres=new HashMap<>();
		
		List<Object> jArray = new ArrayList<>();
		
		log.debug("convertJsonObject");
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryRechargeLogResultMsg").has("ResultHeader")){
			
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryRechargeLogResultMsg").getJSONObject("ResultHeader").get("cbs:ResultCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryRechargeLogResultMsg").getJSONObject("ResultHeader").get("cbs:ResultDesc"));
			
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryRechargeLogResultMsg").has("QueryRechargeLogResult"))
			{
				
				if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryRechargeLogResultMsg").getJSONObject("QueryRechargeLogResult").has("ars:RechargeInfo"))
				{
					JSONObject dataObject = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryRechargeLogResultMsg").getJSONObject("QueryRechargeLogResult").optJSONObject("ars:RechargeInfo");
			
					
					if (dataObject != null) {
						
						
						JSONObject RechargeInfo = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryRechargeLogResultMsg").getJSONObject("QueryRechargeLogResult").getJSONObject("ars:RechargeInfo");
						
						HashMap<String, Object> jobj = new HashMap<>();
			
						jobj.put("TradeTime", RechargeInfo.get("ars:TradeTime"));
							
						double rechargeAmt = (int) RechargeInfo.get("ars:RechargeAmount");
							
						double amount = rechargeAmt/10000;
							
						jobj.put("RechargeAmount", amount);
							
						jArray.add(jobj);
						
						jsonObjres.put("RechargeInfo", jArray);
						
					}
					else
					{
						
						
						JSONArray RechargeInfo = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryRechargeLogResultMsg").getJSONObject("QueryRechargeLogResult").getJSONArray("ars:RechargeInfo");
						
						
						for(int i=0;i<RechargeInfo.length();i++) {
							
							JSONObject tempjobj = RechargeInfo.getJSONObject(i);
							
							HashMap<String, Object> jobj = new HashMap<>();
							
							jobj.put("TradeTime", tempjobj.get("ars:TradeTime"));
							
							double rechargeAmt = (int) tempjobj.get("ars:RechargeAmount");
							
							double amount = rechargeAmt/10000;
							
							jobj.put("RechargeAmount", amount);
							
							jArray.add(jobj);
						}
						
						jsonObjres.put("RechargeInfo", jArray);
						
					}
				}else
				{
					jsonObjres.put("RechargeInfo", jArray);

				}
			}else
			{
				jsonObjres.put("RechargeInfo", jArray);

			}
				
		}else {
			
			jsonObjres.put("RetCode", Config.NILL_VALUE);
			jsonObjres.put("RetMsg", Config.NILL_VALUE);
			jsonObjres.put("RechargeInfo", jArray);
			
		}
		
		return jsonObjres;
}







}
