package com.api.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.configuration.ReadExternlProperty;
import com.api.servicecall.ChangeFamilyGroupSuppOffer;
import com.api.servicecall.ChangeSubInfo;
import com.api.servicecall.GetCustomer;
import com.api.servicecall.GetSubscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ApiServiceController {

	@Autowired
	ReadExternlProperty oProFile;

	//private static Logger log = null;
	
	private static final Logger log = LoggerFactory.getLogger("ApiServiceController");
	
	


	public ApiServiceController() {
		//log = Logger.getLogger("ApiServiceController_log");
	}

	
	

	// testdata
	@RequestMapping(value = "/testpostt", method = RequestMethod.GET)
	public Object posttestt() {
		// HashMap<String, Object> map=new HashMap<>();
		// map.put("res", "test is success");
		JSONObject respJson = new JSONObject();

		String xml2 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n"
				+ "      <quer:GetSubscriberResponse xmlns:quer=\"http://crm.huawei.com/query/\" xmlns:bas=\"http://crm.huawei.com/basetype/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n"
				+ "         <quer:ResponseHeader>\r\n" + "            <bas:RequestHeader xsi:nil=\"true\"/>\r\n"
				+ "            <bas:RetCode>1251046001</bas:RetCode>\r\n"
				+ "            <bas:RetMsg>channel cannot be blank.@653198307</bas:RetMsg>\r\n"
				+ "         </quer:ResponseHeader>\r\n" + "      </quer:GetSubscriberResponse>\r\n"
				+ "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

		String xml1 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
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

		respJson.put("name", "deepak");
		HashMap<String, Object> map = null;
		try {
			map = convertJsonObject(xml2, null);
			map = convertJsonObject(xml1, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map.toString();

	}

	public HashMap<String, Object> convertJsonObject(String outputData, Logger log) throws Exception {
		JSONObject jsonObj = XML.toJSONObject(outputData);

		HashMap<String, Object> jsonObjres = new HashMap<>();

		if (jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body")
				.getJSONObject("quer:GetSubscriberResponse").has("quer:GetSubscriberBody")) {
			jsonObjres.put("RetCode",
					jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body")
							.getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader")
							.get("bas:RetCode"));
			jsonObjres.put("RetMsg",
					jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body")
							.getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader")
							.get("bas:RetMsg"));
			jsonObjres.put("SubscriberType",
					jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body")
							.getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody")
							.get("quer:SubscriberType"));
			jsonObjres.put("Status",
					jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body")
							.getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody")
							.get("quer:Status"));

			JSONArray ParameterInfo = jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body")
					.getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:GetSubscriberBody")
					.getJSONObject("quer:ExtParamList").getJSONArray("bas:ParameterInfo");

			// Object langaugeCode = null;

			for (int i = 0; i < ParameterInfo.length(); i++) {

				JSONObject rec = ParameterInfo.getJSONObject(i);
				Object paramName = rec.get("bas:ParamName");

				if ("IVR Language".equalsIgnoreCase(paramName.toString())) {
					Object langaugeCode = rec.get("bas:ParamValue");
					jsonObjres.put("langaugeCode", langaugeCode);
					break;
				}

			}
		} else {
			jsonObjres.put("RetCode",
					jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body")
							.getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader")
							.get("bas:RetCode"));
			jsonObjres.put("RetMsg",
					jsonObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body")
							.getJSONObject("quer:GetSubscriberResponse").getJSONObject("quer:ResponseHeader")
							.get("bas:RetMsg"));
		}

		// log.debug("convertJsonObject");

		// JSONObject jsonObjres = new JSONObject();

		return jsonObjres;
	}

	// complete@
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getSubscriber", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> getSubscriber(@RequestBody String obj) {

		HashMap<String, Object> respJson = new HashMap<String, Object>();

		try {

			// resMap = new HashMap<>();

			log.info("\n******** getSubscriber ***********");

			log.info("Request input :" + obj.toString());
			
			ReadExternlProperty oProFile1 = new ReadExternlProperty();
			oProFile1.reloadPropertyIfChanged(log);		
			
			LinkedHashMap<String, Object> request = null;
			
			request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

			GetSubscriber oGetSubscriber = new GetSubscriber();

			log.info("Request validate ucid_id :" + request.get("ucid_id").toString());
			
			respJson = oGetSubscriber.getSubscriberImp(request, log);

			log.info("Final outputData : " + respJson);


			//return respJson;
		} catch (Exception e) {
			
			log.error("Exception getSubscriberImp API : " + e);
			respJson.put("StatusCode", "000");
			respJson.put("StatusDescription", "Failure");
			
			
		}
		return respJson;
	}

	// complete@
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeSubInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> changeSubInfo(@RequestBody String obj) {

		HashMap<String, Object> respJson = new HashMap<String, Object>();

		try {

			// resMap = new HashMap<>();

			log.info("\n******** changeSubInfo ***********");

			log.info("Request input :" + obj.toString());
			
			ReadExternlProperty oProFile1 = new ReadExternlProperty();
			oProFile1.reloadPropertyIfChanged(log);

			LinkedHashMap<String, Object> request = null;

			request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

			ChangeSubInfo changeSubInfo = new ChangeSubInfo();

			log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

			respJson = changeSubInfo.changeSubInfoImp(request, log);

			log.info("Final outputData : " + respJson);

			return respJson;
		} catch (Exception e) {
			log.error("Exception changeSubInfo API : " + e);
			respJson.put("StatusCode", "000");
			respJson.put("StatusDescription", "Failure");
			return respJson;
		}
	}

	// complete@
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> getCustomer(@RequestBody String obj) {

		HashMap<String, Object> respJson = new HashMap<String, Object>();

		try {

			// resMap = new HashMap<>();

			log.info("\n******** getCustomer ***********");

			log.info("Request input :" + obj.toString());

			ReadExternlProperty oProFile1 = new ReadExternlProperty();
			oProFile1.reloadPropertyIfChanged(log);

			
			LinkedHashMap<String, Object> request = null;
			

			request = new ObjectMapper().readValue(obj, LinkedHashMap.class);
			
			log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

			GetCustomer getCustomer = new GetCustomer();

			log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

			respJson = getCustomer.getCustomerImp(request, log);

			log.info("Final outputData : " + respJson);

			return respJson;
		} catch (Exception e) {
			log.error("Exception getCustomer API : " + e);
			respJson.put("StatusCode", "000");
			respJson.put("StatusDescription", "Failure");
		
			return respJson;
		}
	}

	// complete@
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeSupplementaryOffering", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> ChangeSupplementaryOffering(@RequestBody String obj) {

		HashMap<String, Object> respJson = new HashMap<String, Object>();

		try {

			// resMap = new HashMap<>();

			log.info("\n******** changeSupplementaryOffering ***********");

			log.info("Request input :" + obj.toString());
			
			ReadExternlProperty oProFile1 = new ReadExternlProperty();
			oProFile1.reloadPropertyIfChanged(log);

			LinkedHashMap<String, Object> request = null;

			request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

			com.api.servicecall.ChangeSupplementaryOffering changeSupplementaryOffer = new com.api.servicecall.ChangeSupplementaryOffering();

			log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

			respJson = changeSupplementaryOffer.changeSupplementaryOfferingImp(request, log);

			log.info("Final outputData : " + respJson);

			return respJson;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception changeSupplementaryOffering API : " + e);
			respJson.put("StatusCode", "000");
			respJson.put("StatusDescription", "Failure");
			return respJson;
		}
	}

	// complete@
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/subActivation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> SubActivation(@RequestBody String obj) {

		HashMap<String, Object> respJson = new HashMap<String, Object>();

		try {

			// resMap = new HashMap<>();

			log.info("\n******** subActivation ***********");

			log.info("Request input :" + obj.toString());

			ReadExternlProperty oProFile1 = new ReadExternlProperty();
			oProFile1.reloadPropertyIfChanged(log);

			LinkedHashMap<String, Object> request = null;

			request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

			com.api.servicecall.SubActivation subActivation = new com.api.servicecall.SubActivation();

			log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

			respJson = subActivation.subActivationImp(request, log);

			log.info("Final outputData : " + respJson);

			return respJson;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception subActivation API : " + e);
			respJson.put("StatusCode", "000");
			respJson.put("StatusDescription", "Failure");
			return respJson;
		}
	}

	// complete@
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkPUK", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> checkPUK(@RequestBody String obj) {

		HashMap<String, Object> respJson = new HashMap<String, Object>();

		try {

			// resMap = new HashMap<>();

			log.info("\n******** checkPUK ***********");

			log.info("Request input :" + obj.toString());

			ReadExternlProperty oProFile1 = new ReadExternlProperty();
			oProFile1.reloadPropertyIfChanged(log);

			LinkedHashMap<String, Object> request = null;

			request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

			com.api.servicecall.CheckPUK checkPUK = new com.api.servicecall.CheckPUK();

			log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

			respJson = checkPUK.checkPUKImp(request, log);

			log.info("Final outputData : " + respJson);

			return respJson;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception checkPUK API : " + e);
			respJson.put("StatusCode", "000");
			respJson.put("StatusDescription", "Failure");
		
			return respJson;
		}
	}

	// complete@
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ReportLost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> reportLost(@RequestBody String obj) {

		HashMap<String, Object> respJson = new HashMap<String, Object>();

		try {

			// resMap = new HashMap<>();

			log.info("\n******** ReportLost ***********");

			log.info("Request input :" + obj.toString());

			ReadExternlProperty oProFile1 = new ReadExternlProperty();
			oProFile1.reloadPropertyIfChanged(log);

			LinkedHashMap<String, Object> request = null;

			request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

			com.api.servicecall.ReportLost reportLost = new com.api.servicecall.ReportLost();

			log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

			respJson = reportLost.reportLostImp(request, log);

			log.info("Final outputData : " + respJson);

			return respJson;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception ReportLost API : " + e);
			respJson.put("StatusCode", "000");
			respJson.put("StatusDescription", "Failure");
			return respJson;
		}
	}

	// complete@
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/CancelLost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> cancelLost(@RequestBody String obj) {

		HashMap<String, Object> respJson = new HashMap<String, Object>();

		try {

			// resMap = new HashMap<>();

			log.info("\n******** CancelLost ***********");

			log.info("Request input :" + obj.toString());

			ReadExternlProperty oProFile1 = new ReadExternlProperty();
			oProFile1.reloadPropertyIfChanged(log);

			LinkedHashMap<String, Object> request = null;

			request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

			com.api.servicecall.CancelLost cancelLost = new com.api.servicecall.CancelLost();

			log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

			respJson = cancelLost.cancelLostImp(request, log);

			log.info("Final outputData : " + respJson);

			return respJson;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception CancelLost API : " + e);
			respJson.put("StatusCode", "000");
			respJson.put("StatusDescription", "Failure");
			return respJson;
		}
	}

	// complete@
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/QueryUnbilledAmount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> queryUnbilledAmount(@RequestBody String obj) {

		HashMap<String, Object> respJson = new HashMap<String, Object>();

		try {

			// resMap = new HashMap<>();

			log.info("\n******** QueryUnbilledAmount ***********");

			log.info("Request input :" + obj.toString());

			ReadExternlProperty oProFile1 = new ReadExternlProperty();
			oProFile1.reloadPropertyIfChanged(log);

			LinkedHashMap<String, Object> request = null;

			request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

			com.api.servicecall.QueryUnbilledAmount queryUnbilledAmount = new com.api.servicecall.QueryUnbilledAmount();

			log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

			respJson = queryUnbilledAmount.queryUnbilledAmountImp(request, log);

			log.info("Final outputData : " + respJson);

			return respJson;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception QueryUnbilledAmount API : " + e);
			respJson.put("StatusCode", "000");
			respJson.put("StatusDescription", "Failure");

			return respJson;
		}
	}

	// complete@
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/LoyaltyServiceRegistration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> loyaltyServiceRegistration(@RequestBody String obj) {

		HashMap<String, Object> respJson = new HashMap<String, Object>();

		try {

			// resMap = new HashMap<>();

			log.info("\n******** LoyaltyServiceRegistration ***********");

			log.info("Request input :" + obj.toString());

			ReadExternlProperty oProFile1 = new ReadExternlProperty();
			oProFile1.reloadPropertyIfChanged(log);

			LinkedHashMap<String, Object> request = null;

			request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

			com.api.servicecall.LoyaltyServiceRegistration loyaltyServiceRegistration = new com.api.servicecall.LoyaltyServiceRegistration();

			log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

			respJson = loyaltyServiceRegistration.loyaltyServiceRegistrationImp(request, log);

			log.info("Final outputData : " + respJson);

			return respJson;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception LoyaltyServiceRegistration API : " + e);
			respJson.put("StatusCode", "000");
			respJson.put("StatusDescription", "Failure");
			return respJson;
		}
	}
	
	// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/QueryPointBalance", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> QueryPointBalance(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** QueryPointBalance ***********");

				log.info("Request input :" + obj.toString());

				ReadExternlProperty oProFile1 = new ReadExternlProperty();
				oProFile1.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.QueryPointBalance queryPointBalance = new com.api.servicecall.QueryPointBalance();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = queryPointBalance.queryPointBalanceImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception QueryPointBalance API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
				
				return respJson;
			}
		}
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/TransferPoint", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> TransferPoint(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** TransferPoint ***********");

				log.info("Request input :" + obj.toString());

				ReadExternlProperty oProFile1 = new ReadExternlProperty();
				oProFile1.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.TransferPoint transferPoint = new com.api.servicecall.TransferPoint();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = transferPoint.transferPointImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception TransferPoint API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");

				return respJson;
			}
		}
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/QueryVoucher", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> QueryVoucher(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** QueryVoucher ***********");

				log.info("Request input :" + obj.toString());

				ReadExternlProperty oProFile1 = new ReadExternlProperty();
				oProFile1.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.QueryVoucher queryVoucher = new com.api.servicecall.QueryVoucher();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = queryVoucher.queryVoucherImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception QueryVoucher API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
				return respJson;
			}
		}
		
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/QueryRechargeLog", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> QueryRechargeLog(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** QueryRechargeLog ***********");

				log.info("Request input :" + obj.toString());

				ReadExternlProperty oProFile1 = new ReadExternlProperty();
				oProFile1.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.QueryRechargeLog queryRechargeLog = new com.api.servicecall.QueryRechargeLog();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = queryRechargeLog.queryRechargeLogImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception QueryRechargeLog API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
			
				return respJson;
			}
		}
		
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/QueryInvoice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> QueryInvoice(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** QueryInvoice ***********");

				log.info("Request input :" + obj.toString());

				ReadExternlProperty oProFile1 = new ReadExternlProperty();
				oProFile1.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.QueryInvoice queryInvoice = new com.api.servicecall.QueryInvoice();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = queryInvoice.queryInvoiceImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception QueryInvoice API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
	
				return respJson;
			}
		}
		
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/CheckPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> CheckPassword(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** CheckPassword ***********");

				log.info("Request input :" + obj.toString());

				ReadExternlProperty oProFile1 = new ReadExternlProperty();
				oProFile1.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.CheckPassword checkPassword = new com.api.servicecall.CheckPassword();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = checkPassword.checkPasswordImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception CheckPassword API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
				return respJson;
			}
		}
		
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/ChangeSubPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> ChangeSubPassword(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** ChangeSubPassword ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.ChangeSubPassword ChangeSubPassword = new com.api.servicecall.ChangeSubPassword();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = ChangeSubPassword.ChangeSubPasswordImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception ChangeSubPassword API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
				return respJson;
			}
		}
		
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/RedeemCommodity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> RedeemCommodity(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				log.info("\n******** RedeemCommodity ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.RedeemCommodity redeemCommodity = new com.api.servicecall.RedeemCommodity();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = redeemCommodity.redeemCommodityImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception RedeemCommodity API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");

				return respJson;
			}
		}
		
		
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/QueryBalance", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> QueryBalance(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** QueryBalance ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.QueryBalance QueryBalance = new com.api.servicecall.QueryBalance();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = QueryBalance.queryBalanceImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception QueryBalance API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
				return respJson;
			}
		}
		
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/GetCustomerDataViaaccountCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> GetCustomerDataViaaccountCode(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** GetCustomerDataViaaccountCode ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.GetCustomerDataViaaccountCode getCustomerDataViaaccountCode = new com.api.servicecall.GetCustomerDataViaaccountCode();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = getCustomerDataViaaccountCode.getCustomerDataViaaccountCodeImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception GetCustomerDataViaaccountCode API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
				return respJson;
			}
		}
		
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/ChangeSubStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> ChangeSubStatus(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** ChangeSubStatus ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.ChangeSubStatus ChangeSubStatus = new com.api.servicecall.ChangeSubStatus();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = ChangeSubStatus.ChangeSubStatusImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception ChangeSubStatus API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");

				return respJson;
			}
		}
		
		//complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/PresentServicegift", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> PresentServicegift(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** PresentServicegift ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.PresentServicegift PresentServicegift = new com.api.servicecall.PresentServicegift();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = PresentServicegift.PresentServicegiftImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception PresentServicegift API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");

				return respJson;
			}
		}
		
		
	
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/QueryFreeUnit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> QueryFreeUnit(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();
			com.api.servicecall.QueryFreeUnit QueryFreeUnit = new com.api.servicecall.QueryFreeUnit();
			try {

				// resMap = new HashMap<>();

				log.info("\n******** QueryFreeUnit ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = QueryFreeUnit.QueryFreeUnitImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception QueryFreeUnit API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
				return respJson;
			}
		}
		
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/GetGroupMemberGroupId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> GetGroupMemberGroupId(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** GetGroupMemberGroupId ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.GetGroupMemberData GetGroupMemberData = new com.api.servicecall.GetGroupMemberData();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());
				
				respJson = GetGroupMemberData.GetGroupMemberGroupIdImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception GetGroupMemberGroupId API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
				return respJson;
			}
		}
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/GetGroupMemberData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> GetGroupMemberData(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** GetGroupMemberData ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.GetGroupMemberData GetGroupMemberData = new com.api.servicecall.GetGroupMemberData();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = GetGroupMemberData.GetGroupMemberDataImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception GetGroupMemberData API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");

				return respJson;
			}
		}

		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/QueryMember", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> QueryMember(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** QueryMember ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.QueryMember QueryMember = new com.api.servicecall.QueryMember();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = QueryMember.QueryMemberImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception QueryMember API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
				return respJson;
			}
		}
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/CreateFamilyGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> CreateFamilyGroup(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** CreateFamilyGroup ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.CreateFamilyGroup CreateFamilyGroup = new com.api.servicecall.CreateFamilyGroup();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = CreateFamilyGroup.CreateFamilyGroupImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception CreateFamilyGroup API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
		
				return respJson;
			}
		}
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/AddFamilyGroupMember", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> AddFamilyGroupMember(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** AddFamilyGroupMember ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.AddFamilyGroupMember AddFamilyGroupMember = new com.api.servicecall.AddFamilyGroupMember();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = AddFamilyGroupMember.AddFamilyGroupMemberImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception AddFamilyGroupMember API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
				return respJson;
			}
		}
				
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/ChangeFamilyFreeResourceShareAdd", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> ChangeFamilyFreeResourceShareAdd(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** ChangeFamilyFreeResourceShareAdd ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.ChangeFamilyFreeResourceShare ChangeFamilyFreeResourceShare = new com.api.servicecall.ChangeFamilyFreeResourceShare();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = ChangeFamilyFreeResourceShare.ChangeFamilyFreeResourceShareAddImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception ChangeFamilyFreeResourceShareAdd API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
	
				return respJson;
			}
		}
		
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/ChangeFamilyFreeResourceShareModify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> ChangeFamilyFreeResourceShareModify(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** ChangeFamilyFreeResourceShareModify ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.ChangeFamilyFreeResourceShare ChangeFamilyFreeResourceShare = new com.api.servicecall.ChangeFamilyFreeResourceShare();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = ChangeFamilyFreeResourceShare.ChangeFamilyFreeResourceShareModifyImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception ChangeFamilyFreeResourceShareModify API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
			
				return respJson;
			}
		}
		
		// complete@	
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/DeleteFamilyGroupMember", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> DeleteFamilyGroupMember(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** DeleteFamilyGroupMember ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.DeleteFamilyGroupMember DeleteFamilyGroupMember = new com.api.servicecall.DeleteFamilyGroupMember();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = DeleteFamilyGroupMember.DeleteFamilyGroupMemberImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception DeleteFamilyGroupMember API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");

				return respJson;
			}
		}
				
		// complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/DeactivateSub", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> DeactivateSub(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** DeactivateSub ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.DeactivateSub DeactivateSub = new com.api.servicecall.DeactivateSub();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = DeactivateSub.DeactivateSubImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception DeactivateSub API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
		
				return respJson;
			}
		}
		
		//complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/GetSubFreeRscRelaInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> GetSubFreeRscRelaInfo(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** GetSubFreeRscRelaInfo ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				com.api.servicecall.GetSubFreeRscRelaInfo DeactivateSub = new com.api.servicecall.GetSubFreeRscRelaInfo();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = DeactivateSub.GetSubFreeRscRelaInfoImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception GetSubFreeRscRelaInfo API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
		
				return respJson;
			}
		}
		
		//complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/ChangeFamilyGroupSuppOffer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> changeFamilyGroupSuppOffer(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				// resMap = new HashMap<>();

				log.info("\n******** ChangeFamilyGroupSuppOffer ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				ChangeFamilyGroupSuppOffer oChangeFamilyGroupSuppOffer = new ChangeFamilyGroupSuppOffer();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = oChangeFamilyGroupSuppOffer.changeFamilyGroupSuppOfferImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception ChangeFamilyGroupSuppOffer API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
		
				return respJson;
			}
		}
		
}
