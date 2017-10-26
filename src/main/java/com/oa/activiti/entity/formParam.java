package com.oa.activiti.entity;

import java.util.List;

public class formParam {
	private String label;
	private String type;
	private String name;
	private List<value> values;
	
	public String getLabel() {
		return label;
	}
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public List<value> getValues() {
		return values;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setValues(List<value> values) {
		this.values = values;
	}
	@Override
	public String toString() {
		StringBuilder valueContent = new StringBuilder();
		if(values!=null)
			for(value v : values) {
				valueContent.append(v.toString());
			}
		return "formParam [label=" + label + ", type=" + type + ", name=" + name + ", values=" + valueContent.toString() + "]";
	}
	
}
