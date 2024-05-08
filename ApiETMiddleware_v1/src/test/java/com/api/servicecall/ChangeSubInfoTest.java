package com.api.servicecall;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.test.context.TestPropertySource;

import com.api.configuration.Config;
import com.api.configuration.InnitReadConfig;
import com.api.configuration.ReadApplicationProperty;
import com.api.configuration.ReadExternlProperty;

//@TestPropertySource(locations = "classpath:application.properties")
public class ChangeSubInfoTest {
	
	Logger mockLogger;
	

	
//	@InjectMocks
//	ReadApplicationProperty ra;

	@Before
	public void setUp() throws Exception {
		 
		mockLogger = Mockito.mock(Logger.class);
		
		ReadApplicationProperty ra = new ReadApplicationProperty();
		ra.readAppProperty("E:/ET/ApplicationProperties/API/ETApiDev.properties", "19000101000000", "20370101000000", mockLogger);
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testChangeSubInfoImp() throws Exception {
		
		LinkedHashMap<String,Object> request = new LinkedHashMap<>();
		request.put("ucid_id", "1234567890");
		request.put("TransactionId", "20210526104933");
		request.put("ServiceNumber","123555138");
		request.put("languageCode","2003");
		request.put("RequestTime","20210526104933");
		
		HashMap<String, Object> res = new HashMap<>();
		res.put("StatusCode", "200");
		res.put("StatusDescription", "Success");
				
		ChangeSubInfo changeSubInfo = new ChangeSubInfo();
		assertEquals(res, changeSubInfo.changeSubInfoImp(request, mockLogger));
		
	}


}
