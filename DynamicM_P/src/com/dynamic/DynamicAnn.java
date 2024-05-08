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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "entry" })
@XmlRootElement(name = "DynamicAnn")
public class DynamicAnn {
	@XmlElement(name = "Entry")
	protected List<DynamicAnn.Entry> entry;
	@XmlAttribute
	protected String value;

	public List<DynamicAnn.Entry> getEntry() {
		if (entry == null) {
			entry = new ArrayList<DynamicAnn.Entry>();
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
		protected DynamicAnn.Entry.Value value;

		

		public String getKey() {
			return key;
		}



		public void setKey(String key) {
			this.key = key;
		}



		public DynamicAnn.Entry.Value getValue() {
			return value;
		}



		public void setValue(DynamicAnn.Entry.Value value) {
			this.value = value;
		}



		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "prompts", "nextNode" })
		public static class Value {

			@XmlElement(name = "Prompts", required = false)
			protected DynamicAnn.Entry.Value.Prompts prompts;

			@XmlElement(name = "NextNode", required = false)
			protected String nextNode;

			public DynamicAnn.Entry.Value.Prompts getPrompts() {
				return prompts;
			}

			public void setPrompts(DynamicAnn.Entry.Value.Prompts value) {
				this.prompts = value;
			}

			public String getNextNode() {
				return nextNode;
			}

			public void setNextNode(String value) {
				this.nextNode = value;
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
