package com.dynamic;

import java.util.HashMap;

import com.audium.server.session.DecisionElementData;
import com.audium.server.voiceElement.DecisionElementBase;

public class CheckGrammer extends DecisionElementBase{

	@Override
	public String doDecision(String arg0, DecisionElementData data) throws Exception {
		
		String menuValue = (String) data.getElementData("Form1", "value");   // ---> getting values from previous node

		String[] gram = ((String) data.getSessionData("grammar")).split(",");
		String[] node = ((String) data.getSessionData("nextNode")).split(",");
		
		String message = "Failure";
		
		HashMap<String, String> keyAndNode = new HashMap<>();

		for (int i = 0; i < gram.length; i++) {
			keyAndNode.put(gram[i], node[i]);            // setting grammer and nextnode as key, value pair . eg - for "grammer 1" -> "NM:Sale"         
		}

		for (String grammarElement : gram) {        
			if (grammarElement.equalsIgnoreCase(menuValue)) {

				String vs = keyAndNode.get(menuValue);

				String Arr[] = vs.split(":");
				data.setSessionData("nextNode", Arr[0]);

				data.setSessionData("nextKey", Arr[1]);
				message = "Success";
				break;

			}
		}
		return message;

	
	}

}
