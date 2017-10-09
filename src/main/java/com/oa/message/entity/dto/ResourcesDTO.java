package com.oa.message.entity.dto;


import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oa.message.entity.Resources;
import com.oa.staff.entity.UserInfornation;

public class ResourcesDTO {
	private Integer resId;
	private String resName;
	private String resIdentify;		//资料辨识
	private Date resDate;
	
	private String userId;//维护关联关系
	private String userName;
	
	
	public static void  dtoToEntity(ResourcesDTO dto,Resources entity) {
		BeanUtils.copyProperties(dto, entity);
		String userId = dto.getUserId();
		
		if(userId!=null && !"".equals(userId.trim())) {
			UserInfornation user = new UserInfornation();
			user.setUserId(userId);//关联关系维护
			
			entity.setUser(user);
		}
	}
	
	public static void  entityToDto(Resources entity , ResourcesDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		
		UserInfornation user = entity.getUser();
		if(user!=null) {
			dto.setUserId(user.getUserId());
			dto.setUserName(user.getUserName());

		}
		
	}
	
	
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
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
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
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	

}
