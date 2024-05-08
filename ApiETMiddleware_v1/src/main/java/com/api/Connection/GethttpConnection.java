package com.api.Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;

import com.api.configuration.Config;

public class GethttpConnection 
{
	public String postRequest(String requestUrl, String requestdata,String soapAction,Logger log) throws Exception 
	{
		BufferedReader br = null;
		HttpURLConnection conn = null;
		OutputStream os =null;
		
		int conTimeOut = Integer.parseInt(Config.API_CONNECTION_TIMEOUT.trim());
		int readTimeOut = Integer.parseInt(Config.API_READ_TIMEOUT.trim());

		
		try
		{
				
			URL url = new URL(requestUrl);
		    conn = (HttpURLConnection) url.openConnection();
		    conn.setDoOutput(true);
		    conn.setRequestMethod("POST");
		    conn.setRequestProperty("Content-Length", "500000");
		    //conn.setRequestProperty("Connection", Config.API_CONNECTION_TIMEOUT.trim());//5000
		    conn.setReadTimeout(readTimeOut);
		    conn.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
		    conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		    conn.setRequestProperty("SOAPAction",soapAction);
		    conn.setConnectTimeout(conTimeOut);
		    conn.setUseCaches(false);
		    conn.setDoInput(true);
		    conn.setDoOutput(true);
		    os = conn.getOutputStream();
		    os.write(requestdata.toString().getBytes()); 
		    os.flush();
			conn.getResponseCode();
			conn.getResponseMessage();
			br = new BufferedReader(new InputStreamReader((conn.getInputStream()))); // Getting the response from the webservice
			 String output;		 
			 while ((output = br.readLine()) != null) {
			 return output;
			 }
		}
		catch (Exception e) {
			
			log.error(" Exception to connect the service:  " + e);
		}
		finally
		{
			try {
				os.close();
				br.close();
			    conn.disconnect();
				
			}catch (Exception e) {
				log.error(" Exception to close the connection:  " + e);
			}
			
		}
		return null;
		
	}
}
