package com.oa.message.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oa.staff.entity.UserInfornation;

@Entity
@Table(name="t_resources")
public class Resources {

	private Integer resId;
	private String resName;
	private String resIdentify;		//资料辨识
	private Date resDate;
	private UserInfornation user;
	
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getResDate() {
		return resDate;
	}
	@ManyToOne
	public UserInfornation getUser() {
		return user;
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
	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}
	public void setUser(UserInfornation user) {
		this.user = user;
	}
	
	
}
