package com.dynamic;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

//import com.test.DynamicMenu;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "entry" })
@XmlRootElement(name = "DynamicMenu")
public class DynamicMenu {
	
	
	@XmlElement(name = "Entry")
	protected List<DynamicMenu.Entry> entry;
	@XmlAttribute
	protected String value;

	public List<DynamicMenu.Entry> getEntry() {
		if (entry == null) {
			entry = new ArrayList<DynamicMenu.Entry>();
		}
		return this.entry;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "key", "value" })
	public static class Entry {

		@XmlElement(name = "Key", required = true)
		protected String key;
		@XmlElement(name = "Value", required = true)
		protected DynamicMenu.Entry.Value value;

		public String getKey() {
			return key;
		}

		public void setKey(String value) {
			this.key = value;
		}

		public DynamicMenu.Entry.Value getValue() {
			return value;
		}

		public void setValue(DynamicMenu.Entry.Value value) {
			this.value = value;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "prompts", "grammars", "nextNode", "menuID", "noInput", "noMatch", "maxTries", "retry" })
		public static class Value {

			@XmlElement(name = "Prompts", required = false)
			protected DynamicMenu.Entry.Value.Prompts prompts;
			
			@XmlElement(name = "Grammars", required = false)
			protected String grammars;
		
			@XmlElement(name = "NextNode", required = false)
			protected String nextNode;
			
			@XmlElement(name = "MenuID", required = false)
			protected String menuID;

			@XmlElement(name = "NoInput", required = false)
			protected DynamicMenu.Entry.Value.NoInput noInput;
		
			@XmlElement(name = "NoMatch", required = false)
			protected DynamicMenu.Entry.Value.NoMatch noMatch;
		
			@XmlElement(name = "MaxTries", required = false)
			protected String maxTries;

			@XmlElement(name = "Retry", required = false)
			protected String retry;

			public String getMaxTries() {
				return maxTries;
			}

			public void setMaxTries(String maxTries) {
				this.maxTries = maxTries;
			}

			public DynamicMenu.Entry.Value.Prompts getPrompts() {
				return prompts;
			}

			public void setPrompts(DynamicMenu.Entry.Value.Prompts value) {
				this.prompts = value;
			}

			public String getGrammars() {
				return grammars;
			}

			public void setGrammars(String grammars) {
				this.grammars = grammars;
			}

			public String getNextNode() {
				return nextNode;
			}

			public void setNextNode(String value) {
				this.nextNode = value;
			}

			public String getMenuID() {
				return menuID;
			}

			public void setMenuID(String menuID) {
				this.menuID = menuID;
			}

			public DynamicMenu.Entry.Value.NoInput getNoInput() {
				return noInput;
			}

			public void setNoInput(DynamicMenu.Entry.Value.NoInput value) {
				this.noInput = value;
			}

			public DynamicMenu.Entry.Value.NoMatch getNoMatch() {
				return noMatch;
			}

			public void setNoMatch(DynamicMenu.Entry.Value.NoMatch value) {
				this.noMatch = value;
			}

			public String getRetry() {
				return retry;
			}

			public void setRetry(String value) {
				this.retry = value;
			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "value" })
			public static class NoInput {

				@XmlValue
				protected String value;
				@XmlAttribute(name = "NextNode")
				protected String nextNode;

				public String getValue() {
					return value;
				}

				public void setValue(String value) {
					this.value = value;
				}

				public String getNextNode() {
					return nextNode;
				}

				public void setNextNode(String value) {
					this.nextNode = value;
				}

			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "value" })
			public static class NoMatch {

				@XmlValue
				protected String value;
				@XmlAttribute(name = "NextNode")
				protected String nextNode;

				public String getValue() {
					return value;
				}

				public void setValue(String value) {
					this.value = value;
				}

				public String getNextNode() {
					return nextNode;
				}

				public void setNextNode(String value) {
					this.nextNode = value;
				}

			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "value" })
			public static class Prompts {

				@XmlValue
				protected String value;
				@XmlAttribute
				protected String bargein;

				public String getValue() {
					return value;
				}

				public void setValue(String value) {
					this.value = value;
				}

				public String getBargein() {
					return bargein;
				}

				public void setBargein(String value) {
					this.bargein = value;
				}

			}

		}

	}
	
	

}
