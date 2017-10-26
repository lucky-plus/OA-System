package com.oa.activiti.entity;

public class value {
	String label;
	String value;
	public String getLabel() {
		return label;
	}
	public String getValue() {
		return value;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "value [label=" + label + ", value=" + value + "]";
	}
	
}
