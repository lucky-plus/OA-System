package com.oa.business.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oa.staff.entity.UserInfornation;

@Entity
@Table(name="t_task")
public class Task {
	
	private Integer taskId;
	private String taskName;
	private String taskText;
	private Date createDate;
	private Date completeDate;
	private String taskState;
	private String createId;		//发布者ID
	private String createName;		//发布者姓名
	private UserInfornation user;	//接收者

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getTaskId() {
		return taskId;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	@Lob
	public String getTaskText() {
		return taskText;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getCreateDate() {
		return createDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getCompleteDate() {
		return completeDate;
	}
	
	public String getTaskState() {
		return taskState;
	}
	
	public String getCreateId() {
		return createId;
	}
	
	public String getCreateName() {
		return createName;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	public UserInfornation getUser() {
		return user;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public void setUser(UserInfornation user) {
		this.user = user;
	}

}
