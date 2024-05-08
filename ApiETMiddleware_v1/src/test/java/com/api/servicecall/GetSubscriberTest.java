package com.api.servicecall;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.LinkedHashMap;


import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;

import com.api.configuration.InnitReadConfig;
import com.api.configuration.ReadApplicationProperty;
import com.api.util.FileOperations;


public class GetSubscriberTest {
	
	
	Logger mockLogger;
	
	@Before
	public void setUp() throws Exception {
		
		  mockLogger = Mockito.mock(Logger.class);
		  Mockito.mock(GetSubscriber.class);
		  ReadApplicationProperty ra = new ReadApplicationProperty();
		  ra.readAppProperty("E:/ET/ApplicationProperties/API/ETApiDev.properties", "19000101000000", "20370101000000", mockLogger);
		  
	}

	@After
	public void tearDown() throws Exception {
		
	}

	
	@Test
    public void testGetSubscriberImp() throws Exception {
						 
        LinkedHashMap<String, Object> request = new LinkedHashMap<>();
        request.put("ucid_id", "1234356");
        request.put("TransactionId", "20210526104933");
        request.put("ServiceNumber", "123888013");
        
		GetSubscriber getSubscriber = new GetSubscriber();
		HashMap<String, Object> res = new HashMap<>();	
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		
		assertEquals(res, getSubscriber.getSubscriberImp(request, mockLogger));				
		             
    }
	


}
