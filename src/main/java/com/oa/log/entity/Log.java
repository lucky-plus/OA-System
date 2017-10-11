package com.oa.log.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oa.staff.entity.UserInfornation;

@Entity
@Table(name="t_log")
public class Log {
	
	private Integer logId;
	private Date createDate;
	private String operation;
	private String content;
	private UserInfornation user;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getLogId() {
		return logId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getCreateDate() {
		return createDate;
	}
	public String getOperation() {
		return operation;
	}
	public String getContent() {
		return content;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	public UserInfornation getUser() {
		return user;
	}
	
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setUser(UserInfornation user) {
		this.user = user;
	}
	
}
