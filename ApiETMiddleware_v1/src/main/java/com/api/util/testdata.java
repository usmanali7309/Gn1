package com.api.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;

import com.api.configuration.Config;

public class testdata {

	//private static Logger log = Logger.getLogger("ApiServiceController_log");
	
	public static String outputData1 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
			+ "   <soapenv:Header/>\r\n"
			+ "   <soapenv:Body>\r\n"
			+ "      <quer:GetCustomerResponse xmlns:quer=\"http://crm.huawei.com/query/\" xmlns:bas=\"http://crm.huawei.com/basetype/\">\r\n"
			+ "         <quer:ResponseHeader>\r\n"
			+ "            <bas:RequestHeader>\r\n"
			+ "               <bas:Version>1</bas:Version>\r\n"
			+ "               <bas:TransactionId>20221019015350</bas:TransactionId>\r\n"
			+ "               <bas:ProcessTime>20221019015350</bas:ProcessTime>\r\n"
			+ "               <bas:Language>2060</bas:Language>\r\n"
			+ "               <bas:ChannelId>62</bas:ChannelId>\r\n"
			+ "               <bas:TechnicalChannelId>1</bas:TechnicalChannelId>\r\n"
			+ "               <bas:TenantId>101</bas:TenantId>\r\n"
			+ "               <bas:AccessUser>ethioccIVR</bas:AccessUser>\r\n"
			+ "            </bas:RequestHeader>\r\n"
			+ "            <bas:RetCode>0</bas:RetCode>\r\n"
			+ "            <bas:RetMsg>success</bas:RetMsg>\r\n"
			+ "         </quer:ResponseHeader>\r\n"
			+ "         <quer:GetCustomerBody>\r\n"
			+ "            <quer:CustomerId>1101305216465</quer:CustomerId>\r\n"
			+ "            <quer:CustomerCode>883878315</quer:CustomerCode>\r\n"
			+ "            <quer:CustomerType>0</quer:CustomerType>\r\n"
			+ "            <quer:CustomerLevel>6</quer:CustomerLevel>\r\n"
			+ "            <quer:CustomerLanguage>2002</quer:CustomerLanguage>\r\n"
			+ "            <quer:TenantId>101</quer:TenantId>\r\n"
			+ "            <quer:SubscriberList>\r\n"
			+ "               <bas:SubscriberAbstractInfo>\r\n"
			+ "                  <bas:SubscriberId>2101310743016</bas:SubscriberId>\r\n"
			+ "                  <bas:ServiceNumber>962920629</bas:ServiceNumber>\r\n"
			+ "                  <bas:PaymentType>0</bas:PaymentType>\r\n"
			+ "                  <bas:DefaultAccountId>4111309226615</bas:DefaultAccountId>\r\n"
			+ "                  <bas:Status>B01</bas:Status>\r\n"
			+ "               </bas:SubscriberAbstractInfo>\r\n"
			+ "            </quer:SubscriberList>\r\n"
			+ "            <quer:Status>A02</quer:Status>\r\n"
			+ "            <quer:ExtParamList>\r\n"
			+ "               <bas:ParameterInfo>\r\n"
			+ "                  <bas:ParamName>CustomerType</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue>1</bas:ParamValue>\r\n"
			+ "               </bas:ParameterInfo>\r\n"
			+ "               <bas:ParameterInfo>\r\n"
			+ "                  <bas:ParamName>PrimaryOfferId</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue>583494505</bas:ParamValue>\r\n"
			+ "               </bas:ParameterInfo>\r\n"
			+ "               <bas:ParameterInfo>\r\n"
			+ "                  <bas:ParamName>IVR Language</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue>2002</bas:ParamValue>\r\n"
			+ "               </bas:ParameterInfo>\r\n"
			+ "            </quer:ExtParamList>\r\n"
			+ "         </quer:GetCustomerBody>\r\n"
			+ "      </quer:GetCustomerResponse>\r\n"
			+ "   </soapenv:Body>\r\n"
			+ "</soapenv:Envelope>";
	
	
	public static String outputData2  =  "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
			+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n"
			+ "      <quer:GetSubscriberResponse xmlns:quer=\"http://crm.huawei.com/query/\" xmlns:bas=\"http://crm.huawei.com/basetype/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n"
			+ "         <quer:ResponseHeader>\r\n" + "            <bas:RequestHeader>\r\n"
			+ "               <bas:TransactionId>20210526104933</bas:TransactionId>\r\n"
			+ "               <bas:ChannelId>55</bas:ChannelId>\r\n"
			+ "               <bas:TechnicalChannelId>53</bas:TechnicalChannelId>\r\n"
			+ "               <bas:TenantId>101</bas:TenantId>\r\n"
			+ "               <bas:AccessUser>ethiocc</bas:AccessUser>\r\n"
			+ "               <bas:AccessPwd xsi:nil=\"true\"/>\r\n" + "               <bas:ExtParamList>\r\n"
			+ "                  <bas:ParameterInfo>\r\n" + "                     <bas:ExtParamList/>\r\n"
			+ "                  </bas:ParameterInfo>\r\n" + "               </bas:ExtParamList>\r\n"
			+ "            </bas:RequestHeader>\r\n" + "            <bas:RetCode>0</bas:RetCode>\r\n"
			+ "            <bas:RetMsg>success</bas:RetMsg>\r\n" + "         </quer:ResponseHeader>\r\n"
			+ "         <quer:GetSubscriberBody>\r\n"
			+ "            <quer:SubscriberId>10101229502401</quer:SubscriberId>\r\n"
			+ "            <quer:AccountId>10111229414500</quer:AccountId>\r\n"
			+ "            <quer:CustomerId>1101249364365</quer:CustomerId>\r\n"
			+ "            <quer:CustomerCode>828195765</quer:CustomerCode>\r\n"
			+ "            <quer:ServiceNumber>123555137</quer:ServiceNumber>\r\n"
			+ "            <quer:SubscriberType>1</quer:SubscriberType>\r\n"
			+ "            <quer:AccountCode>628448601</quer:AccountCode>\r\n"
			+ "            <quer:NetworkType>21</quer:NetworkType>\r\n"
			+ "            <quer:IMSI>918858889586721</quer:IMSI>\r\n"
			+ "            <quer:ICCID>61985819959100206721</quer:ICCID>\r\n"
			+ "            <quer:PIN1>P0sDgVJRdy5w97lNgmv9oQ==</quer:PIN1>\r\n"
			+ "            <quer:PIN2>W4mbNf2cUw2c8BNPMQmuEQ==</quer:PIN2>\r\n"
			+ "            <quer:PUK1>/FyEVIYIvg//OV4GIHfYtw==</quer:PUK1>\r\n"
			+ "            <quer:PUK2>80jJr3oQXjCgE9gkPZTLCg==</quer:PUK2>\r\n"
			+ "            <quer:BrandId>758842420</quer:BrandId>\r\n"
			+ "            <quer:Language>2002</quer:Language>\r\n"
			+ "            <quer:WrittenLanguage>2002</quer:WrittenLanguage>\r\n"
			+ "            <quer:EffectiveDate>20210329014038</quer:EffectiveDate>\r\n"
			+ "            <quer:ExpireDate>20991231205959</quer:ExpireDate>\r\n"
			+ "            <quer:ActiveDate>20210329014230</quer:ActiveDate>\r\n"
			+ "            <quer:PrimaryOffering>\r\n" + "               <bas:OfferingId>\r\n"
			+ "                  <bas:OfferingId>383990980</bas:OfferingId>\r\n"
			+ "                  <bas:PurchaseSeq>601238405450</bas:PurchaseSeq>\r\n"
			+ "               </bas:OfferingId>\r\n"
			+ "               <bas:OfferingName>4G LTE Data (Postpaid)</bas:OfferingName>\r\n"
			+ "               <bas:Status>C01</bas:Status>\r\n"
			+ "               <bas:EffectiveTime>20210329014038</bas:EffectiveTime>\r\n"
			+ "               <bas:ExpiredTime>20991231205959</bas:ExpiredTime>\r\n"
			+ "               <bas:ProductList>\r\n" + "                  <bas:GetSubProductInfo>\r\n"
			+ "                     <bas:ProductId>1908391496</bas:ProductId>\r\n"
			+ "                     <bas:ProductName>PCRF service product</bas:ProductName>\r\n"
			+ "                     <bas:Status xsi:nil=\"true\"/>\r\n"
			+ "                  </bas:GetSubProductInfo>\r\n" + "                  <bas:GetSubProductInfo>\r\n"
			+ "                     <bas:ProductId>1010</bas:ProductId>\r\n"
			+ "                     <bas:ProductName>TransferProduct</bas:ProductName>\r\n"
			+ "                     <bas:Status xsi:nil=\"true\"/>\r\n"
			+ "                  </bas:GetSubProductInfo>\r\n" + "                  <bas:GetSubProductInfo>\r\n"
			+ "                     <bas:ProductId>1053</bas:ProductId>\r\n"
			+ "                     <bas:ProductName>DemoGSMProduct</bas:ProductName>\r\n"
			+ "                     <bas:Status>C01</bas:Status>\r\n"
			+ "                  </bas:GetSubProductInfo>\r\n" + "                  <bas:GetSubProductInfo>\r\n"
			+ "                     <bas:ProductId>1822969218</bas:ProductId>\r\n"
			+ "                     <bas:ProductName>GPRS</bas:ProductName>\r\n"
			+ "                     <bas:Status>C01</bas:Status>\r\n"
			+ "                  </bas:GetSubProductInfo>\r\n" + "               </bas:ProductList>\r\n"
			+ "               <bas:ExtParamList>\r\n" + "                  <bas:ParameterInfo>\r\n"
			+ "                     <bas:ParamName>C_DEFAULT_CURRENCY</bas:ParamName>\r\n"
			+ "                     <bas:ParamValue>1048</bas:ParamValue>\r\n"
			+ "                  </bas:ParameterInfo>\r\n" + "                  <bas:ParameterInfo>\r\n"
			+ "                     <bas:ParamName>C_PROD_SWITCH</bas:ParamName>\r\n"
			+ "                     <bas:ParamValue>151</bas:ParamValue>\r\n"
			+ "                  </bas:ParameterInfo>\r\n" + "                  <bas:ParameterInfo>\r\n"
			+ "                     <bas:ParamName>C_SUB_LATEFEE</bas:ParamName>\r\n"
			+ "                     <bas:ParamValue>218500</bas:ParamValue>\r\n"
			+ "                  </bas:ParameterInfo>\r\n" + "                  <bas:ParameterInfo>\r\n"
			+ "                     <bas:ParamName>C_SUB_NETWORKTYPE</bas:ParamName>\r\n"
			+ "                     <bas:ParamValue>3</bas:ParamValue>\r\n"
			+ "                  </bas:ParameterInfo>\r\n" + "               </bas:ExtParamList>\r\n"
			+ "               <bas:GroupMemberFlag>N</bas:GroupMemberFlag>\r\n"
			+ "            </quer:PrimaryOffering>\r\n" + "            <quer:Status>B01</quer:Status>\r\n"
			+ "            <quer:StatusReason>000000</quer:StatusReason>\r\n"
			+ "            <quer:BeId>101</quer:BeId>\r\n" + "            <quer:ExtParamList>\r\n"
			+ "               <bas:ParameterInfo>\r\n"
			+ "                  <bas:ParamName>paidType</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue>0</bas:ParamValue>\r\n" + "               </bas:ParameterInfo>\r\n"
			+ "               <bas:ParameterInfo>\r\n" + "                  <bas:ParamName>beId</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue>101</bas:ParamValue>\r\n"
			+ "               </bas:ParameterInfo>\r\n" + "               <bas:ParameterInfo>\r\n"
			+ "                  <bas:ParamName>SLAPriority</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue>4</bas:ParamValue>\r\n" + "               </bas:ParameterInfo>\r\n"
			+ "               <bas:ParameterInfo>\r\n"
			+ "                  <bas:ParamName>CallCenterAccess</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue>994</bas:ParamValue>\r\n"
			+ "               </bas:ParameterInfo>\r\n" + "               <bas:ParameterInfo>\r\n"
			+ "                  <bas:ParamName>SalesDate</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue>2021-03-29</bas:ParamValue>\r\n"
			+ "               </bas:ParameterInfo>\r\n" + "               <bas:ParameterInfo>\r\n"
			+ "                  <bas:ParamName>IVR Language</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue></bas:ParamValue>\r\n" + "               </bas:ParameterInfo>\r\n"
			+ "               <bas:ParameterInfo>\r\n"
			+ "                  <bas:ParamName>TelecomRegionId</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue></bas:ParamValue>\r\n" + "               </bas:ParameterInfo>\r\n"
			+ "               <bas:ParameterInfo>\r\n"
			+ "                  <bas:ParamName>FamilyGroupId</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue>-1</bas:ParamValue>\r\n"
			+ "               </bas:ParameterInfo>\r\n" + "               <bas:ParameterInfo>\r\n"
			+ "                  <bas:ParamName>FamilyMemberType</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue>-1</bas:ParamValue>\r\n"
			+ "               </bas:ParameterInfo>\r\n" + "               <bas:ParameterInfo>\r\n"
			+ "                  <bas:ParamName>IsVpnMemberOrSmeSub</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue>N</bas:ParamValue>\r\n" + "               </bas:ParameterInfo>\r\n"
			+ "               <bas:ParameterInfo>\r\n"
			+ "                  <bas:ParamName>VpnGroupId</bas:ParamName>\r\n"
			+ "                  <bas:ParamValue>-1</bas:ParamValue>\r\n"
			+ "               </bas:ParameterInfo>\r\n" + "            </quer:ExtParamList>\r\n"
			+ "         </quer:GetSubscriberBody>\r\n" + "      </quer:GetSubscriberResponse>\r\n"
			+ "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

	
	public testdata() {
		super();
	}

	
	public static void main(String[] args) {	
		
	
		
		testdata otest = new testdata();
		try {
			HashMap<String, Object> jsonObjres= otest.convertJsonObject1(outputData1,"QueryUnPaidBill");
		//	otest.convertJsonObject(outputData1);
			
			HashMap<String, Object> jsonObjres1 = otest.convertJsonObjectGetSubscriber(outputData2,"GetSubscriber");
			
			
			System.out.println(jsonObjres.toString());
			
			System.out.println(jsonObjres1.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}					
	
	

public HashMap<String, Object> convertJsonObject1(String outputData ,String queryType) throws Exception
{
	JSONObject jsonObj = XML.toJSONObject(outputData);
	
	HashMap<String, Object> jsonObjres=new HashMap<>();
	
	//log.debug("convertJsonObject");
	
	if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").has("quer:ResponseHeader")) {
	
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
		
		

		
		if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").has("quer:GetCustomerBody")) {
		
		jsonObjres.put("CustomerCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:CustomerCode"));
		jsonObjres.put("CustomerLanguage", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:CustomerLanguage"));
		jsonObjres.put("CustomerLevel", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:CustomerLevel"));


		
		JSONArray ParameterInfo = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").getJSONObject("quer:ExtParamList").getJSONArray("bas:ParameterInfo");
		
		
		String CustomerType = Config.NILL_VALUE;
		String CustomerCategory = Config.NILL_VALUE;
		String CustomerSubCategory = Config.NILL_VALUE;
		
		for (int i=0;i< ParameterInfo.length();i++)
		{
			
			JSONObject rec = ParameterInfo.getJSONObject(i);
			Object paramName = rec.get("bas:ParamName");
		    
		    if("CustomerType".equalsIgnoreCase(paramName.toString()))
		    {
		    	Object CustomerType1 = rec.get("bas:ParamValue");
		    	CustomerType = CustomerType1.toString();
		    	
		    }
		    
		    
		    if(CustomerType.equalsIgnoreCase("1"))
	    	{
		    	
		    	
		    	if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").has("quer:FirstName")){
		    		jsonObjres.put("FirstName", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:FirstName"));

		    	}else {
		    		jsonObjres.put("FirstName", Config.NILL_VALUE);
		    	}
		    	
		    	if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").has("quer:MiddleName")) {
		    		jsonObjres.put("MiddleName", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:MiddleName"));

		    	}else {
		    		jsonObjres.put("MiddleName", Config.NILL_VALUE);
		    	}
		    	
		    	if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").has("quer:LastName")) {
		    		jsonObjres.put("LastName", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:LastName"));

		    	}else {
		    		jsonObjres.put("LastName", Config.NILL_VALUE);
		    	}
		    	
		    	if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").has("quer:Nationality")) {
		    		jsonObjres.put("Nationality", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:Nationality"));

		    	}else {
		    		jsonObjres.put("Nationality", Config.NILL_VALUE);
		    	}
		    	
		    	if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").has("quer:Gender")) {
		    		jsonObjres.put("Gender", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetCustomerResponse").getJSONObject("quer:GetCustomerBody").get("quer:Gender"));

		    	}else {
		    		jsonObjres.put("Gender", Config.NILL_VALUE);

		    	}
		    		
		    	jsonObjres.put("BranchName",Config.NILL_VALUE);
	    	}
	    	
	    	if(CustomerType.equalsIgnoreCase("2"))
	    	{
	    		 if("Enterprise Customer Name".equalsIgnoreCase(paramName.toString()))
	 		    {
	    			Object CustomerType1 = rec.get("bas:ParamValue");
	 		    	String firstname = CustomerType1.toString();
	 	    		jsonObjres.put("FirstName",firstname);
	 	    		
	 	    		jsonObjres.put("MiddleName", Config.NILL_VALUE);
	 	    		jsonObjres.put("LastName", Config.NILL_VALUE);
	 	    		jsonObjres.put("Nationality", Config.NILL_VALUE);
	 	    		jsonObjres.put("Gender", Config.NILL_VALUE);
	 		    }
	    		 
	    		 if("Branch Name".equalsIgnoreCase(paramName.toString()))
	    		 {
	    		    	Object branchName = rec.get("bas:ParamValue");   		    	
	    		    	jsonObjres.put("BranchName",branchName.toString());
	    		 }
	    	}
	    	
		    
		    
		    if("Customer Category".equalsIgnoreCase(paramName.toString()))
		    {
		    	Object CustomerCategory1 = rec.get("bas:ParamValue");
		    	CustomerCategory = CustomerCategory1.toString();
		    
		    }
		    if("Customer Sub-Category".equalsIgnoreCase(paramName.toString()))
		    {
		    	Object CustomerSubCategory1 = rec.get("bas:ParamValue");
		    	CustomerSubCategory = CustomerSubCategory1.toString();
		    }

		  }
		
		jsonObjres.put("CustomerType", CustomerType);
		jsonObjres.put("CustomerCategory", CustomerCategory);
		jsonObjres.put("CustomerSubCategory", CustomerSubCategory);

		}else {
			jsonObjres.put("CustomerCode", Config.NILL_VALUE);
			jsonObjres.put("CustomerCategory", Config.NILL_VALUE);
			jsonObjres.put("CustomerLanguage", Config.NILL_VALUE);
			jsonObjres.put("CustomerLevel", Config.NILL_VALUE);
			jsonObjres.put("CustomerType", Config.NILL_VALUE);
			jsonObjres.put("CustomerSubCategory", Config.NILL_VALUE);
			
			jsonObjres.put("FirstName", Config.NILL_VALUE);
			jsonObjres.put("MiddleName", Config.NILL_VALUE);
			jsonObjres.put("LastName", Config.NILL_VALUE);
			jsonObjres.put("Nationality", Config.NILL_VALUE);
			jsonObjres.put("Gender", Config.NILL_VALUE);
			jsonObjres.put("BranchName",Config.NILL_VALUE);
		}
		
	}else{
		jsonObjres.put("RetCode",  Config.NILL_VALUE);
		jsonObjres.put("RetMsg",  Config.NILL_VALUE);
		
	}
	
	
	
	
	
	return jsonObjres;
}


public HashMap<String, Object> convertJsonObjectGetSubscriber(String outputData,String queryType) throws Exception
{
	JSONObject jsonObj = XML.toJSONObject(outputData);
	
	HashMap<String, Object> jsonObjres=new HashMap<>();
	
//	log.debug("convertJsonObject");
	
	if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").has("quer:GetSubscriberBody")) {
	jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
	jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
	jsonObjres.put("SubscriberType", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").get("quer:SubscriberType"));
	jsonObjres.put("NetworkType", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").get("quer:NetworkType"));
	jsonObjres.put("Status", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").get("quer:Status"));
	//jsonObjres.put("AshamTeleActiveStatus", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").getJSONObject("quer:PrimaryOffering").get("bas:Status"));  
	
	
	if(jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").has("quer:PUK1")) {

		String tempPuknumber = (jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").get("quer:PUK1")).toString();
		
//		AESEcription aESEcription = new AESEcription();
//		
//		
//		String puknumber = aESEcription.decrypt(tempPuknumber,Config.ENCRYPTION_KEY);
		
		
		jsonObjres.put("pukNo", tempPuknumber);
	}
	else
	{
		jsonObjres.put("pukNo", Config.NILL_VALUE);
	}
	
	
	JSONArray ParameterInfo = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody").getJSONObject("quer:ExtParamList").getJSONArray("bas:ParameterInfo");

	for (int i=0;i< ParameterInfo.length();i++)
	{
		
		JSONObject rec = ParameterInfo.getJSONObject(i);
		Object paramName = rec.get("bas:ParamName");
	    
	    if("IVR Language".equalsIgnoreCase(paramName.toString()))
	    {
	    	Object langaugeCode = rec.get("bas:ParamValue");
	    	jsonObjres.put("langaugeCode", langaugeCode);
	    }
	    
	    if("CallCenterAccess".equalsIgnoreCase(paramName.toString()))
	    {
	    	Object langaugeCode = rec.get("bas:ParamValue");
	    	jsonObjres.put("CallCenterAccess", langaugeCode);
	    }
	   
	  }
	}else {
		jsonObjres.put("RetCode", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader").get("bas:RetCode"));
		jsonObjres.put("RetMsg", jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader").get("bas:RetMsg"));
		jsonObjres.put("SubscriberType", Config.NILL_VALUE);
		jsonObjres.put("NetworkType", Config.NILL_VALUE);
		jsonObjres.put("Status", Config.NILL_VALUE);
		jsonObjres.put("CallCenterAccess", Config.NILL_VALUE);
		jsonObjres.put("langaugeCode", Config.NILL_VALUE);
		jsonObjres.put("AshamTeleActiveStatus", Config.NILL_VALUE);
		jsonObjres.put("pukNo", Config.NILL_VALUE);
	}
	
	
	return jsonObjres;
}

}