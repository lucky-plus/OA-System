package com.oa.message.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oa.staff.entity.UserInfornation;

@Entity
@Table(name="t_notice")
public class Notice {
	
	private Integer noticeId;
	private String noticeName;
	private Date noticeTime;
	private String noticeText;
	private UserInfornation user;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getNoticeId() {
		return noticeId;
	}

	public String getNoticeName() {
		return noticeName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getNoticeTime() {
		return noticeTime;
	}
	@Lob
	public String getNoticeText() {
		return noticeText;
	}
	@ManyToOne
	public UserInfornation getUser() {
		return user;
	}
	
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}
	public void setNoticeText(String noticeText) {
		this.noticeText = noticeText;
	}
	public void setUser(UserInfornation user) {
		this.user = user;
	}
	
}
