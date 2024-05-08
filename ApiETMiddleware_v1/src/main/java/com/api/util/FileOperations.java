package com.api.util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;

public class FileOperations {
	
	
	
	public String parseXmlData(String filePath,Logger log)
	{
		 String textVal =  null;
				 
		try
		{
			
			 textVal = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
			 
			 //log.debug("textVal : "+ textVal);
			// System.out.println("textVal : "+ textVal);
			 
			 return textVal;
			
		}catch (Exception e) {
			log.error("Exception to parse the xml string data : "+ e);
			return textVal;
		}
	}
	
	public String getUUID()
	{
		 UUID uuid = UUID.randomUUID();
	     String uuidAsString = uuid.toString().toString().replace("-","").substring(0,25);

	     return uuidAsString;
	}

}
