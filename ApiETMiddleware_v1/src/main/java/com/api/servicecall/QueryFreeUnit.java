package com.api.servicecall;

import java.net.URL;
import java.sql.Timestamp;
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
import com.api.util.FileOperations;

public class QueryFreeUnit {


	public HashMap<String, Object> QueryFreeUnitImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
			HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** QueryFreeUnitImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String serviceNumber = request.get("ServiceNumber").toString();
			
			String requestTime = request.get("RequestTime").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("serviceNumber :"+ serviceNumber);
			log.info("RequestTime  :"+ requestTime);
			
						
			String requestXmlData = Config.QUERY_FREE_UNIT_XML_DATA.replace(Config.SERVICE_NUMBER,serviceNumber)
									.replace(Config.TRANSACTION_ID, transactionId).replace(Config.REQUEST_TIME, requestTime);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.QUERY_FREE_UNIT_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.QUERY_FREE_UNIT_URL, requestXmlData, Config.QUERY_FREE_UNIT_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.QUERY_FREE_UNIT_URL, requestXmlData, Config.QUERY_FREE_UNIT_SOAP_ACTION_NAME, log);

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
		
		List<String> defaultList = new ArrayList<String>();
		defaultList.add("AshamTele_Voice");
		defaultList.add("AshamTele_SMS");
		defaultList.add("AshamTele_Flexi");
		defaultList.add("AshamTele_Data");
		
		List<HashMap<String, Object>> freeUnintList = new ArrayList<HashMap<String, Object>>();
		
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryFreeUnitRspMsg").has("com:RspHeader")) {
		
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryFreeUnitRspMsg").getJSONObject("com:RspHeader").get("com:ReturnCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryFreeUnitRspMsg").getJSONObject("com:RspHeader").get("com:ReturnMsg"));
			
			
			if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryFreeUnitRspMsg").has("off:FreeUnit")) {
				
				
				JSONObject dataObject = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryFreeUnitRspMsg").optJSONObject("off:FreeUnit");

			    
			    if (dataObject != null) {
			    	
			    		//json object
			    		JSONObject freeUnitObj = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryFreeUnitRspMsg").getJSONObject("off:FreeUnit");
						
							
							if(defaultList.contains(freeUnitObj.get("off:TypeName").toString())){
								HashMap<String, Object> freeUnitMap = new HashMap<String, Object>();
								freeUnitMap.put("TypeName", freeUnitObj.get("off:TypeName"));
								freeUnitMap.put("UnusedAmt", freeUnitObj.get("off:UnusedAmt"));
								freeUnintList.add(freeUnitMap);
							}
					
						
						freeUnintList=check_And_Add_Missed_Unit(defaultList,freeUnintList);
						
						jsonObjres.put("FreeUnitList",freeUnintList);
			    	
			    }else {
			    	
			    	//array
			    	JSONArray unitList = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("off:QueryFreeUnitRspMsg").getJSONArray("off:FreeUnit");
					
					for(int i=0; i<unitList.length();i++) {
						JSONObject freeUnitObj = unitList.getJSONObject(i);
						
						if(defaultList.contains(freeUnitObj.get("off:TypeName").toString())){
							HashMap<String, Object> freeUnitMap = new HashMap<String, Object>();
							freeUnitMap.put("TypeName", freeUnitObj.get("off:TypeName"));
							freeUnitMap.put("UnusedAmt", freeUnitObj.get("off:UnusedAmt"));
							freeUnintList.add(freeUnitMap);
						}
					}
					
					freeUnintList=check_And_Add_Missed_Unit(defaultList,freeUnintList);
					
					jsonObjres.put("FreeUnitList",freeUnintList);
			    }
				
				
			}else
			{
				freeUnintList = check_And_Add_Missed_Unit(defaultList,freeUnintList);
				jsonObjres.put("FreeUnitList",freeUnintList);
			}
			
			
		}else
		{
			jsonObjres.put("RetCode", Config.NILL_VALUE);
			jsonObjres.put("RetMsg", Config.NILL_VALUE);
			freeUnintList = check_And_Add_Missed_Unit(defaultList,freeUnintList);
			jsonObjres.put("FreeUnitList",freeUnintList);
		}
		
		return jsonObjres;
	}


	private List<HashMap<String, Object>> check_And_Add_Missed_Unit(List<String> defaultList, List<HashMap<String, Object>> freeUnintList) throws Exception {
		List<String> tempList = new ArrayList<String>();
		for(int i=0;i<freeUnintList.size();i++) {
			HashMap<String, Object> FreeUnitMap = freeUnintList.get(i);
			tempList.add(FreeUnitMap.get("TypeName").toString());
		}
		
		defaultList.removeAll(tempList);
		for(int i=0;i<defaultList.size();i++) {
			HashMap<String, Object> FreeUnitMap = new HashMap<String, Object>();
			FreeUnitMap.put("TypeName", defaultList.get(i));
			FreeUnitMap.put("UnusedAmt", Config.NILL_VALUE);
			freeUnintList.add(FreeUnitMap);
		}
		
		return freeUnintList;
	}


}
