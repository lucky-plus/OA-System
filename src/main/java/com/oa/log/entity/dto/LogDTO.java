package com.oa.log.entity.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oa.log.entity.Log;

public class LogDTO {

	private Integer logId;
	private Date createDate;
	private String operation;
	private String content;
	private String userName;
	
	public static void entityToDto(Log entity, LogDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		dto.setUserName(entity.getUser().getUserName());
	}
	
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
