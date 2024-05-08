package com.dynamic;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;

public class MenuLoader extends ActionElementBase {

	@Override
	public void doAction(String arg0, ActionElementData data) throws Exception {

		try {

			String key;

			HashMap<String, String> loadxml = new HashMap<>();
			Map<String, HashMap<String, String>> objMapDynamicXml = new HashMap<>();

			File file = new File(
					"C:/Cisco/CallStudio/eclipse/plugins/com.audiumcorp.studio.debug.runtime/CATALINA_HOME/webapps/CVP/Config/DyMenuTest.xml");

			JAXBContext jaxbContext = JAXBContext.newInstance(DynamicMenu.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			DynamicMenu dynamic = (DynamicMenu) unmarshaller.unmarshal(file);

			for (DynamicMenu.Entry entry : dynamic.getEntry()) {
				key = entry.getKey();
				System.out.println("Key: " + key);
				data.addToLog("This is for logging======>", key);

				DynamicMenu.Entry.Value value = entry.getValue();
				final Logger logger = Logger.getGlobal();
				logger.info("This is for logging======>" + value.getMenuID());

				System.out.println("Entered");

				loadxml = new HashMap<>();
				System.out.println("Reading XML values from property file");
				loadxml.put("PROMPTS", value.getPrompts().getValue().trim());
				loadxml.put("BARGEIN", value.getPrompts().getBargein().trim());
				loadxml.put("GRAMMARS", value.getGrammars().trim());
				loadxml.put("NEXT_NODE", value.getNextNode().trim());
				loadxml.put("MENU_ID", value.getMenuID().trim());
				loadxml.put("NI_PROMPT", value.getNoInput().getValue().trim());
				loadxml.put("NM_PROMPT", value.getNoMatch().getValue().trim());
				loadxml.put("RETRY", value.getRetry().trim());
				loadxml.put("MAX_TRIES", value.getMaxTries().trim());

				objMapDynamicXml.put(key, loadxml);

				System.out.println("loadxml------" + loadxml);
//			    System.out.println("objMapDynamicXml------" + objMapDynamicXml);
				key = "";

			}
			
			System.out.println("objMapDynamicXml------" + objMapDynamicXml);

			
			String nextKey = (String) data.getSessionData("nextKey");
			System.out.println("nextKey------" + nextKey);
			
			HashMap<String, String> xKeyValues = objMapDynamicXml.get(nextKey);
			
			if (xKeyValues != null) {
				String prompts = xKeyValues.get("PROMPTS");
				String grammar = xKeyValues.get("GRAMMARS");
				String bargein = xKeyValues.get("BARGEIN");
				String next_node = xKeyValues.get("NEXT_NODE");
				String menuID = xKeyValues.get("MENU_ID");
				String noInputPrompt = xKeyValues.get("NI_PROMPT");
				String noMatchPrompt = xKeyValues.get("NM_PROMPT");
				String Tries = xKeyValues.get("RETRY");
				String maxTries = xKeyValues.get("MAX_TRIES");

				data.setSessionData("prompts", prompts);
				data.setSessionData("grammar", grammar);
				data.setSessionData("bargein", bargein);
				data.setSessionData("nextNode", next_node);
				data.setSessionData("menuID", menuID);
				data.setSessionData("noInputPrompt", noInputPrompt);
				data.setSessionData("noMatchPrompt", noMatchPrompt);
				data.setSessionData("Tries", Tries);
				data.setSessionData("maxTries", maxTries);

				System.out.println("prompts------" + prompts);
				System.out.println("Grammar------" + grammar);
				System.out.println("Bargein------" + bargein);
				System.out.println("nextNode------" + next_node);
				System.out.println("menuID------" + menuID);
				System.out.println("noInputPrompt------" + noInputPrompt);
				System.out.println("noMatchPrompt------" + noMatchPrompt);
				System.out.println("Tries------" + Tries);
				System.out.println("maxTries------" + maxTries);

			} else {
				System.out.println("Key not found: " + nextKey);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
