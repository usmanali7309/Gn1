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

public class GetGroupMemberData {

	public HashMap<String, Object> GetGroupMemberDataImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
			HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** GetGroupMemberDataImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			FileOperations ofile= new FileOperations();
			
			transactionId = ofile.getUUID();
			
			String groupId = request.get("GroupId").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("groupId :"+ groupId);
						
			String requestXmlData = Config.GET_GROUP_MEMBER_DATA_XML_DATA.replace(Config.GROUP_ID,groupId)
									.replace(Config.TRANSACTION_ID, transactionId);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.GET_GROUP_MEMBER_DATA_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.GET_GROUP_MEMBER_DATA_URL, requestXmlData, Config.GET_GROUP_MEMBER_DATA_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.GET_GROUP_MEMBER_DATA_URL, requestXmlData, Config.GET_GROUP_MEMBER_DATA_SOAP_ACTION_NAME, log);

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
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").has("quer:GetGroupMemberDataBody")) {
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
		
		
				if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:GetGroupMemberDataBody").has("quer:GetGroupDataList") &&
						jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:GetGroupMemberDataBody").getJSONObject("quer:GetGroupDataList").has("quer:GroupMembers") && 
						jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:GetGroupMemberDataBody").getJSONObject("quer:GetGroupDataList").getJSONObject("quer:GroupMembers").has("quer:MemberSubscriberList")){
				
					
					    
					    JSONObject dataObject = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:GetGroupMemberDataBody").getJSONObject("quer:GetGroupDataList").getJSONObject("quer:GroupMembers").optJSONObject("quer:MemberSubscriberList");

					    
					    if (dataObject != null) {

					    	JSONObject memberListObj = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:GetGroupMemberDataBody").getJSONObject("quer:GetGroupDataList").getJSONObject("quer:GroupMembers").getJSONObject("quer:MemberSubscriberList");
							
							List<HashMap<String, Object>> memberObjList = new ArrayList<HashMap<String, Object>>();
							
							HashMap<String, Object> memberObjMap = new HashMap<String, Object>();
							memberObjMap.put("MemberSubsId", memberListObj.get("bas:MemberSubsId"));
							memberObjMap.put("MemberServiceNumber", memberListObj.get("bas:MemberServiceNumber"));
							memberObjList.add(memberObjMap);						
							jsonObjres.put("MemberList",memberObjList);

					    } else {

					    	JSONArray memberListArray = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:GetGroupMemberDataBody").getJSONObject("quer:GetGroupDataList").getJSONObject("quer:GroupMembers").getJSONArray("quer:MemberSubscriberList");
							
							List<HashMap<String, Object>> memberObjList = new ArrayList<HashMap<String, Object>>();
													
							for(int i=0;i<memberListArray.length();i++) {
							JSONObject memberObj =memberListArray.getJSONObject(i);
							HashMap<String, Object> memberObjMap = new HashMap<String, Object>();
							memberObjMap.put("MemberSubsId", memberObj.get("bas:MemberSubsId"));
							memberObjMap.put("MemberServiceNumber", memberObj.get("bas:MemberServiceNumber"));
							memberObjList.add(memberObjMap);
							}
							
							jsonObjres.put("MemberList",memberObjList);
					    }
					    
					    
			
						
				}else {
					jsonObjres.put("MemberList",Config.NILL_VALUE);
				}
		
		}else {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
			jsonObjres.put("MemberList",Config.NILL_VALUE);
		}
		
		return jsonObjres;
}


	
	public HashMap<String, Object> GetGroupMemberGroupIdImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
		   HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** GetGroupMemberGroupIdImp *****");
			
			
			String transactionId = request.get("TransactionId").toString();
			
			String serviceNumber = request.get("ServiceNumber").toString();
			
			log.info("transactionId :"+ transactionId);
			log.info("serviceNumber :"+ serviceNumber);
			
						
			String requestXmlData = Config.GET_GROUP_MEMBER_GROUP_ID_XML_DATA.replace(Config.SERVICE_NUMBER,serviceNumber)
									.replace(Config.TRANSACTION_ID, transactionId);
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.GET_GROUP_MEMBER_DATA_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDate = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDate.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.GET_GROUP_MEMBER_DATA_URL, requestXmlData, Config.GET_GROUP_MEMBER_DATA_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.GET_GROUP_MEMBER_DATA_URL, requestXmlData, Config.GET_GROUP_MEMBER_DATA_SOAP_ACTION_NAME, log);

            }
			
			
			log.info("outputData : "+ outputData);
			
			Date endDate = new Date();	        
		    log.info("API Request end Time :"+ new Timestamp(endDate.getTime()));
			
		    respJson = this.convertGetGroupMemberGroupId(outputData,log);
					    
		    respJson.put("StatusCode", "200");
		    respJson.put("StatusDescription", "Success");
		
		    return respJson;

	}
	
	
	public HashMap<String, Object> convertGetGroupMemberGroupId(String outputData,Logger log) throws Exception
	{
		JSONObject jsonObj = XML.toJSONObject(outputData);
		
		HashMap<String, Object> jsonObjres=new HashMap<>();
		
		log.debug("convertJsonObject");
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").has("quer:GetGroupMemberDataBody")) {
		
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
		
		
		
		JSONObject dataObject = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:GetGroupMemberDataBody").optJSONObject("quer:GetGroupDataList");

		 if (dataObject != null) {
			 
			 
			 	//System.out.println("list");
				
			 	JSONObject oInfo = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:GetGroupMemberDataBody").getJSONObject("quer:GetGroupDataList");
				
				List<HashMap<String, Object>> jArray = new ArrayList<HashMap<String, Object>>();

		
					HashMap<String, Object> jobj = new HashMap<>();
					
					String groupid = oInfo.get("quer:GroupId").toString();
					String groupNo = oInfo.get("quer:GroupNo").toString();

					
					
					JSONObject OfferingObj = oInfo.getJSONObject("quer:PrimaryOffering").getJSONObject("bas:OfferingId");
					
					String offeringId = OfferingObj.get("bas:OfferingId").toString();
					
					
					JSONObject MemberTypeObj = oInfo.getJSONObject("quer:GroupMembers").getJSONObject("quer:MemberSubscriberList");
					
					
					String MemberType = MemberTypeObj.get("bas:MemberType").toString();

					jobj.put("GroupId", groupid);
					jobj.put("GroupNo", groupNo);
					jobj.put("MemberType", MemberType);
					jobj.put("OfferingId", offeringId);
					
					jArray.add(jobj);
					
			
			if(jArray!=null && jArray.size()!=0) {
				jsonObjres.put("GetGroupDataList", jArray);
				}else {
					jsonObjres.put("GetGroupDataList",jArray);
				}
			 
		 
			 
			 
		 }else
		 {
			 
			 //System.out.println("list");
				
		    	JSONArray InvoiceInfo = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:GetGroupMemberDataBody").getJSONArray("quer:GetGroupDataList");
				
				List<HashMap<String, Object>> jArray = new ArrayList<HashMap<String, Object>>();

		
				for(int i=0;i<InvoiceInfo.length();i++) {
					
					 HashMap<String, Object> jobj = new HashMap<>();
					
					JSONObject tempjobj = InvoiceInfo.getJSONObject(i);
					
					
					String groupid = tempjobj.get("quer:GroupId").toString();
					String groupNo = tempjobj.get("quer:GroupNo").toString();

					
					JSONObject productIdObj = tempjobj.getJSONObject("quer:PrimaryOffering").getJSONObject("bas:OfferingId");
					
					String offeringId = productIdObj.get("bas:OfferingId").toString();
					
					
					JSONObject MemberTypeObj = tempjobj.getJSONObject("quer:GroupMembers").getJSONObject("quer:MemberSubscriberList");
					
					
					String MemberType = MemberTypeObj.get("bas:MemberType").toString();

					jobj.put("GroupId", groupid);
					jobj.put("GroupNo", groupNo);
					jobj.put("MemberType", MemberType);
					jobj.put("OfferingId", offeringId);
					
					jArray.add(jobj);
					
				
					
				  
			}
			 
			if(jArray!=null && jArray.size()!=0) {
				jsonObjres.put("GetGroupDataList", jArray);
				}else {
					jsonObjres.put("GetGroupDataList",jArray);
				}
		 }
		 
		}else {
			List<HashMap<String, Object>> jArray = new ArrayList<HashMap<String, Object>>();

			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetGroupMemberDataResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
			jsonObjres.put("GetGroupDataList",jArray);
		}
		
		return jsonObjres;
}


}
