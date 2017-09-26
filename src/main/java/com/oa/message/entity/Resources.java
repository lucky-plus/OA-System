package com.oa.message.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_resources")
public class Resources {

	private Integer resId;
	private String resName;
	private String resIdentify;		//资料辨识
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getResId() {
		return resId;
	}
	public String getResName() {
		return resName;
	}
	public String getResIdentify() {
		return resIdentify;
	}
	
	public void setResId(Integer resId) {
		this.resId = resId;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public void setResIdentify(String resIdentify) {
		this.resIdentify = resIdentify;
	}
	
}
