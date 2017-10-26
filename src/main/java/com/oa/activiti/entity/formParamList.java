package com.oa.activiti.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.oa.staff.entity.UserInfornation;

@Entity
public class formParamList {
	private int formId;
	private String keyName;
	private List<formParam> lfp;
	private String description;
	private Date createDate;
	private UserInfornation userInfornation;
	
	public formParamList() {
		super();
	}
	
	public formParamList(String keyName, List<formParam> lfp) {
		super();
		this.keyName = keyName;
		this.lfp = lfp;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getFormId() {
		return formId;
	}
	public String getKeyName() {
		return keyName;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	public UserInfornation getUserInfornation() {
		return userInfornation;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public void setUserInfornation(UserInfornation userInfornation) {
		this.userInfornation = userInfornation;
	}

	@Transient
	public List<formParam> getLfp() {
		return lfp;
	}
	public void setFormId(int formId) {
		this.formId = formId;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public void setLfp(List<formParam> lfp) {
		this.lfp = lfp;
	}
	
}
