package com.api.servicecall;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class QueryInvoice {


	public HashMap<String, Object> queryInvoiceImp(LinkedHashMap<String,Object> request,Logger log)  throws Exception
	{
			HashMap<String, Object> respJson = new HashMap<String, Object>();
			
			log.info("***** queryInvoiceImp *****");
			
			String msgSquence = request.get("MSGSequence").toString();
			
			String InputNumber = request.get("InputNumber").toString();
			
			String InputType = request.get("InputType").toString();
			
			String queryType = request.get("QueryType").toString();
			String startDate = null;
			String endDate =null;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			
			if(queryType.equalsIgnoreCase("CurrentMonth")) {
				Calendar calendar = Calendar.getInstance();
				
				log.info(formatter.format(calendar.getTime()));
				endDate = formatter.format(calendar.getTime());
				
				calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
				log.info(formatter.format(calendar.getTime()));
				startDate = formatter.format(calendar.getTime());
			}else if(queryType.equalsIgnoreCase("LastMonth")) {
				
//				Calendar calendar = Calendar.getInstance();
//				calendar.add(Calendar.MONTH, -1);
//				calendar.set(Calendar.DATE, 1);
//				log.info(formatter.format(calendar.getTime()));
//				startDate=formatter.format(calendar.getTime());
//			
//				calendar.set(Calendar.DATE,     calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//				log.info(formatter.format(calendar.getTime()));
//				endDate=formatter.format(calendar.getTime());
				
				//startDate = "20200101000000";
				
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -1);
				calendar.set(Calendar.DATE, 1);
				log.info(formatter.format(calendar.getTime()));
			    startDate = formatter.format(calendar.getTime());
				
				SimpleDateFormat transactiondatetime_formate1 = new SimpleDateFormat("yyyyMMdd");

				String lastMonthEnddateTemp = transactiondatetime_formate1.format(new Date());
								
				endDate = lastMonthEnddateTemp+"000000";
				
				log.info("endDate lastmonth: "+ endDate);
				
			}else if(queryType.equalsIgnoreCase("LastSixMonth")) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -6);
				calendar.set(Calendar.DATE, 1);
				log.info(formatter.format(calendar.getTime()));
				startDate=formatter.format(calendar.getTime());

				calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -1);
				calendar.set(Calendar.DATE,     calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				log.info(formatter.format(calendar.getTime()));
				endDate=formatter.format(calendar.getTime());
			}
			else if(queryType.equalsIgnoreCase("QueryUnPaidBill")) {
				
				startDate	=	Config.QUERY_UNPAIDBILL_STARTTIME;	
				endDate		=	Config.QUERY_UNPAIDBILL_ENDTIME;
			}
			
			
			
			
			
			log.info("msgSquence :"+ msgSquence);
			log.info("InputNumber :"+ InputNumber);
			log.info("endDate :"+ endDate);
			log.info("startDate :"+ startDate);
			log.info("InputType :"+ InputType);
			
			String requestXmlData = null;
			
			String requestXmlDataTemp = null;
			
			if(InputType.equalsIgnoreCase("ServiceNumber"))
			{
				requestXmlDataTemp = Config.QUERY_INVOICE_SERVICE_XML_DATA.replace(Config.SERVICE_NUMBER,InputNumber).replace(Config.MSG_SEQUENCE, msgSquence)
					.replace(Config.START_TIME, startDate).replace(Config.END_TIME, endDate);
			}
			
			if(InputType.equalsIgnoreCase("AccountNumber"))
			{
				requestXmlDataTemp = Config.QUERY_INVOICE_ACCOUNT_XML_DATA.replace(Config.ACCOUNT_NUMBER,InputNumber).replace(Config.MSG_SEQUENCE, msgSquence)
					.replace(Config.START_TIME, startDate).replace(Config.END_TIME, endDate);
			}
			
			
			if(queryType.equalsIgnoreCase("LastMonth"))
			{							
				requestXmlData = requestXmlDataTemp.replace(Config.PAYMENT_TYPE, "<ars:PayType>2</ars:PayType>");
			}
			else
			{
				requestXmlData = requestXmlDataTemp.replace(Config.PAYMENT_TYPE, "");
			
			}
			
			
			
			log.info("requestXmlData :"+ requestXmlData);
			
			URL requestUrl = new URL(Config.QUERY_INVOICE_URL);
			
			log.info("requestUrl :"+ requestUrl);
			
			Date startDatetemp = new Date();	        
	    	log.info("API Request start Time :"+ new Timestamp(startDatetemp.getTime()));

			String outputData = null;
			
			if ("https".equals(requestUrl.getProtocol())) { 
				
				GethttpsConnection oGethttpsConnection = new GethttpsConnection();
				outputData = oGethttpsConnection.postRequest(Config.QUERY_INVOICE_URL, requestXmlData, Config.QUERY_INVOICE_SOAP_ACTION_NAME, log);
				
            } else if ("http".equals(requestUrl.getProtocol())) {
            	
    			GethttpConnection oGethttpConnection = new GethttpConnection();
    			outputData = oGethttpConnection.postRequest(Config.QUERY_INVOICE_URL, requestXmlData, Config.QUERY_INVOICE_SOAP_ACTION_NAME, log);

            }
			
			
			log.info("outputData : "+ outputData);
			
			Date endDateTwmp = new Date();	        
		    log.info("API Request end Time :"+ new Timestamp(endDateTwmp.getTime()));
			
//		    respJson = this.convertJsonObject(outputData,queryType,log);
					    
		    respJson.put("StatusCode", "200");
		    respJson.put("StatusDescription", "Success");
		
		    return respJson;
		    
		

	}
	
	
	public HashMap<String, Object> convertJsonObject(String outputData,String queryType,Logger log) throws Exception
	{
		JSONObject jsonObj = XML.toJSONObject(outputData);
		
		HashMap<String, Object> jsonObjres=new HashMap<>();
		
		log.debug("convertJsonObject");
		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryInvoiceResultMsg").has("QueryInvoiceResult") && 
				
			jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryInvoiceResultMsg").getJSONObject("QueryInvoiceResult").has("ars:InvoiceInfo")) {
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryInvoiceResultMsg").getJSONObject("ResultHeader").get("cbs:ResultCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryInvoiceResultMsg").getJSONObject("ResultHeader").get("cbs:ResultDesc"));
			
			
			JSONObject dataObject = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryInvoiceResultMsg").getJSONObject("QueryInvoiceResult").optJSONObject("ars:InvoiceInfo");

		    
		    if (dataObject != null) {
		       	

		    	JSONObject InvoiceInfo = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryInvoiceResultMsg").getJSONObject("QueryInvoiceResult").getJSONObject("ars:InvoiceInfo");
				
				List<HashMap<String, Object>> jArray = new ArrayList<HashMap<String, Object>>();
				
				double sumOfOpenAmount = 0;
				
				HashMap<String, Object> firstOpenAmt = new HashMap<>();
				
								
				String TransType = InvoiceInfo.get("ars:TransType").toString();
				String Status = InvoiceInfo.get("ars:Status").toString();
			
			    if(TransType.toLowerCase().equalsIgnoreCase("bll") && Status.toLowerCase().equalsIgnoreCase("o"))
				{
							if(queryType.equalsIgnoreCase("LastMonth")) {
								
								sumOfOpenAmount = sumOfOpenAmount+InvoiceInfo.getLong("ars:OpenAmount");
							}
							else if(queryType.equalsIgnoreCase("QueryUnPaidBill"))
							{
								sumOfOpenAmount = sumOfOpenAmount+InvoiceInfo.getLong("ars:OpenAmount");
							}
							else {
								//LastSixMonth
								HashMap<String, Object> jobj = new HashMap<>();
								
							    double Amount = (double) InvoiceInfo.getLong("ars:OpenAmount")/10000;

								jobj.put("OpenAmount", Amount);
								jobj.put("BillCycleBeginTime", InvoiceInfo.get("ars:BillCycleID"));
								jArray.add(jobj);
							}
							
				}else {
					   
					   if(queryType.equalsIgnoreCase("LastSixMonth") && TransType.toLowerCase().equalsIgnoreCase("bll") && Status.toLowerCase().equalsIgnoreCase("c"))
					   {
						    HashMap<String, Object> jobj = new HashMap<>();
						    
						    double Amount = (double) InvoiceInfo.getLong("ars:InvoiceAmount")/10000;

							jobj.put("OpenAmount", Amount);
							jobj.put("BillCycleBeginTime", InvoiceInfo.get("ars:BillCycleID"));
							jArray.add(jobj);
					   }
						
				}	
			    
			    
				    if(queryType.equalsIgnoreCase("LastMonth")) {
						
						double Amount = (sumOfOpenAmount)/10000;
		
						jsonObjres.put("InvoiceInfo", Amount);
					}
					else if(queryType.equalsIgnoreCase("QueryUnPaidBill"))
					{
						double Amount = (sumOfOpenAmount)/10000;
		
						jsonObjres.put("InvoiceInfo", Amount);
					}
		
					else {
						if(jArray!=null && jArray.size()!=0) {
						jsonObjres.put("InvoiceInfo", jArray);
						}else {
							jsonObjres.put("InvoiceInfo",firstOpenAmt);
						}
					}
			    

			    
		    }
		    else {
		    	
		
				
				    	JSONArray InvoiceInfo = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryInvoiceResultMsg").getJSONObject("QueryInvoiceResult").getJSONArray("ars:InvoiceInfo");
						
						List<HashMap<String, Object>> jArray = new ArrayList<HashMap<String, Object>>();
						
						double sumOfOpenAmount = 0;
						
						HashMap<String, Object> firstOpenAmt = new HashMap<>();
						
					
						for(int i=0;i<InvoiceInfo.length();i++) {
							
							JSONObject tempjobj = InvoiceInfo.getJSONObject(i);
										
							String TransType = tempjobj.get("ars:TransType").toString();
							String Status 	 = tempjobj.get("ars:Status").toString();
										
							System.out.println(TransType);
							System.out.println(Status);
							
						   if(TransType.toLowerCase().equalsIgnoreCase("bll") && Status.toLowerCase().equalsIgnoreCase("o"))
							{	
							
							   			if(queryType.equalsIgnoreCase("LastMonth")) {
											sumOfOpenAmount=sumOfOpenAmount+tempjobj.getLong("ars:OpenAmount");
										}
										else if(queryType.equalsIgnoreCase("QueryUnPaidBill"))
										{
											sumOfOpenAmount=sumOfOpenAmount+tempjobj.getLong("ars:OpenAmount");
										}
										else {
											//LastSixMonth
											HashMap<String, Object> jobj = new HashMap<>();
											
										    double Amount = (double) tempjobj.getLong("ars:OpenAmount")/10000;

											jobj.put("OpenAmount", Amount);
											jobj.put("BillCycleBeginTime", tempjobj.get("ars:BillCycleID"));
											jArray.add(jobj);
										}
										
									
							}else {
						   
								   if(queryType.equalsIgnoreCase("LastSixMonth") && TransType.toLowerCase().equalsIgnoreCase("bll") && Status.toLowerCase().equalsIgnoreCase("c"))
								   {
									    HashMap<String, Object> jobj = new HashMap<>();
									    
									    double Amount = (double) tempjobj.getLong("ars:InvoiceAmount")/10000;
									    
										jobj.put("OpenAmount", Amount);
										jobj.put("BillCycleBeginTime", tempjobj.get("ars:BillCycleID"));
										jArray.add(jobj);
								   }
									
							}	
						   			
						   
						   
						   			if(queryType.equalsIgnoreCase("LastMonth")) {
										
										double Amount = (sumOfOpenAmount)/10000;

										jsonObjres.put("InvoiceInfo", Amount);
									}
									else if(queryType.equalsIgnoreCase("QueryUnPaidBill"))
									{
										double Amount = (sumOfOpenAmount)/10000;

										jsonObjres.put("InvoiceInfo", Amount);
									}
					
									else {
										if(jArray!=null && jArray.size()!=0) {
										jsonObjres.put("InvoiceInfo", jArray);
										}else {
											jsonObjres.put("InvoiceInfo",firstOpenAmt);
										}
									}
					}
		    }
			
		}else {
			
			jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryInvoiceResultMsg").getJSONObject("ResultHeader").get("cbs:ResultCode"));
			jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("ars:QueryInvoiceResultMsg").getJSONObject("ResultHeader").get("cbs:ResultDesc"));
			jsonObjres.put("InvoiceInfo", Config.NILL_VALUE);
		}
		
		return jsonObjres;
}







}
