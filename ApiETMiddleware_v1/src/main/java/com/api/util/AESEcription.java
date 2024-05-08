package com.api.util;

import javax.crypto.*;
import javax.crypto.spec.*;

public class AESEcription {
	
	
	public  String encrypt(String input, String key) 
	{
	byte[] crypted = null;
		try 
		{
		
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			crypted = cipher.doFinal(input.getBytes());
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
		
		return new String(encoder.encodeToString(crypted));
   }

public String decrypt(String input, String key) {
	byte[] output = null;
	try {
		java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
		SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, skey);
		output = cipher.doFinal(decoder.decode(input));
	} catch (Exception e) {
		System.out.println(e.toString());
	}
	return new String(output);
}

/**
 * @param args
 */
public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	String key = "1234567890111110";
	String data = "06696342800612";

	AESEcription oaes = new AESEcription();
	System.out.println(oaes.encrypt(data, key));
	System.out.println(oaes.decrypt("e0V8f3mzlYP8f/dTvAI22Q==", key));
	
	//System.out.println(oaes.encrypt("020202", key127));
}

}