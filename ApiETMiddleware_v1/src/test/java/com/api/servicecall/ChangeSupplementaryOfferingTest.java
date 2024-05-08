package com.api.servicecall;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import com.api.configuration.ReadApplicationProperty;

public class ChangeSupplementaryOfferingTest {

	
   Logger mockLogger;
   
   ChangeSupplementaryOffering changeSupplementaryOffering;
	
	@Before
	public void setUp() throws Exception {
		changeSupplementaryOffering = Mockito.mock(ChangeSupplementaryOffering.class);
		
		 mockLogger = Mockito.mock(Logger.class);
		 ReadApplicationProperty ra = new ReadApplicationProperty();
		 ra.readAppProperty("E:/ET/ApplicationProperties/API/ETApiDev.properties", "19000101000000", "20370101000000", mockLogger);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testChangeSupplementaryOfferingImp() throws Exception {
		
		LinkedHashMap<String, Object> request = new LinkedHashMap<>();
		request.put("ucid_id","1234356");
		request.put("TransactionId","20210526104933");
		request.put("ServiceNumber","123888013");
		request.put("ActivateDate","20220327121722");
		request.put("RequestTime","20240418011918");
		request.put("ExpirationDate","20250612112455");
		request.put("OfferingId","123456");
		
		HashMap<String,Object> res = new HashMap<>();
		res.put("StatusCode","200");
		res.put("StatusDescription", "Success");
		
		Mockito.when(changeSupplementaryOffering.changeSupplementaryOfferingImp(request, mockLogger)).thenReturn(res);
		
	}

}
