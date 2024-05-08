package com.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.slf4j.Logger;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.api.configuration.ReadApplicationProperty;
import com.api.configuration.ReadExternlProperty;
import com.api.servicecall.GetSubscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;




public class ApiServiceControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	ReadExternlProperty oProFile;
	
	@InjectMocks
	ReadApplicationProperty ra;
	
		
	AutoCloseable autoCloseable;
	
	@InjectMocks
	private ApiServiceController apiServiceController;
	
	@Mock
    private GetSubscriber mockGetSubscriber;
	
	Logger mockLogger;
	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(apiServiceController).build();
        mockLogger = Mockito.mock(Logger.class);

        ra.readAppProperty("E:/ET/ApplicationProperties/API/ETApiDev.properties", "19000101000000", "20370101000000", mockLogger);
        oProFile.reloadPropertyIfChanged(mockLogger);
	}

	@After
	public void tearDown() throws Exception {
//		autoCloseable.close();
		
	}


	@Test
	public void testGetSubscriber() throws Exception {	
		 
		 String req = "{\n" +
	                "\"ucid_id\":\"1234356\", \n"+
	                "\"TransactionId\":\"20210526104933\",\n" +
	                "\"ServiceNumber\":\"123888013\"\n" +
	                "}";
		  
        LinkedHashMap<String, Object> request = new LinkedHashMap<>();
        request.put("ucid_id", "1234356");
        request.put("TransactionId", "20210526104933");
        request.put("ServiceNumber", "123888013");
           
		HashMap<String, Object> res = new HashMap<>();	
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");	
		
		assertEquals(res, apiServiceController.getSubscriber(req));
		
		mockMvc.perform(MockMvcRequestBuilders 
				.post("/getSubscriber")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(req))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));
				
	}
	
	
	@Test
	public void testChangeSubInfo() throws Exception {
		
		String req = "{\r\n"
				+ "	\"ucid_id\": \"1234567890\",\r\n"
				+ "	\"TransactionId\": \"20210526104933\",\r\n"
				+ "	\"ServiceNumber\":\"123555138\",\r\n"
				+ "	\"languageCode\":\"2003\",\r\n"
				+ "	\"RequestTime\":\"20210526104933\"\r\n"
				+ "}";
		
		HashMap<String,Object> res = new HashMap<>();
	    res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		 
	   assertThat(apiServiceController.changeSubInfo(req)).isEqualTo(res);
	   
	   mockMvc.perform(MockMvcRequestBuilders
			   .post("/changeSubInfo")
			   .contentType(MediaType.APPLICATION_JSON_VALUE)
			   .content(req))
	           .andExpect(status().isOk())
	           .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
	           .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));
		
	}

	
	@Test
	public void testGetCustomer() throws Exception {
		
		String req = "{\r\n"
				+ " \"ucid_id\": \"1234567890\",\r\n"
				+ "	\"TransactionId\": \"20220606050211\",\r\n"
				+ "	\"ServiceNumber\":\"123000229\",\r\n"
				+ "	\"LanguageCode\":\"2002\",\r\n"
				+ "	\"RequestTime\":\"20220606050211\"\r\n"
				+ "}";
		
		
	      HashMap<String, Object> res = new HashMap<>();
	      res.put("StatusCode", "200");
		  res.put("StatusDescription", "Success");
		  
	     assertThat(apiServiceController.getCustomer(req)).isEqualTo(res);
	     
	     mockMvc.perform(MockMvcRequestBuilders
	    		 .post("/getCustomer")
	    		 .contentType(MediaType.APPLICATION_JSON_VALUE)
	    		 .content(req))
	             .andExpect(status().isOk())
	             .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));
		
	}

	
	@Test
	public void testSubActivation() throws Exception {
		
		String req = "{\r\n"
				+ "	\"ucid_id\":\"1234567890\",\r\n"
				+ "	\"TransactionId\":\"20220405165806\",\r\n"
				+ "	\"ServiceNumber\":\"123555434\"\r\n"
				+ "}";
		
		
	      HashMap<String, Object> res = new HashMap<>();
	      res.put("StatusCode", "200");
		  res.put("StatusDescription", "Success");
		  
	     assertThat(apiServiceController.SubActivation(req)).isEqualTo(res);
	     
	     mockMvc.perform(MockMvcRequestBuilders
	    		 .post("/subActivation")
	    		 .contentType(MediaType.APPLICATION_JSON_VALUE)
	    		 .content(req))
	             .andExpect(status().isOk())
	             .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));
		
	}
	
	
	@Test
	public void testChangeSupplementaryOffering() throws Exception {
	
		String req = "{  \r\n"
				+ "	\"ucid_id\":\"1234356\",\r\n"
				+ "	\"TransactionId\":\"20210526104933\",\r\n"
				+ "	\"ServiceNumber\":\"123888013\",\r\n"
				+ " \"ActivateDate\":\"20220327121722\",\r\n"
				+ " \"RequestTime\":\"20240418011918\",\r\n"
				+ " \"ExpirationDate\":\"20250612112455\",\r\n"
				+ " \"OfferingId\":\"123456\"\r\n"
				+ "}";
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		
		assertThat(apiServiceController.ChangeSupplementaryOffering(req)).isEqualTo(res);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/changeSupplementaryOffering")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(req))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));
		
	}

	
	@Test
	public void testCheckPUK() throws Exception {
		
		String req = "{  \r\n"
				+ "	\"ucid_id\":\"1234356\",\r\n"
				+ "	\"TransactionId\":\"20210526104933\",\r\n"
				+ "	\"ServiceNumber\":\"123888013\",\r\n"
				+ "    \"PUKCode\":\"123456\" \r\n"
				+ "}";
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		
		assertThat(apiServiceController.checkPUK(req)).isEqualTo(res);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/checkPUK")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(req))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));
		
	}

	
	@Test
	public void testReportLost() throws Exception {
		
		String req = "{\r\n"
				+ "    \"ucid_id\": \"1234567890\",\r\n"
				+ "	\"TransactionId\": \"20220606050211\",\r\n"
				+ "	\"ServiceNumber\":\"123000229\",\r\n"
				+ "	\"RequestTime\":\"20240221123455\"\r\n"
				+ "}";
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		
		assertThat(apiServiceController.reportLost(req)).isEqualTo(res);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/ReportLost")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(req))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));
		
	}

	
	@Test
	public void testCancelLost() throws Exception {
		
		String req = "{\r\n"
				+ "    \"ucid_id\": \"1234567890\",\r\n"
				+ "	\"TransactionId\": \"20220606050211\",\r\n"
				+ "	\"ServiceNumber\":\"123000229\",\r\n"
				+ "	\"RequestTime\":\"20240221123455\"\r\n"
				+ "}";
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		
		assertThat(apiServiceController.cancelLost(req)).isEqualTo(res);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/CancelLost")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(req))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));
		
	}

	
	@Test
	public void testQueryUnbilledAmount() throws Exception {
		
		String req = "{\r\n"
				+ " \"ucid_id\": \"1234567890\",\r\n"
				+ "	\"TransactionId\": \"20220606050211\",\r\n"
				+ "	\"MSGSequence\":\"TestMsg\",\r\n"
				+ " \"InputNumber\":\"1234567898\",\r\n"
				+ " \"InputType\":\"123000229\"\r\n"
				+ "}";
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		
		assertThat(apiServiceController.queryUnbilledAmount(req)).isEqualTo(res);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/QueryUnbilledAmount")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(req))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));
		
		
	}

	
	@Test
	public void testLoyaltyServiceRegistration() throws Exception {
		
		String req = "{\r\n"
				+ "    \"ucid_id\": \"1234567890\",\r\n"
				+ "	\"TransactionId\": \"20220606050211\",\r\n"
				+ "	\"ServiceNumber\":\"123000229\",\r\n"
				+ "	\"RequestTime\":\"20240221123455\"\r\n"
				+ "}";
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		
		assertEquals(res, apiServiceController.loyaltyServiceRegistration(req));
		
		mockMvc.perform(MockMvcRequestBuilders 
				.post("/LoyaltyServiceRegistration")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(req))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));		
		
	}

	
	
	@Test
	public void testQueryPointBalance() throws Exception {
		
		String req = "{\r\n"
				+ " \"ucid_id\": \"1234567890\",\r\n"
				+ "	\"TransactionId\": \"20220606050211\",\r\n"
				+ "	\"ServiceNumber\":\"123000229\",\r\n"
				+ "	\"RequestTime\":\"20240221123455\"\r\n"
				+ "}";
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		
		assertThat(apiServiceController.QueryPointBalance(req)).isEqualTo(res);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/QueryPointBalance")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(req))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));
		
		
		
	}

	@Test
	public void testTransferPoint() throws Exception {
	
		String req = "{\r\n"
				+ "	\"ucid_id\": \"1234567890\",\r\n"
				+ "	\"TransactionId\": \"20210526104933\",	\r\n"
				+ "	\"LanguageCode\":\"2003\",\r\n"
				+ "	\"RequestTime\":\"20210526104933\",\r\n"
				+ " \"TransferSrcOwnerSrvcNo\":\"12345762123\",\r\n"
				+ " \"TransferAmount\":\"20000\",\r\n"
				+ " \"TransferDestOwnerSrvcNo\":\"12345676453\"\r\n"
				+ "}";
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		
		assertEquals(res, apiServiceController.TransferPoint(req));
		
		mockMvc.perform(MockMvcRequestBuilders 
				.post("/TransferPoint")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(req))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));
		
		
	}

	
	
	@Test
	public void testQueryVoucher() throws Exception {
		
		String req = "{\r\n"
				+ "    \"ucid_id\": \"1234567890\",\r\n"
				+ "	\"TransactionId\": \"20220606050211\",	\r\n"
				+ "	\"MSGSequence\":\"TestMsg\",\r\n"
				+ "    \"LanguageCode\":\"2003\",\r\n"
				+ "    \"SerialNo\":\"123000229\"\r\n"
				+ "}";
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		
		assertThat(apiServiceController.QueryVoucher(req)).isEqualTo(res);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/QueryVoucher")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(req))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));				
		
	}

	@Test
	public void testQueryRechargeLog() throws Exception {
		
		String req = "{\r\n"
				+ " \"ucid_id\": \"1234567890\",\r\n"
				+ "	\"TransactionId\": \"20220606050211\",\r\n"
				+ " \"servivceNumber\":\"128382311\",	\r\n"
				+ "	\"MSGSequence\":\"TestMsg\",\r\n"
				+ " \"CustomerCode\":\"1234567898\",\r\n"
				+ " \"StartDate\":\"20231124112344\",\r\n"
				+ " \"EndDate\":\"20240123123454\"\r\n"
				+ "}";
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		
		assertEquals(res, apiServiceController.QueryRechargeLog(req));
		
		mockMvc.perform(MockMvcRequestBuilders 
				.post("/QueryRechargeLog")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(req))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));
		
	}


	@Test
	public void testCheckPassword() throws Exception {
		
		String req = "{\r\n"
				+ " \"ucid_id\": \"1234567890\",\r\n"
				+ "	\"TransactionId\": \"20220606050211\",\r\n"
				+ "	\"ServiceNumber\":\"123000229\",\r\n"
				+ "	\"Password\":\"292919\"\r\n"
				+ "}";
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		 
		assertThat(apiServiceController.CheckPassword(req)).isEqualTo(res);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/CheckPassword")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(req))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));		
		
	}

	
	@Test
	public void testQueryInvoice() throws Exception {
		
		String req = "{\r\n"
				+ " \"ucid_id\": \"1234567890\",\r\n"
				+ "	\"TransactionId\": \"20220606050211\",\r\n"
				+ "	\"MSGSequence\":\"TestMsg\",\r\n"
				+ " \"InputNumber\":\"1234567898\",\r\n"
				+ " \"InputType\":\"123000229\",\r\n"
				+ " \"QueryType\":\"LastMonth\"\r\n"
				+ "}";
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode","200");
		res.put("StatusDescription", "Success");
		
		assertThat(apiServiceController.QueryInvoice(req)).isEqualTo(res);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/QueryInvoice")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(req))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusCode").value("200"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.StatusDescription").value("Success"));	
		
	}

	
	
//	@Test
//	public void testChangeSubPassword() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRedeemCommodity() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testQueryBalance() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCustomerDataViaaccountCode() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testChangeSubStatus() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPresentServicegift() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testQueryFreeUnit() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetGroupMemberGroupId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetGroupMemberData() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testQueryMember() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCreateFamilyGroup() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddFamilyGroupMember() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testChangeFamilyFreeResourceShareAdd() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testChangeFamilyFreeResourceShareModify() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteFamilyGroupMember() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeactivateSub() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetSubFreeRscRelaInfo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testChangeFamilyGroupSuppOffer() {
//		fail("Not yet implemented");
//	}

}
