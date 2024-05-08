package com.api.Connection;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.slf4j.Logger;
import com.api.configuration.Config;



public class GethttpsConnection 
{

	//String usercredential 	= "";
	String certificate_path = "";
	String certificate_key  = "";
	String cethostName	    = "";
	
	
	public String postRequest(String url, String requestdata,String soapAction,Logger log) throws Exception {

		String responseSTR 				= "";
		SSLSocketFactory socketFactory 	= null;
		KeyStore keyStoreKeys 			= null;
		URL obj 						= null;
		HttpsURLConnection postConnection = null;
		OutputStream os 				= null;
		BufferedReader in 				= null;
		
		//usercredential 		= Config.AUTH_USERNAME+ ":" + Config.AUTH_PASSWORD;
		certificate_path 	= Config.CERTIFICATE_PATH;
		certificate_key  	= Config.CERTIFICATE_KEY;
		cethostName       	= Config.API_HOSTNAME;
		
		log.info("Soap Action : "+ soapAction);
		log.info("URL : "+ url);
		log.info("Request Data : "+ requestdata);
		//log.info("Usercredential : "+ usercredential);
		log.info("certificate_path : "+ certificate_path);
		log.info("certificate_key : "+ certificate_key);

		

			//String encoded = Base64.getEncoder().encodeToString((usercredential).getBytes("UTF-8"));

			
			log.debug("Set Sytem properties!!");

			// *****Set Sytem properties*******
			System.setProperty("javax.net.ssl.trustStore", certificate_path);
			System.setProperty("javax.net.ssl.trustStorePassword", certificate_key);
			System.setProperty("javax.net.ssl.trustStoreType", "jks");
			System.setProperty("javax.net.ssl.keyStore", certificate_path);
			System.setProperty("javax.net.ssl.keyStorePassword", certificate_key);
			System.setProperty("javax.net.ssl.keyStoreType", "jks");

			keyStoreKeys = KeyStore.getInstance("JKS");
			keyStoreKeys.load(new FileInputStream(certificate_path), certificate_key.toCharArray());
			KeyManagerFactory keyMgrFactory = KeyManagerFactory.getInstance("SunX509");

			keyMgrFactory.init(keyStoreKeys, certificate_key.toCharArray());
			KeyStore trustStore = KeyStore.getInstance("JKS");
			trustStore.load(new FileInputStream(certificate_path), certificate_key.toCharArray());
			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			trustManagerFactory.init(trustStore);
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(keyMgrFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
			socketFactory = sslContext.getSocketFactory();
			
			log.debug("Key Added!!");
			
			HostnameVerifier hv = new HostnameVerifier() {

				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					String hostName = cethostName;
					if (arg0.equals(hostName))
						return true;
					return false;
				}
			};
			log.debug("HostnameVerifier Success!!");
			
			obj = new URL(url);

			HttpsURLConnection.setDefaultHostnameVerifier(hv);

			postConnection = (HttpsURLConnection) obj.openConnection();
			((HttpsURLConnection) postConnection).setSSLSocketFactory(socketFactory);

			postConnection.setDoInput(true);
			postConnection.setDoOutput(true);
			postConnection.setUseCaches(false);
			postConnection.setRequestMethod("POST");
			//postConnection.setRequestProperty ("Authorization", "Basic " + encoded);
			postConnection.setRequestProperty("Content-Length", "500000");
			postConnection.setRequestProperty("Connection", Config.API_CONNECTION_TIMEOUT.trim());
			postConnection.setReadTimeout(50000);
			postConnection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
			postConnection.setRequestProperty("SOAPAction",soapAction);

			log.debug("HttpsURLConnection Properties    : " + postConnection.getRequestProperties());
			
			os = postConnection.getOutputStream();
			os.write(requestdata.getBytes());
			os.flush();
			os.close();

			int responseCode = postConnection.getResponseCode();
			
			log.debug("HttpsURLConnection Response Code    : " + responseCode);
			log.debug("HttpsURLConnection Response Message : " + postConnection.getResponseMessage());

			in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			responseSTR = response.toString();
			System.out.println("HttpsURLConnection Response         : " + responseSTR);
			postConnection.disconnect();

		

		return responseSTR;

	}

	
	
}
