package com.oa.personnel.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oa.staff.entity.UserInfornation;

@Entity
@Table(name="t_notes")
public class PersonelNotes {
	
	private Integer notesId;
	private String notesName;		//流程名称
	private Date examineTime;		//审核时间
	private String examineResult;	//审核结果
	private String userId;			//申请人Id
	private String userName;		//申请人用户名
	private String realName;		//申请人姓名

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getNotesId() {
		return notesId;
	}
	public String getNotesName() {
		return notesName;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getExamineTime() {
		return examineTime;
	}
	public String getExamineResult() {
		return examineResult;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getRealName() {
		return realName;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public void setNotesId(Integer notesId) {
		this.notesId = notesId;
	}
	public void setNotesName(String notesName) {
		this.notesName = notesName;
	}
	public void setExamineTime(Date examineTime) {
		this.examineTime = examineTime;
	}
	public void setExamineResult(String examineResult) {
		this.examineResult = examineResult;
	}
	
}
