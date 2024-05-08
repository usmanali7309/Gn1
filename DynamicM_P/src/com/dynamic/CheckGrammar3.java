package com.dynamic;

import java.util.HashMap;

import com.audium.server.session.DecisionElementData;
import com.audium.server.voiceElement.DecisionElementBase;

public class CheckGrammar3 extends DecisionElementBase{

	private Object sessionData;

	@Override
	public String doDecision(String arg0, DecisionElementData data) throws Exception {
		
		String menuValue = (String) data.getElementData("Form3", "value");   // ---> getting values from previous node
        
		String message = "Failure";
		
		String node = (String) data.getSessionData("nextNode");
		
		if(menuValue.equals("123456")) {
			
			String[] AT = node.split(":");
			
			data.setSessionData("nextNode", AT[0]);
			data.setSessionData("nextKey", AT[1]);
			message = "Success";
			
		
		
		}
		
		String ND = (String) data.getSessionData("nextNode");
		String NK = (String) data.getSessionData("nextKey");
		data.addToLog("Next Node is : ", ND);
		data.addToLog("Next Key is : ", NK);
		
        return message;
		
		
		
	}
	
	
	
	

}
