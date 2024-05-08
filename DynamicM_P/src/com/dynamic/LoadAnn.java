package com.dynamic;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;


public class LoadAnn extends ActionElementBase {

	@Override
	public void doAction(String arg0, ActionElementData data) throws Exception {

	
	try {

		String key;

		HashMap<String, String> loadxml = new HashMap<>();
		Map<String, HashMap<String, String>> objMapDynamicXml = new HashMap<>();

		File file = new File(
				"C:/Cisco/CallStudio/eclipse/plugins/com.audiumcorp.studio.debug.runtime/CATALINA_HOME/webapps/CVP/Config/DyAnn.xml");

		JAXBContext jaxbContext = JAXBContext.newInstance(DynamicAnn.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		DynamicAnn dynamic = (DynamicAnn) unmarshaller.unmarshal(file);

		for (DynamicAnn.Entry entry : dynamic.getEntry()) {
			key = entry.getKey();
			System.out.println("Key: " + key);
			data.addToLog("This is for logging======>", key);

			DynamicAnn.Entry.Value value = entry.getValue();
			final Logger logger = Logger.getGlobal();

			System.out.println("Entered");
			
			loadxml = new HashMap<>();
			System.out.println("Entered");
			loadxml.put("PROMPTS", value.getPrompts().getValue().trim());
			loadxml.put("BARGEIN", value.getPrompts().getBargein().trim());
			loadxml.put("NEXT_NODE", value.getNextNode().trim());

			objMapDynamicXml.put(key, loadxml);

			System.out.println("loadxml------" + loadxml);
			key = "";
		}
		

		String nextKey = (String) data.getSessionData("nextKey");
		System.out.println("nextKey------" + nextKey);
		
		HashMap<String, String> xKeyValues = objMapDynamicXml.get(nextKey);

		if (xKeyValues != null) {
			String prompts = xKeyValues.get("PROMPTS");
			String bargein = xKeyValues.get("BARGEIN");
			String next_node = xKeyValues.get("NEXT_NODE");

			data.setSessionData("prompts", prompts);
			data.setSessionData("bargein", bargein);
			data.setSessionData("nextNode", next_node);

			System.out.println("prompts------" + prompts);
			System.out.println("Bargein------" + bargein);
			System.out.println("nextNode------" + next_node);

			
			
		} else {
			System.out.println("Key not found: " + nextKey);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
			
			
	
		}
}
