package com.dynamic;

import com.audium.server.AudiumException;
import com.audium.server.proxy.VoiceElementInterface;
import com.audium.server.session.ElementAPI;
import com.audium.server.voiceElement.AudioGroup;
import com.audium.server.xml.VoiceElementConfig;
import com.audium.server.xml.VoiceElementConfig.StaticAudio;

public class Form1 implements VoiceElementInterface{

	
	@Override
	public VoiceElementConfig getConfig(String arg0, ElementAPI data, VoiceElementConfig voElmtDefaults)
			throws AudiumException {

		voElmtDefaults = new VoiceElementConfig();

		VoiceElementConfig.AudioGroup initialAudioGroup = voElmtDefaults.new AudioGroup("initial_audio_group",
				Boolean.parseBoolean((String) data.getSessionData("bargein")));
		

		//"initial_audio_group" -->  cisco "Form" node oda --> " Audio - Audio Groups - Form Initial - audio item 1 ".
		// (used to set give "audio URI" dynamically in the "Form Node". 
		
		voElmtDefaults.setSettingValue("inputmode", "dtmf");
		voElmtDefaults.setSettingValue("noinput_timeout", "5s");
		voElmtDefaults.setSettingValue("max_noinput_count", "1");
		voElmtDefaults.setSettingValue("max_nomatch_count", "1");
		voElmtDefaults.setSettingValue("confidence_level", "0.4");		
		voElmtDefaults.addSettingValue("dtmf_grammar", "1");

		String[] arrPrompt = ((String) data.getSessionData("prompts")).split(",");
		 String str = (String) data.getSessionData("prompts");
		 data.addToLog("Prompts  === : ", str);
		// String strMediaSysPath = (String)
		// data.getSessionData("language");strMediaSysPath +
	    
		data.addToLog("Prompts array lenth === : ", arrPrompt.length+" ");
		
		for (int i = 0; i < arrPrompt.length; i++) {

			VoiceElementConfig.StaticAudio promptFor = voElmtDefaults.new StaticAudio("", arrPrompt[i] + ".wav");
			initialAudioGroup.addAudioItem(promptFor);
			
//			voElmtDefaults.setAudioGroup(initialAudioGroup);
//			data.addToLog("Prompts array === : ", promptFor+" ");
		}

		voElmtDefaults.setAudioGroup(initialAudioGroup);

		return voElmtDefaults;

	}
	
}
