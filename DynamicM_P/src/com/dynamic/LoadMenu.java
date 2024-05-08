package com.dynamic;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;

public class LoadMenu extends ActionElementBase {

	@Override
	public void doAction(String arg0, ActionElementData data) throws Exception {
		
		try {
			
			String key;
			

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
				
				   String prompts = value.getPrompts().getValue().trim();
				   String bargein = value.getPrompts().getBargein().trim();
				   String grammar = value.getGrammars().trim();
				   String next_node = value.getNextNode().trim();
				   String menuID = value.getMenuID().trim()+",";
				   String noInputPrompt = value.getNoInput().getValue().trim();
				   String noMatchPrompt = value.getNoMatch().getValue().trim();
				   String tries = value.getRetry().trim();
				   String maxTries = value.getMaxTries().trim();	  
			
				    
				    data.setSessionData("prompts", prompts);
					data.setSessionData("grammar", grammar);
					data.setSessionData("bargein", bargein);
					data.setSessionData("nextNode", next_node);
					data.setSessionData("menuID", menuID);
					data.setSessionData("noInputPrompt", noInputPrompt);
					data.setSessionData("noMatchPrompt", noMatchPrompt);
					data.setSessionData("Tries", tries);
					data.setSessionData("maxTries", maxTries);
					key = "";
					


					System.out.println("prompts------" + prompts);
					System.out.println("Grammar------" + grammar);
					System.out.println("Bargein------" + bargein);
					System.out.println("nextNode------" + next_node);
					System.out.println("menuID------" + menuID);
					System.out.println("noInputPrompt------" + noInputPrompt);
					System.out.println("noMatchPrompt------" + noMatchPrompt);
					System.out.println("Tries------" + tries);
					System.out.println("maxTries------" + maxTries);
					
					
			}
			
			
			String[] arrPrompt = ((String) data.getSessionData("prompts")).split(",");
			data.addToLog("Prompts array for logging ++++++++++++ : ", arrPrompt.length+" ");
			
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

}
