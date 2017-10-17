package com.oa.staff.entity.dto;

import org.springframework.beans.BeanUtils;

import com.oa.staff.entity.UserInfornation;


public class TaskUserDTO {
	
	private String userId;
	private String realName;
	
	public static void entityToDto(UserInfornation entity, TaskUserDTO dto) {
		BeanUtils.copyProperties(entity, dto);
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
}
