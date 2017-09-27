package com.oa.message.entity.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.oa.message.entity.Notice;
import com.oa.staff.entity.UserInfornation;

public class NoticeDTO 
{
	private Long noticeId;
	private String noticeName;
	private Date noticeTime;
	private String noticeText;
	
	
	//private UserInfornation user;
	private String userId;//维护关联关系
	private String userName;

	
	//前台ajax、form到后台 关联关系维护
	public static void  dtoToEntity(NoticeDTO dto,Notice entity) {
		BeanUtils.copyProperties(dto, entity);
		String userId = dto.getUserId();
		
		if(userId!=null || !userId.trim().equals("")) {
			UserInfornation user = new UserInfornation();
			user.setUserId(userId);//关联关系维护
			
			entity.setUser(user);
		}
		
		
		
	}
	
	//后台反回针对View封装DTO
	public static void  entityToDto(Notice entity , NoticeDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		
		UserInfornation user = entity.getUser();
		if(user!=null) {
			dto.setUserId(user.getUserId());
			dto.setUserName(user.getUserName());

		}
		
	}
	
	
	
	
	
	
	
	
	public Long getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeName() {
		return noticeName;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	public Date getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}
	public String getNoticeText() {
		return noticeText;
	}
	public void setNoticeText(String noticeText) {
		this.noticeText = noticeText;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	
	
}
