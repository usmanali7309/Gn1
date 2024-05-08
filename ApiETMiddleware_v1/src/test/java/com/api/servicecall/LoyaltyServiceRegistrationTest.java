package com.api.servicecall;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import com.api.configuration.ReadApplicationProperty;

public class LoyaltyServiceRegistrationTest {

	Logger mockLogger;
	LoyaltyServiceRegistration loyaltyServiceRegistration;
	
	
	@Before
	public void setUp() throws Exception {
		
		mockLogger = Mockito.mock(Logger.class);
		loyaltyServiceRegistration = Mockito.mock(LoyaltyServiceRegistration.class);
		
		ReadApplicationProperty ra = new ReadApplicationProperty();
	    ra.readAppProperty("E:/ET/ApplicationProperties/API/ETApiDev.properties", "19000101000000", "20370101000000", mockLogger);
		
	}

	@After
	public void tearDown() throws Exception {		
	}

	@Test
	public void testLoyaltyServiceRegistrationImp() throws Exception {
		
		LinkedHashMap<String,Object> request = new LinkedHashMap<>();
		request.put("ucid_id","1234567890");
		request.put("TransactionId", "20220606050211");
		request.put("ServiceNumber","123000229");
		request.put("RequestTime","20240221123455");
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
		
		Mockito.when(loyaltyServiceRegistration.loyaltyServiceRegistrationImp(request, mockLogger)).thenReturn(res);		
		
	}

}
